package com.github.winteralexander.gdx.soundfont2.hydra;

import com.github.winteralexander.gdx.soundfont2.SampleLinkType;
import me.winter.gdx.utils.io.CustomSerializable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.github.winteralexander.gdx.soundfont2.SerializationUtilPlus.readAsciiCharArray;
import static me.winter.gdx.utils.io.StreamUtil.*;

/**
 * TODO Undocumented :(
 * <p>
 * Created on 2023-08-16.
 *
 * @author Alexander Winter
 */
public class HydraSampleHeader implements CustomSerializable {
	String sampleName;
	// unsigned int
	int start, end, startLoop, endLoop, sampleRate;
	// unsigned byte
	int originalPitch;
	byte pitchCorrection;

	// unsighed short
	int sampleLink;
	SampleLinkType sampleType;

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

	@Override
	public void writeTo(OutputStream outputStream) throws IOException {

	}
}
