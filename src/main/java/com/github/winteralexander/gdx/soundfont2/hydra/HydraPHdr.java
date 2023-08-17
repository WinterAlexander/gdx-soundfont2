package com.github.winteralexander.gdx.soundfont2.hydra;

import me.winter.gdx.utils.io.CustomSerializable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.github.winteralexander.gdx.soundfont2.SerializationUtilPlus.readAsciiCharArray;
import static me.winter.gdx.utils.io.StreamUtil.readInt;
import static me.winter.gdx.utils.io.StreamUtil.readUnsignedShort;

/**
 * TODO Undocumented :(
 * <p>
 * Created on 2023-08-16.
 *
 * @author Alexander Winter
 */
public class HydraPHdr implements CustomSerializable {
	String presetName;
	int preset, bank, presetBagNdx;
	int library, genre, morphology; // unsigned

	@Override
	public void readFrom(InputStream input) throws IOException {
		presetName = new String(readAsciiCharArray(input, 20));
		preset = readUnsignedShort(input);
		bank = readUnsignedShort(input);
		presetBagNdx = readUnsignedShort(input);
		library = readInt(input);
		genre = readInt(input);
		morphology = readInt(input);
	}

	@Override
	public void writeTo(OutputStream outputStream) throws IOException {

	}
}
