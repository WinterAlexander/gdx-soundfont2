package com.github.winteralexander.gdx.soundfont2.hydra;

import com.badlogic.gdx.utils.Array;

/**
 * TODO Undocumented :(
 * <p>
 * Created on 2023-08-16.
 *
 * @author Alexander Winter
 */
public class Hydra
{
	Array<PresetHeader> phdrs = new Array<>();
	Array<PresetBag> pbags = new Array<>();
	Array<ModList> pmods = new Array<>();
	Array<GeneratorList> pgens = new Array<>();
	Array<Instance> insts = new Array<>();
	Array<InstanceBag> ibags = new Array<>();
	Array<ModList> imods = new Array<>();
	Array<GeneratorList> igens = new Array<>();
	Array<SampleHeader> shdrs = new Array<>();

	public boolean isIncomplete() {
		return false;
	}
}
