package com.github.winteralexander.gdx.soundfont2.hydra;

import com.badlogic.gdx.utils.Array;
import me.winter.gdx.utils.io.CustomSerializable;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

/**
 * TODO Undocumented :(
 * <p>
 * Created on 2023-08-16.
 *
 * @author Alexander Winter
 */
public class Hydra
{
	public Array<PresetHeader> phdrs = null;
	public Array<PresetBag> pbags = null;
	public Array<ModList> pmods = null;
	public Array<GeneratorList> pgens = null;
	public Array<Instance> insts = null;
	public Array<InstanceBag> ibags = null;
	public Array<ModList> imods = null;
	public Array<GeneratorList> igens = null;
	public Array<SampleHeader> shdrs = null;

	public boolean isIncomplete() {
		return phdrs == null || pbags == null || pmods == null
				|| pgens == null || insts == null || ibags == null
				|| imods == null || igens == null || shdrs == null;
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	public void readHydraChunks(InputStream stream, HydraChunkType hydraChunkType, int count) throws IOException {
		Array array = new Array(count);
		for(int i = 0; i < count; i++) {
			try {
				CustomSerializable obj = hydraChunkType.getClassType().getConstructor().newInstance();
				obj.readFrom(stream);
				array.add(obj);
			} catch(InstantiationException |
			        IllegalAccessException |
			        InvocationTargetException |
			        NoSuchMethodException ex) {
				throw new RuntimeException(ex);
			}
		}

		switch(hydraChunkType) {
			case PHDR: phdrs = array; break;
			case PBAG: pbags = array; break;
			case PMOD: pmods = array; break;
			case PGEN: pgens = array; break;
			case INST: insts = array; break;
			case IBAG: ibags = array; break;
			case IMOD: imods = array; break;
			case IGEN: igens = array; break;
			case SHDR: shdrs = array; break;
			default: throw new IllegalArgumentException("Unrecognized HydraChunkType: " + hydraChunkType);
		}
	}
}
