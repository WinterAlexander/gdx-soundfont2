package com.github.winteralexander.gdx.soundfont2.hydra;

import me.winter.gdx.utils.io.CustomSerializable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static me.winter.gdx.utils.io.StreamUtil.readUnsignedShort;

/**
 * TODO Undocumented :(
 * <p>
 * Created on 2023-08-16.
 *
 * @author Alexander Winter
 */
public class HydraGenAmount implements CustomSerializable {
	int low, high;
	short shortAmount;
	short wordAmount;

	@Override
	public void readFrom(InputStream input) throws IOException {
		int data = readUnsignedShort(input);
		low = (data & 0xFF);
		high = (data >> 8 & 0xFF);
		shortAmount = (short)data;
		wordAmount = (short)data;
	}

	@Override
	public void writeTo(OutputStream output) throws IOException {

	}
}
