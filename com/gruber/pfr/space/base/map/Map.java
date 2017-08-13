package com.gruber.pfr.space.base.map;

import com.gruber.pfr.space.base.Set;

public interface Map extends Set {

	public Set getDomainBasis();
	
	public Set getRangeBasis();
	
	public abstract Set getImage(Set orig);
	
// the following methods are good to have but are not necessary to define a map
	default public Set getPreImage(Set image) {
		return null;
	}
	
	default public boolean isElement(Set set) {
		// TODO Auto-generated method stub
		return false;
	}

	default public Set getRandomElement() {
		// TODO Auto-generated method stub
		return null;
	}
}
