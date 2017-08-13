package com.gruber.pfr.space.numbers.real;

import com.gruber.pfr.space.rings.RingElement;
import com.gruber.pfr.space.vectors.Vector;
import com.gruber.pfr.space.vectors.knspaces.KnSpace;
import com.gruber.pfr.space.vectors.knspaces.KnVector;
import com.gruber.pfr.space.vectors.knspaces.KnVector.InvalidElementsException;

public class RnSpace extends KnSpace {
	
	protected RnSpace(int dim) {
		super(RealNumbers.getInstance(), dim);
	}
	
	public static KnSpace getInstance(int dim) {
		
		return KnSpace.getKnSpace(RealNumbers.getInstance(), dim);
	}
	public RealNumber innerProduct(RealVector vec1, RealVector vec2) {

		return (RealNumber)this.innerProduct((Vector)vec1, (Vector)vec2);
	}

	public KnVector newVector(RingElement[] elements) throws InvalidElementsException {
		
		RealNumber[] els = new RealNumber[elements.length];
		for(int i = 0; i < elements.length; i++)
			els[i] = (RealNumber)elements[i];
			
		return new RealVector(els);
	}
}