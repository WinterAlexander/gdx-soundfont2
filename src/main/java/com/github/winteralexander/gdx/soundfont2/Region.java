package com.github.winteralexander.gdx.soundfont2;

import com.github.winteralexander.gdx.soundfont2.hydra.GeneratorAmount;

/**
 * TODO Undocumented :(
 * <p>
 * Created on 2023-08-16.
 *
 * @author Alexander Winter
 */
public class Region {
	int loop_mode;
	int sample_rate;
	byte lowKey, highKey, lowVelocity, highVelocity;
	int group, offset, end, loopStart, loopEnd;
	int transpose, tune, pitchKeycenter, pitchKeytrack;
	float attenuation, pan;
	Envelope ampEnvelope = new Envelope(), modEnvelope = new Envelope();
	int initialFilterQ, initialFilterFc;
	int modEnvToPitch, modEnvToFilterFc, modLfoToFilterFc, modLfoToVolume;
	float delayModLFO;
	int freqModLFO, modLfoToPitch;
	float delayVibLFO;
	int freqVibLFO, vibLfoToPitch;

	public void clear(boolean forRelative)
	{
		loop_mode = 0;
		sample_rate = 0;
		lowKey = 0;
		lowVelocity = 0;

		group = 0;
		offset = 0;
		end = 0;
		loopStart = 0;
		loopEnd = 0;
		transpose = 0;
		tune = 0;
		pitchKeytrack = 0;
		attenuation = 0.0f;
		pan = 0.0f;
		ampEnvelope.clear();
		modEnvelope.clear();
		initialFilterQ = 0;
		initialFilterFc = 0;
		modEnvToPitch = 0;
		modEnvToFilterFc = 0;
		modLfoToFilterFc = 0;
		modLfoToVolume = 0;
		delayModLFO = 0.0f;
		freqModLFO = 0;
		modLfoToPitch = 0;
		delayVibLFO = 0.0f;
		freqVibLFO = 0;
		vibLfoToPitch = 0;

		highKey = highVelocity = 127;
		pitchKeycenter = 60;
		if(forRelative)
			return;

		pitchKeytrack = 100;
		pitchKeycenter = -1;

		ampEnvelope.delay = ampEnvelope.attack = ampEnvelope.hold = ampEnvelope.decay = ampEnvelope.release = -12000.0f;
		modEnvelope.delay = modEnvelope.attack = modEnvelope.hold = modEnvelope.decay = modEnvelope.release = -12000.0f;

		initialFilterFc = 13500;
		delayModLFO = -12000.0f;
		delayVibLFO = -12000.0f;
	}

	public void operator(int genOper, GeneratorAmount generatorAmount, Region otherRegion) {
		if(generatorAmount != null) {
			int offset;
			if(genOper >=)
		} else {

		}
	}
}
