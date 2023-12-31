package com.github.winteralexander.gdx.soundfont2.hydra;

import com.github.winteralexander.gdx.soundfont2.SampleLinkType;
import com.github.winteralexander.gdx.utils.io.Readable;

import java.io.IOException;
import java.io.InputStream;

import static com.github.winteralexander.gdx.soundfont2.SerializationUtilPlus.readAsciiCharArray;
import static com.github.winteralexander.gdx.utils.io.StreamUtil.*;

/**
 * TODO Undocumented :(
 * <p>
 * Created on 2023-08-16.
 *
 * @author Alexander Winter
 */
public class SampleHeader implements Readable {
	public String sampleName;
	// unsigned int
	public int start, end, startLoop, endLoop, sampleRate;
	// unsigned byte
	public int originalPitch;
	public byte pitchCorrection;

	// unsighed short
	public int sampleLink;
	public SampleLinkType sampleType;

	@Override
	public void readFrom(InputStream input) throws IOException {
		sampleName = new String(readAsciiCharArray(input, 20));
		start = readInt(input);
		end = readInt(input);
		startLoop = readInt(input);
		endLoop = readInt(input);
		sampleRate = readInt(input);
		originalPitch = readUnsignedByte(input);
		pitchCorrection = readByte(input);
		sampleLink = readUnsignedShort(input);
		sampleType = SampleLinkType.fromValue(readUnsignedShort(input));
	}
}
