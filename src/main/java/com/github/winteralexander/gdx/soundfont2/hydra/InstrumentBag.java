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
public class InstrumentBag implements Readable {
	// unsigned short
	public int instGenNdx, instModNdx;

	@Override
	public void readFrom(InputStream input) throws IOException {
		instGenNdx = readUnsignedShort(input);
		instModNdx = readUnsignedShort(input);
	}
}
