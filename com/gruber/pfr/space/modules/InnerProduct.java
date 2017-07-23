package com.gruber.pfr.space.modules;

import com.gruber.pfr.space.base.Operation;
import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.rings.RingElement;

public abstract class InnerProduct implements Operation {

	Module base = null;
	
	public void setBase(Module base) {
		this.base = base;
	}

	public Module getBase() {
		return base;
	}

	public Set getFirstOperantDomain() {
		
		return base;
	}

	public Set getSecondOperantDomain() {

		return base;
	}

	public Set getOperationRange() {

		return base.getBaseRing();
	}
	public RingElement operate(ModuleElement vec1, ModuleElement vec2) throws OperantException {
		
		return (RingElement)this.operate((Set)vec1,(Set) vec2);
	}
}
