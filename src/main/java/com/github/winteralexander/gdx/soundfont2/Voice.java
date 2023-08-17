package com.github.winteralexander.gdx.soundfont2;

/**
 * TODO Undocumented :(
 * <p>
 * Created on 2023-08-16.
 *
 * @author Alexander Winter
 */
public class Voice {
	int playingPreset, playingKey, playingChannel;
	Region region;
	double pitchInputTimecents, pitchOutputFactor;
	double sourceSamplePosition;
	float noteGainDB, panFactorLeft, panFactorRight;
	int playIndex, loopStart, loopEnd;
	VoiceEnvelope ampEnv, modEnv;
	VoiceLowPass lowpass;
	VoiceLowFrequencyOscillator modLfo, vibLfo;
}
