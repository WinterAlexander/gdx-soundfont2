package com.github.winteralexander.gdx.soundfont2.hydra;

import com.github.winteralexander.gdx.utils.io.Readable;

import java.io.IOException;
import java.io.InputStream;

import static com.github.winteralexander.gdx.utils.io.StreamUtil.readShort;
import static com.github.winteralexander.gdx.utils.io.StreamUtil.readUnsignedShort;

/**
 * TODO Undocumented :(
 * <p>
 * Created on 2023-08-16.
 *
 * @author Alexander Winter
 */
public class ModulatorList implements Readable {
	// unsigned short
	int modSrcOper, modDestOper;
	short modAmount;
	// unsigned short
	int modAmtSrcOper, modTransOper;

	@Override
	public void readFrom(InputStream input) throws IOException {
		modSrcOper = readUnsignedShort(input);
		modDestOper = readUnsignedShort(input);
		modAmount = readShort(input);
		modAmtSrcOper = readUnsignedShort(input);
		modTransOper = readUnsignedShort(input);
	}
}
