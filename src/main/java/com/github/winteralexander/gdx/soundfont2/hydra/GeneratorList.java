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
public class GeneratorList implements Readable {
	// unsigned short
	public GeneratorOperation genOper;
	public GeneratorAmount genAmount = new GeneratorAmount();

	@Override
	public void readFrom(InputStream input) throws IOException {
		genOper = GeneratorOperation.fromValue(readUnsignedShort(input));
		genAmount.readFrom(input);
	}
}
