package com.github.winteralexander.gdx.soundfont2.hydra;

import com.github.winteralexander.gdx.utils.io.Readable;

import java.io.IOException;
import java.io.InputStream;

import static com.github.winteralexander.gdx.utils.io.StreamUtil.readUnsignedShort;

/**
 * TODO Undocumented :(
 * <p>
 * Created on 2023-08-16.
 *
 * @author Alexander Winter
 */
public class GeneratorAmount implements Readable {
	public int low, high;
	public short shortAmount;
	// unsigned short
	public int wordAmount;

	@Override
	public void readFrom(InputStream input) throws IOException {
		int data = readUnsignedShort(input);
		low = (data & 0xFF);
		high = (data >> 8 & 0xFF);
		shortAmount = (short)data;
		wordAmount = data;
	}
}
