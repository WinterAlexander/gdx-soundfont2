package com.github.winteralexander.gdx.soundfont2;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import me.winter.gdx.utils.io.CustomSerializable;
import com.github.winteralexander.gdx.soundfont2.hydra.Hydra;

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
	FloatArray fontSamples;
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
		RiffChunk innerChunk = new RiffChunk();
		Hydra hydra = new Hydra();
		Integer sampleBuffer = null;
		int smplLength = 0;

		chunkHead.read(null, stream);
		if(!chunkHead.id.equals("sfbk"))
			throw new IOException("Incorrect Chunk head ID");

		while(true) {
			try {
				chunkList.read(chunkHead, stream);

				if(chunkList.id.equals("pdta")) {
					while(true) {
						try {
							innerChunk.read(chunkList, stream);



						} catch(IOException ex) {
							break;
						}
					}

				} else if(chunkList.id.equals("sdta")) {

				} else {

				}
			} catch(IOException ex) {
				break;
			}
		}

		if(hydra.isIncomplete())
			throw new IOException("Soundfont is incomplete");

		if(sampleBuffer == null)
			throw new IOException("No sample data");

	}

	@Override
	public void writeTo(OutputStream outputStream) throws IOException {

	}
}
