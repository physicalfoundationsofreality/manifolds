package com.gruber.pfr.space.vectors.linearmaps.tensor;

import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.base.Tupel;
import com.gruber.pfr.space.modules.ModuleElement;

public class ModuleElementTupel implements Tupel {
	
	CartesianNaryProductOfModules baseSpace;
	ModuleElement[] constituents;

	public ModuleElementTupel(CartesianNaryProductOfModules baseSpace, ModuleElement[] constituents) {
		super();
		this.baseSpace = baseSpace;
		this.constituents = constituents;
	}

	public boolean isElement(Set set) {
		return false;
	}

	public ModuleElement[] getConstituents() {
		return this.constituents;
	}

	public CartesianNaryProductOfModules getBaseSpace() {
		return baseSpace;
	}

	public void setConstituents(ModuleElement[] constituents) {
		this.constituents = constituents;
	}
	
}
