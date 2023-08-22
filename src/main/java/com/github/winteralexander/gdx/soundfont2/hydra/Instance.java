package com.github.winteralexander.gdx.soundfont2.hydra;

import me.winter.gdx.utils.io.CustomSerializable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.github.winteralexander.gdx.soundfont2.SerializationUtilPlus.readAsciiCharArray;
import static me.winter.gdx.utils.io.StreamUtil.readUnsignedShort;

/**
 * TODO Undocumented :(
 * <p>
 * Created on 2023-08-16.
 *
 * @author Alexander Winter
 */
public class Instance implements CustomSerializable {
	public String instName;
	public int instBagNdx;

	@Override
	public void readFrom(InputStream input) throws IOException {
		instName = new String(readAsciiCharArray(input, 20));
		instBagNdx = readUnsignedShort(input);
	}

	@Override
	public void writeTo(OutputStream outputStream) throws IOException {

	}
}
