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
	Array<HydraPresetHeader> phdrs = new Array<>();
	Array<HydraPresetBag> pbags = new Array<>();
	Array<HydraModList> pmods = new Array<>();
	Array<HydraGeneratorList> pgens = new Array<>();
	Array<HydraInstance> insts = new Array<>();
	Array<HydraInstanceBag> ibags = new Array<>();
	Array<HydraModList> imods = new Array<>();
	Array<HydraGeneratorList> igens = new Array<>();
	Array<HydraSampleHeader> shdrs = new Array<>();
}
