package com.gruber.pfr.space.modules;

import com.gruber.pfr.space.base.Operation;
import com.gruber.pfr.space.base.Set;

public abstract class ScalarMultiplication implements Operation {

	Module base = null;
	
	public void setBase(Module base) {
		this.base = base;
	}

	public Module getBase() {
		return base;
	}

	public Set getFirstOperantDomain() {
		
		return base.getBaseRing();
	}

	public Set getSecondOperantDomain() {

		return base;
	}

	public Set getOperationRange() {

		return base;
	}
}
