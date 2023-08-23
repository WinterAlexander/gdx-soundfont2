package com.github.winteralexander.gdx.soundfont2;

import java.io.IOException;
import java.io.InputStream;

import static com.github.winteralexander.gdx.utils.io.StreamUtil.readUnsignedByte;

/**
 * TODO Undocumented :(
 * <p>
 * Created on 2023-08-16.
 *
 * @author Alexander Winter
 */
public class SerializationUtilPlus {
	public static char[] readAsciiCharArray(InputStream input, int size) throws IOException {
		char[] chars = new char[size];
		while(size > 0) {
			chars[chars.length - size] = (char)readUnsignedByte(input);
			size--;
		}
		return chars;
	}
}
