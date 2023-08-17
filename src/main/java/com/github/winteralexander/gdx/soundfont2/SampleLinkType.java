package com.github.winteralexander.gdx.soundfont2;

import com.badlogic.gdx.utils.IntMap;

/**
 * Undocumented :(
 * <p>
 * Created on 2023-08-17.
 *
 * @author Alexander Winter
 */
public enum SampleLinkType {
	MONO_SAMPLE(1),
	RIGHT_SAMPLE(2),
	LEFT_SAMPLE(4),
	LINKED_SAMPLE(8),
	ROM_MONO_SAMPLE(0x8001),
	ROM_RIGHT_SAMPLE(0x8002),
	ROM_LEFT_SAMPLE(0x8004),
	ROM_LINKED_SAMPLE(0x8008);

	public static final SampleLinkType[] values = values();

	private static final IntMap<SampleLinkType> map = new IntMap<>();
	static {
		for(SampleLinkType link : values)
			map.put(link.getValue(), link);
	}

	private final int value;

	SampleLinkType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static SampleLinkType fromValue(int value) {
		return map.get(value);
	}
}
