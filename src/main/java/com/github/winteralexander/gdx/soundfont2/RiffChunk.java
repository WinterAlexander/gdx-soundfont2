package com.github.winteralexander.gdx.soundfont2;

import java.io.IOException;
import java.io.InputStream;

import static com.github.winteralexander.gdx.soundfont2.SerializationUtilPlus.readAsciiCharArray;
import static me.winter.gdx.utils.io.StreamUtil.readInt;

/**
 * TODO Undocumented :(
 * <p>
 * Created on 2023-08-16.
 *
 * @author Alexander Winter
 */
public class RiffChunk {
	String id;
	int size;

	public void read(RiffChunk parent, InputStream stream) throws IOException
	{
		if(parent != null && parent.size < 8)
			throw new IOException("Not enough size in parent");
		id = new String(readAsciiCharArray(stream, 4));
		char c = id.charAt(0);
		if(c <= ' ' || c > 'z')
			throw new IOException("Invalid chunk ID was read: " + id);

		size = readInt(stream);

		if(parent != null && parent.size < 8 + size)
			throw new IOException("Not enough size in parent");

		boolean isRiff = id.equals("RIFF");
		boolean isList = id.equals("LIST");

		if(isRiff && parent != null)
			throw new IOException("RIFF chunk cannot have parent");

		if(!isRiff && !isList)
			throw new IOException("Unrecognized chunk ID: " + id);

		id = new String(readAsciiCharArray(stream, 4));

		size -= 4;
	}
}
