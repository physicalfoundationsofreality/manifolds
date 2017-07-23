package com.gruber.pfr.space.base.map;

import com.gruber.pfr.space.base.Set;

public class Identity implements Map {

	Set set;
	
	public Identity(Set set) {
		this.set = set;
	}

	public Set getDomainBasis() {
		return set;
	}

	public Set getRangeBasis() {
		return set;
	}

	public Set getImage(Set orig) {
		return orig;
	}

	public Set getPreImage(Set image) {
		return image;
	}
}
