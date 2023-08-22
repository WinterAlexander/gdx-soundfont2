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
public class GeneratorList implements CustomSerializable {
	// unsigned short
	public GeneratorOperation genOper;
	public GeneratorAmount genAmount = new GeneratorAmount();

	@Override
	public void readFrom(InputStream input) throws IOException {
		genOper = GeneratorOperation.fromValue(readUnsignedShort(input));
		genAmount.readFrom(input);
	}

	@Override
	public void writeTo(OutputStream outputStream) throws IOException {

	}
}
