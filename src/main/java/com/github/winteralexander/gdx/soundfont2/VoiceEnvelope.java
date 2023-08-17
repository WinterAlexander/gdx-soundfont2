package com.github.winteralexander.gdx.soundfont2;

/**
 * TODO Undocumented :(
 * <p>
 * Created on 2023-08-16.
 *
 * @author Alexander Winter
 */
public class VoiceEnvelope {
	float level, slope;
	int samplesUntilNextSegment;
	short segment, midiVelocity;
	Envelope parameters;
	boolean segmentIsExponential, isAmpEnv;

}
