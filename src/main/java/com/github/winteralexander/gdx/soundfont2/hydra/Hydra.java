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
	Array<HydraPHdr> phdrs = new Array<>();
	Array<HydraPBag> pbags = new Array<>();
	Array<HydraPMod> pmods = new Array<>();
	Array<HydraPGen> pgens = new Array<>();
	Array<HydraINst> insts = new Array<>();
	Array<HydraIBag> ibags = new Array<>();
	Array<HydraIMod> imods = new Array<>();
	Array<HydraIGen> igens = new Array<>();
	Array<HydraSHdr> shdrs = new Array<>();
}
