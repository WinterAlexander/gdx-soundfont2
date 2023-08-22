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
	public PresetHeader[] phdrs = null;
	public PresetBag[] pbags = null;
	public ModList[] pmods = null;
	public GeneratorList[] pgens = null;
	public Instance[] insts = null;
	public InstanceBag[] ibags = null;
	public ModList[] imods = null;
	public GeneratorList[] igens = null;
	public SampleHeader[] shdrs = null;

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
		Array<?> array2 = (Array<?>)array;

		switch(hydraChunkType) {
			case PHDR: phdrs = array2.toArray((Class<PresetHeader>)hydraChunkType.getClassType()); break;
			case PBAG: pbags = array2.toArray((Class<PresetBag>)hydraChunkType.getClassType()); break;
			case PMOD: pmods = array2.toArray((Class<ModList>)hydraChunkType.getClassType()); break;
			case PGEN: pgens = array2.toArray((Class<GeneratorList>)hydraChunkType.getClassType()); break;
			case INST: insts = array2.toArray((Class<Instance>)hydraChunkType.getClassType()); break;
			case IBAG: ibags = array2.toArray((Class<InstanceBag>)hydraChunkType.getClassType()); break;
			case IMOD: imods = array2.toArray((Class<ModList>)hydraChunkType.getClassType()); break;
			case IGEN: igens = array2.toArray((Class<GeneratorList>)hydraChunkType.getClassType()); break;
			case SHDR: shdrs = array2.toArray((Class<SampleHeader>)hydraChunkType.getClassType()); break;
			default: throw new IllegalArgumentException("Unrecognized HydraChunkType: " + hydraChunkType);
		}
	}
}
