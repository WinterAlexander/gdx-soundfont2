package com.github.winteralexander.gdx.soundfont2.hydra;

import me.winter.gdx.utils.io.CustomSerializable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.github.winteralexander.gdx.soundfont2.SerializationUtilPlus.readAsciiCharArray;

/**
 * TODO Undocumented :(
 * <p>
 * Created on 2023-08-16.
 *
 * @author Alexander Winter
 */
public class HydraPHdr implements CustomSerializable
{
	String presetName;
	int preset, bank, presetBagNdx;
	long library, genre, morphology;

	@Override
	public void readFrom(InputStream inputStream) throws IOException
	{
		presetName = new String(readAsciiCharArray(inputStream, 20));
	}

	@Override
	public void writeTo(OutputStream outputStream) throws IOException
	{

	}
}