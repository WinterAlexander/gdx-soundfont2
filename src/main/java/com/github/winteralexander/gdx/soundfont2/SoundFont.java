package com.github.winteralexander.gdx.soundfont2;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;

/**
 * TODO Undocumented :(
 * <p>
 * Created on 2023-08-16.
 *
 * @author Alexander Winter
 */
public class SoundFont {
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
}
