package com.gruber.pfr.space.vectors.linearmaps.tensor;

import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.base.map.Map;
import com.gruber.pfr.space.modules.Module;

public abstract class MultiLinearMap implements Map {

	CartesianNaryProductOfModules domain;
	Module range;

	public MultiLinearMap(CartesianNaryProductOfModules domain, Module range) {
		this.domain = domain;
		this.range = range;
	}

	public Set getRangeBasis() {

		return range;
	}
	
	public Set getDomainBasis() {

		return this.domain;
	}
}
