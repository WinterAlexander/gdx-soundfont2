package com.github.winteralexander.gdx.soundfont2.hydra;

import me.winter.gdx.utils.io.CustomSerializable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static me.winter.gdx.utils.io.StreamUtil.readShort;
import static me.winter.gdx.utils.io.StreamUtil.readUnsignedShort;

/**
 * TODO Undocumented :(
 * <p>
 * Created on 2023-08-16.
 *
 * @author Alexander Winter
 */
public class HydraIMod implements CustomSerializable {
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

	@Override
	public void writeTo(OutputStream outputStream) throws IOException {

	}
}
