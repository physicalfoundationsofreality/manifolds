package com.gruber.pfr.space.base.map;

import com.gruber.pfr.space.base.Set;

public interface Map {

	public Set getDomainBasis();
	
	public Set getRangeBasis();
	
	public abstract Set getImage(Set orig);
	
	public abstract Set getPreImage(Set image);
}
