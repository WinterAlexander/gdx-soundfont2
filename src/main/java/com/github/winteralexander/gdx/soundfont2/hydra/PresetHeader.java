package com.github.winteralexander.gdx.soundfont2.hydra;

import com.github.winteralexander.gdx.utils.io.Readable;

import java.io.IOException;
import java.io.InputStream;

import static com.github.winteralexander.gdx.soundfont2.SerializationUtilPlus.readAsciiCharArray;
import static com.github.winteralexander.gdx.utils.io.StreamUtil.readInt;
import static com.github.winteralexander.gdx.utils.io.StreamUtil.readUnsignedShort;

/**
 * TODO Undocumented :(
 * <p>
 * Created on 2023-08-16.
 *
 * @author Alexander Winter
 */
public class PresetHeader implements Readable {
	public String presetName;
	public int preset, bank, presetBagNdx;
	public int library, genre, morphology; // unsigned

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
}
