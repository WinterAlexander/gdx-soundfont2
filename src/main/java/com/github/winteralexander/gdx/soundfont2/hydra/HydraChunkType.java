package com.github.winteralexander.gdx.soundfont2.hydra;

import com.github.winteralexander.gdx.utils.io.Readable;

import java.util.Locale;

import static com.github.winteralexander.gdx.utils.Validation.ensureNotNull;

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
	PMOD(10, ModulatorList.class),
	PGEN(4, GeneratorList.class),
	INST(22, Instrument.class),
	IBAG(4, InstrumentBag.class),
	IMOD(10, ModulatorList.class),
	IGEN(4, GeneratorList.class),
	SHDR(46, SampleHeader.class);

	public static final HydraChunkType[] values = values();
	private final int sizeInFile;
	private final Class<? extends Readable> classType;

	HydraChunkType(int sizeInFile, Class<? extends Readable> classType) {
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

	public Class<? extends Readable> getClassType() {
		return classType;
	}
}
