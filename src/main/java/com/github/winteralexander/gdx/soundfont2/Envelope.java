package com.github.winteralexander.gdx.soundfont2;

/**
 * TODO Undocumented :(
 * <p>
 * Created on 2023-08-16.
 *
 * @author Alexander Winter
 */
public class Envelope {
	float delay, attack, hold, decay, sustain, release, keynumToHold, keynumToDecay;

	public void clear() {
		delay = 0.0f;
		attack = 0.0f;
		hold = 0.0f;
		decay = 0.0f;
		sustain = 0.0f;
		release = 0.0f;
		keynumToHold = 0.0f;
		keynumToDecay = 0.0f;
	}
}
