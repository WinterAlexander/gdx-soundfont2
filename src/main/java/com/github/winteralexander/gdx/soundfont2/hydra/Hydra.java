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
	public Array<PresetHeader> phdrs = new Array<>();
	public Array<PresetBag> pbags = new Array<>();
	public Array<ModList> pmods = new Array<>();
	public Array<GeneratorList> pgens = new Array<>();
	public Array<Instance> insts = new Array<>();
	public Array<InstanceBag> ibags = new Array<>();
	public Array<ModList> imods = new Array<>();
	public Array<GeneratorList> igens = new Array<>();
	public Array<SampleHeader> shdrs = new Array<>();

	public boolean isIncomplete() {
		return false;
	}
}
