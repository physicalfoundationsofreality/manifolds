package com.gruber.pfr.space.base.quotient;

import com.gruber.pfr.space.base.Set;

public class EquivalenceClass implements Set {

	EquivalenceRelation relat;
	Set baseElement;
	
	public static int checkNum = 1000;
	
	public EquivalenceClass(Set base, EquivalenceRelation relat, Set baseElement) {
		
		this.relat = relat;
		relat.setBase(base);
		
		this.baseElement = baseElement;
	}

	public boolean isElement(Set set) {
		
		for(int i = 0; i < checkNum; i++) 
			if(!relat.related(baseElement, set.getRandomElement()))
				return false;
		
		return true;
	}

	public Set getRandomElement() {
		
		return relat.getEquivalenceClass(relat.base.getRandomElement());
	}

	public EquivalenceRelation getRelat() {
		return relat;
	}

	public Set getBaseElement() {
		return baseElement;
	}
}
