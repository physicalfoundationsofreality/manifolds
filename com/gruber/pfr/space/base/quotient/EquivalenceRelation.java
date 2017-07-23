package com.gruber.pfr.space.base.quotient;

import com.gruber.pfr.space.base.Set;

public abstract class EquivalenceRelation {
	
	Set base;

	public Set getBase() {
		return base;
	}

	public void setBase(Set base) {
		this.base = base;
	}
	
	public abstract EquivalenceClass getEquivalenceClass(Set el);
	
	public abstract boolean related(Set el1, Set el2);
	
	public boolean checkEquivalence(int num) {
		
		for(int i  = 0; i < num; i++) {
			
			Set el1 = base.getRandomElement();
			if(!related(el1,el1))
				return false;
			
			Set el2 = base.getRandomElement();
			if((related(el1,el2) && !related(el2,el1))
				|| (related(el2,el1) && !related(el1,el2)))
				return false;
			
			Set el3 = base.getRandomElement();
			if((related(el1,el2) && related(el2,el3) && !related(el1,el3))
				|| (related(el2,el3) && related(el3,el1) && !related(el2,el1))
				|| (related(el2,el3) && related(el3,el1) && !related(el2,el1)))
				return false;
		}
		return true;
	}
}
