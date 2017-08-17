package com.gruber.pfr.space.vectors.linearmaps.homspace;

import com.gruber.pfr.space.rings.RingElement;
import com.gruber.pfr.space.vectors.Vector;
import com.gruber.pfr.space.vectors.linearmaps.LinearMap;

public class HomVector extends Vector {
	
	HomSpace baseSpace;
	LinearMap baseMap;

	public HomVector(LinearMap map, HomSpace baseSpace) {
		super(baseSpace);
		this.baseSpace = baseSpace;
		this.baseMap = map;
	}

	public HomSpace getBaseSpace() {
		return baseSpace;
	}

	public void setBaseSpace(HomSpace baseSpace) {
		this.baseSpace = baseSpace;
	}

	public LinearMap getBaseMap() {
		return baseMap;
	}

	public void setBaseMap(LinearMap baseMap) {
		this.baseMap = baseMap;
	}

	public HomVector add(HomVector map) {
		return this.baseSpace.add(this, map);
	}
	
	public HomVector multiply(RingElement scalar) {
		return this.baseSpace.multiply(scalar, this);
	}
}
