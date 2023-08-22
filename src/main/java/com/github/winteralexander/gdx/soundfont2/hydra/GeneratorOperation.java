package com.github.winteralexander.gdx.soundfont2.hydra;

import com.badlogic.gdx.utils.IntMap;

/**
 * Undocumented :(
 * <p>
 * Created on 2023-08-22.
 *
 * @author Alexander Winter
 */
public enum GeneratorOperation {
	INSTRUMENT(41),
	KEY_RANGE(43),
	VEL_RANGE(44),
	SAMPLE_ID(53);

	public static final GeneratorOperation[] values = values();

	private static final IntMap<GeneratorOperation> map = new IntMap<>();
	static {
		for(GeneratorOperation oper : values)
			map.put(oper.getValue(), oper);
	}

	private final int value;

	GeneratorOperation(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static GeneratorOperation fromValue(int value) {
		return map.get(value);
	}
}
