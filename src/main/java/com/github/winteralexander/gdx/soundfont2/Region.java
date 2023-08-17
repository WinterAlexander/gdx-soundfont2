package com.github.winteralexander.gdx.soundfont2;

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
	byte low_key, high_key, low_velocity, high_velocity;
	int group, offset, end, loop_start, loop_end;
	int transpose, tune, pitch_keycenter, pitch_keytrack;
	float attenuation, pan;
	Envelope ampEnvelope, modEnvelope;
	int initialFilterQ, initialFilterFc;
	int modEnvToPitch, modEnvToFilterFc, modLfoToFilterFc, modLfoToVolume;
	float delayModLFO;
	int freqModLFO, modLfoToPitch;
	float delayVibLFO;
	int freqVibLFO, vibLfoToPitch;
}
