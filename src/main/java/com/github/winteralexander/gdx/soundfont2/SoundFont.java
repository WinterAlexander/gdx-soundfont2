package com.github.winteralexander.gdx.soundfont2;

import com.badlogic.gdx.utils.Array;
import com.github.winteralexander.gdx.soundfont2.hydra.Hydra;
import com.github.winteralexander.gdx.soundfont2.hydra.HydraChunkType;
import me.winter.gdx.utils.io.CustomSerializable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * TODO Undocumented :(
 * <p>
 * Created on 2023-08-16.
 *
 * @author Alexander Winter
 */
public class SoundFont implements CustomSerializable {
	Array<Preset> presets;
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

	}

	@Override
	public void writeTo(OutputStream outputStream) throws IOException {

	}
}
