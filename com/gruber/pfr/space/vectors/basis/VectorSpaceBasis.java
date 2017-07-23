package com.gruber.pfr.space.vectors.basis;

import com.gruber.pfr.space.modules.LinearIndependentSet;
import com.gruber.pfr.space.modules.Module;
import com.gruber.pfr.space.rings.RingElement;
import com.gruber.pfr.space.vectors.Vector;
import com.gruber.pfr.space.vectors.VectorSpace;

public interface VectorSpaceBasis extends LinearIndependentSet {

	
	public RingElement getCoordinate(Vector baseVector, Vector vector);
	
	public VectorSpace getBaseSpace();

	public void setBaseSpace(VectorSpace space);
	
	default public void setBaseSpace(Module space) {
		this.setBaseSpace((VectorSpace)space);
	}
}