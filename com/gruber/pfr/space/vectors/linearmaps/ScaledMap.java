package com.gruber.pfr.space.vectors.linearmaps;

import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.modules.Module;
import com.gruber.pfr.space.modules.ModuleElement;
import com.gruber.pfr.space.rings.RingElement;

public class ScaledMap extends LinearMap {

	LinearMap map;
	RingElement scalar;

	public ScaledMap(LinearMap map, RingElement scalar) {
		super((Module)map.getDomainBasis(), (Module)map.getRangeBasis());
		this.map = map;
		this.scalar = scalar;
	}

	public Set getImage(Set orig) {
		try {

			return ((ModuleElement) map.getImage(orig)).multiply(scalar);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
