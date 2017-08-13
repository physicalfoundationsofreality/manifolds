package com.gruber.pfr.space.vectors.linearmaps;

import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.modules.Module;
import com.gruber.pfr.space.modules.ModuleElement;

public class AddedMaps extends LinearMap {

	LinearMap map1;
	LinearMap map2;

	public AddedMaps(LinearMap map1, LinearMap map2) {
		super((Module)map1.getDomainBasis(), (Module)map1.getRangeBasis());
		this.map1 = map1;
		this.map2 = map2;
	}

	public Set getImage(Set orig) {
		try {

			return ((ModuleElement) map1.getImage(orig)).add((ModuleElement) map2.getImage(orig));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
