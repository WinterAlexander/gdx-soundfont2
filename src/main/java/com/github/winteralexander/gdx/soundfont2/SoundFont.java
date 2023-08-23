package com.github.winteralexander.gdx.soundfont2;

import com.badlogic.gdx.utils.Array;
import com.github.winteralexander.gdx.soundfont2.hydra.*;
import com.github.winteralexander.gdx.utils.io.Readable;

import java.io.IOException;
import java.io.InputStream;

/**
 * TODO Undocumented :(
 * <p>
 * Created on 2023-08-16.
 *
 * @author Alexander Winter
 */
public class SoundFont implements Readable {
	Preset[] presets;
	float[] fontSamples;
	Array<Voice> voices;
	Array<Channel> channels;

	long voicePlayIndex;
	float outSampleRate;
	float globalGainDB;

	public static double timecentsToSeconds(double timecents)
	{
		return Math.pow(2.0, timecents / 1200.0);
	}

	public float centsToHertz(float cents)
	{
		return 8.176f * (float)Math.pow(2.0, cents / 1200.0);
	}

	public float decibelsToGain(float db)
	{
		return db > -100.0f ? (float)Math.pow(10.0, db * 0.05f) : 0.0f;
	}

	public float gainToDecibels(float gain)
	{
		return gain <= 0.00001f ? -100.0f : (float)(20.0 * Math.log10(gain));
	}

	@Override
	public void readFrom(InputStream stream) throws IOException {
		RiffChunk chunkHead = new RiffChunk();
		RiffChunk chunkList = new RiffChunk();
		RiffChunk chunk = new RiffChunk();
		Hydra hydra = new Hydra();
		byte[] sampleBuffer = null;

		chunkHead.read(stream, null);
		if(!chunkHead.id.equals("sfbk"))
			throw new IOException("Incorrect Chunk head ID");

		while(chunkHead.size >= 8) {
			chunkList.read(stream, chunkHead);

			if(chunkList.id.equals("pdta")) {
				while(chunkList.size >= 8) {
					chunk.read(stream, chunkList);
					boolean found = false;
					for(HydraChunkType hydraChunkType : HydraChunkType.values) {
						if(chunk.id.equals(hydraChunkType.getId())
								&& chunk.size % hydraChunkType.getSizeInFile() == 0) {
							found = true;

							int count = chunk.size / hydraChunkType.getSizeInFile();
							hydra.readHydraChunks(stream, hydraChunkType, count);
							break;
						}
					}

					if(!found)
						if(stream.skip(chunk.size) != chunk.size)
							throw new IOException("Unable to skip chunk");
				}
			} else if(chunkList.id.equals("sdta")) {
				while(chunkList.size >= 8) {
					chunk.read(stream, chunkList);

					if(chunk.id.equals("smpl") && sampleBuffer == null && chunk.size >= 2) {
						sampleBuffer = loadSamples(stream, chunk.size);
					} else if(stream.skip(chunk.size) != chunk.size)
						throw new IOException("Unable to skip chunk");
				}
			} else {
				if(stream.skip(chunkList.size) != chunkList.size)
					throw new IOException("Unable to skip chunk");
			}
		}

		if(hydra.isIncomplete())
			throw new IOException("Soundfont is incomplete");

		if(sampleBuffer == null)
			throw new IOException("No sample data");

		decodeSamples(sampleBuffer);
		loadPresets(hydra);
	}

	private void decodeSamples(byte[] sampleBuffer) {
		fontSamples = new float[sampleBuffer.length / 2];
		for(int i = 0; i < fontSamples.length; i++) {
			int byte1 = Byte.toUnsignedInt(sampleBuffer[i * 2]);
			int byte2 = Byte.toUnsignedInt(sampleBuffer[i * 2 + 1]);
			fontSamples[i] = (short)((byte1 << 8) | byte2) / 32767.0f;
		}
		outSampleRate = 44100.0f;
	}

	private byte[] loadSamples(InputStream stream, int sampleLength) throws IOException {

		byte[] buffer = new byte[sampleLength];

		if(stream.read(buffer) != sampleLength)
			throw new IOException("Incomplete sample data");

		return buffer;
	}

