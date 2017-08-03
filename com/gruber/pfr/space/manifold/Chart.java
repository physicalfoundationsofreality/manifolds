package com.gruber.pfr.space.manifold;

import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.base.map.Map;
import com.gruber.pfr.space.numbers.real.RealVector;

public interface Chart extends Map {

	public RealVector getImage(Set point);
	
	public Set getPreImage(RealVector point);
	
	public boolean isInDomain(Set point);
	
	public boolean isInRange(RealVector point);
	
	public RealVector getDirection(RealVector vector);

}
