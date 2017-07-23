package com.gruber.pfr.space.base;

public abstract class AutoOperation implements Operation {
	
	Set base = null;
	
	public void setBase(Set base) {
		this.base = base;
	}

	public Set getBase() {
		return base;
	}

	public Set getFirstOperantDomain() {
		
		return base;
	}

	public Set getSecondOperantDomain() {

		return base;
	}

	public Set getOperationRange() {

		return base;
	}
}