	private void loadPresets(Hydra hydra) {
		presets = new Preset[hydra.phdrs.length - 1];

		for(int i = 0; i < hydra.phdrs.length - 1; i++) {
			PresetHeader presetHeader = hydra.phdrs[i];
			PresetHeader nextPHeader = hydra.phdrs[i + 1];
			int sortedIndex = 0;
			for(int j = 0; j < hydra.phdrs.length - 1; j++) {
				PresetHeader other = hydra.phdrs[j];

				if(presetHeader == other || other.bank > presetHeader.bank)
					continue;
				if(other.bank < presetHeader.bank)
					sortedIndex++;
				else if(other.preset > presetHeader.preset)
					continue;
				else if(other.preset < other.bank || j < i)
					sortedIndex++;
			}

			Preset preset = new Preset();

			preset.name = presetHeader.presetName;
			preset.bank = presetHeader.bank;
			preset.preset = presetHeader.preset;

			presets[sortedIndex] = preset;
			int regionCount = 0;

			for(int pBagIdx = presetHeader.presetBagNdx;
			    pBagIdx < nextPHeader.presetBagNdx;
				pBagIdx++) {
				PresetBag bag = hydra.pbags[pBagIdx];
				PresetBag nextBag = hydra.pbags[pBagIdx + 1];
				int pLowKey = 0;
				int pHighKey = 127;
				int pLowVel = 0;
				int pHighVel = 127;

				for(int pGenIdx = bag.genNdx; pGenIdx < nextBag.genNdx; pGenIdx++) {
					GeneratorList gen = hydra.pgens[pGenIdx];

					switch(gen.genOper) {
						case KEY_RANGE:
							pLowKey = gen.genAmount.low;
							pHighKey = gen.genAmount.high;
							continue;

						case VEL_RANGE:
							pLowVel = gen.genAmount.low;
							pHighVel = gen.genAmount.high;
							continue;
					}

					if(gen.genOper != GeneratorOperation.INSTRUMENT
							|| gen.genAmount.wordAmount >= hydra.insts.length)
						continue;

					Instrument instrument = hydra.insts[gen.genAmount.wordAmount];
					Instrument nextInstrument = hydra.insts[gen.genAmount.wordAmount + 1];

					for(int iBagIdx = instrument.instBagNdx;
					    iBagIdx < nextInstrument.instBagNdx;
					    iBagIdx++) {
						InstrumentBag instrumentBag = hydra.ibags[iBagIdx];
						InstrumentBag nextIBag = hydra.ibags[iBagIdx + 1];
						int iLowKey = 0;
						int iHighKey = 127;
						int iLowVel = 0;
						int iHighVel = 127;

						for(int iGenIdx = instrumentBag.instGenNdx;
						    iGenIdx < nextIBag.instGenNdx;
						    iGenIdx++) {
							GeneratorList iGen = hydra.igens[iGenIdx];
							switch(iGen.genOper) {
								case KEY_RANGE:
									iLowKey = iGen.genAmount.low;
									iHighKey = iGen.genAmount.high;
									continue;
								case VEL_RANGE:
									iLowVel = iGen.genAmount.low;
									iHighVel = iGen.genAmount.high;
									continue;
								case SAMPLE_ID:
									if(iHighKey >= pLowKey
											&& iLowKey <= pHighKey
											&& iHighVel >= pLowVel
											&& iLowVel <= pHighVel)
										regionCount++;
							}
						}
					}
				}
			}

			preset.regions = new Region[regionCount];

			Region globalRegion = new Region();
			globalRegion.clear(true);

			for(int pBagIdx = presetHeader.presetBagNdx;
			    pBagIdx < nextPHeader.presetBagNdx;
				pBagIdx++) {
				PresetBag bag = hydra.pbags[pBagIdx];
				PresetBag nextBag = hydra.pbags[pBagIdx + 1];
				Region presetRegion = globalRegion;
				int hadGenInstrument = 0;
				for(int pGenIdx = bag.genNdx;
					pGenIdx < nextBag.genNdx;
					pGenIdx++) {
					GeneratorList gen = hydra.pgens[pGenIdx];

					if(gen.genOper == GeneratorOperation.INSTRUMENT) {

						int instIdx = gen.genAmount.wordAmount;
						if(instIdx >= hydra.insts.length)
							continue;

						Region instrumentRegion = new Region();
						instrumentRegion.clear(false);
						Instrument instrument = hydra.insts[instIdx];
						Instrument nextInstrument = hydra.insts[instIdx + 1];
						for(int iBagIdx = instrument.instBagNdx;
						    iBagIdx < nextInstrument.instBagNdx;
							iBagIdx++) {
							InstrumentBag instrumentBag = hydra.ibags[iBagIdx];
							InstrumentBag nextIBag = hydra.ibags[iBagIdx + 1];

							Region zoneRegion = instrumentRegion;
							int hadSampleID = 0;

							for(int iGenIdx = instrumentBag.instGenNdx;
							    iGenIdx < nextIBag.instGenNdx;
								iGenIdx++) {
								GeneratorList generatorList = hydra.igens[iGenIdx];

								if(generatorList.genOper == GeneratorOperation.SAMPLE_ID) {
									if(zoneRegion.highKey < presetRegion.lowKey
											|| zoneRegion.lowKey > presetRegion.highKey)
										continue;
									if(zoneRegion.highVelocity < presetRegion.lowVelocity
											|| zoneRegion.lowVelocity > presetRegion.highVelocity)
										continue;
									if(presetRegion.lowKey > zoneRegion.lowKey)
										zoneRegion.lowKey = presetRegion.lowKey;
									if(presetRegion.highKey < zoneRegion.highKey)
										zoneRegion.highKey = presetRegion.highKey;
									if(presetRegion.lowVelocity > zoneRegion.lowVelocity)
										zoneRegion.lowVelocity = presetRegion.lowVelocity;
									if(presetRegion.highVelocity < zoneRegion.highVelocity)
										zoneRegion.highVelocity = presetRegion.highVelocity;


									zoneRegion.operator(0, null, presetRegion);

								}
							}
						}
					}
				}
			}
		}
	}
}
