package com.gruber.pfr.space.base.quotient;

import com.gruber.pfr.space.base.Set;

public abstract class QuotientSpace implements Set {
	
	EquivalenceRelation relat;
	QuotientProjection projection;
	
	protected QuotientSpace(Set base, EquivalenceRelation relat) {
		this.relat = relat;
		relat.setBase(base);
	}

	public boolean isElement(Set set) {
		
		EquivalenceClass cl = (EquivalenceClass)set;
		return relat.base.isElement(cl.getRandomElement());
	}

	public Set getRandomElement() {
		
		return relat.getEquivalenceClass(relat.base.getRandomElement());
	}

	public EquivalenceRelation getRelation() {
		return relat;
	}

	public QuotientProjection getProjection() {
		return projection;
	}	

	public Set getBase() {
		return this.relat.getBase();
	}

	public EquivalenceRelation getRelat() {
		return relat;
	}
}
