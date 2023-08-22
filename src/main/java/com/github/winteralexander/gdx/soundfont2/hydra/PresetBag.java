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
public class PresetBag implements CustomSerializable {
	// unsigned short
	public int genNdx, modNdx;

	@Override
	public void readFrom(InputStream input) throws IOException {
		genNdx = readUnsignedShort(input);
		modNdx = readUnsignedShort(input);
	}

	@Override
	public void writeTo(OutputStream output) throws IOException {

	}
}
