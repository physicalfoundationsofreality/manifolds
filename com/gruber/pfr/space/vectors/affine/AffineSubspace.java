package com.gruber.pfr.space.vectors.affine;

import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.modules.Module;
import com.gruber.pfr.space.vectors.Vector;

public class AffineSubspace implements Set {

	Module baseSpace;
	Vector baseVector;
	Module baseSubspace;

	public AffineSubspace(Module baseSpace, Vector baseVector, Module baseSubspace) {
		super();
		this.baseSpace = baseSpace;
		this.baseVector = baseVector;
		this.baseSubspace = baseSubspace;
	}

	public boolean isElement(Set set) {

		return baseSubspace
				.isElement(((Vector) set).add((Vector) baseSpace.getNegative(baseVector)));
	}

	public Set getRandomElement() {

		return ((Vector)baseSubspace.getRandomElement()).add(baseVector);
	}
}
