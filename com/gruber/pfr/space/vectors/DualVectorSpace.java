package com.gruber.pfr.space.vectors;

import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.base.map.Map;

public class DualVectorSpace extends VectorSpace {

	VectorSpace baseSpace;

	public DualVectorSpace(VectorSpace baseSpace) {
		super(baseSpace.getBaseField(), null, null);
		this.baseSpace = baseSpace;
	}

	public Set getNullElement() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isElement(Set set) {
		try {
			Map map = (Map) set;
			return (map.getDomainBasis().equals(this.baseSpace) 
					&& map.getRangeBasis().equals(this.baseRing));
		} catch (Exception e) {
			return false;
		}
	}

	public Set getRandomElement() {
		return null;
	}
}
