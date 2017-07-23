package com.gruber.pfr.space.numbers.real;

import java.util.Arrays;

import com.gruber.pfr.space.rings.RingElement;
import com.gruber.pfr.space.vectors.Vector;
import com.gruber.pfr.space.vectors.knspaces.KnSpace;
import com.gruber.pfr.space.vectors.knspaces.KnVector;
import com.gruber.pfr.space.vectors.knspaces.KnVector.InvalidElementsException;

public class RnSpace extends KnSpace {

	static RnSpace[] RnSpaces = new RnSpace[20];
	
	protected RnSpace(int dim) {
		super(RealNumbers.getInstance(), dim);
	}
	
	public static KnSpace getInstance(int dim) {
		
		if(RnSpaces.length < dim + 1)
			RnSpaces = Arrays.copyOf(RnSpaces, dim + 1);
		
		if(RnSpaces[dim] == null)
			RnSpaces[dim] = new RnSpace(dim);
		
		return RnSpaces[dim];
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