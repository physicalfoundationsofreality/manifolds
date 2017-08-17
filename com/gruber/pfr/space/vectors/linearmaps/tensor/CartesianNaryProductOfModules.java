package com.gruber.pfr.space.vectors.linearmaps.tensor;

import com.gruber.pfr.space.base.CartesianNaryProduct;
import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.base.Tupel;
import com.gruber.pfr.space.modules.Module;
import com.gruber.pfr.space.modules.ModuleElement;

public class CartesianNaryProductOfModules implements CartesianNaryProduct {
	
	Module[] baseModules;
	
	public CartesianNaryProductOfModules(Module[] baseModules) {
		
		this.baseModules = baseModules;
	}
	

	public Module[] getBaseModules() {
		return baseModules;
	}


	public void setBaseModules(Module[] baseModules) {
		this.baseModules = baseModules;
	}

	public Set[] getBaseSets() {
		return this.getBaseModules();
	}

	public Tupel createTupel(Set[] constituents) {
		return new ModuleElementTupel(this, (ModuleElement[]) constituents);
	}
}
