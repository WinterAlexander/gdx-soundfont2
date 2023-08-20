package com.github.winteralexander.gdx.soundfont2.hydra;

import me.winter.gdx.utils.io.CustomSerializable;

import java.util.Locale;

import static me.winter.gdx.utils.Validation.ensureNotNull;

/**
 * Undocumented :(
 * <p>
 * Created on 2023-08-20.
 *
 * @author Alexander Winter
 */
public enum HydraChunkType {
	PHDR(38, PresetHeader.class),
	PBAG(4, PresetBag.class),
	PMOD(10, ModList.class),
	PGEN(4, GeneratorList.class),
	INST(22, Instance.class),
	IBAG(4, InstanceBag.class),
	IMOD(10, ModList.class),
	IGEN(4, GeneratorList.class),
	SHDR(46, SampleHeader.class);

	public static final HydraChunkType[] values = values();
	private final int sizeInFile;
	private final Class<? extends CustomSerializable> classType;

	HydraChunkType(int sizeInFile, Class<? extends CustomSerializable> classType) {
		ensureNotNull(classType, "classType");
		this.sizeInFile = sizeInFile;
		this.classType = classType;
	}

	public int getSizeInFile() {
		return sizeInFile;
	}

	public String getId() {
		return name().toLowerCase(Locale.ROOT);
	}

	public Class<? extends CustomSerializable> getClassType() {
		return classType;
	}
}
