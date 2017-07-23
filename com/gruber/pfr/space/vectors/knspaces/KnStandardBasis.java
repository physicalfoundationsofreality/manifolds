package com.gruber.pfr.space.vectors.knspaces;

import com.gruber.pfr.space.rings.RingElement;
import com.gruber.pfr.space.vectors.Vector;
import com.gruber.pfr.space.vectors.basis.FiniteDimensionalVectorSpaceBasis;

public class KnStandardBasis extends FiniteDimensionalVectorSpaceBasis {

	public KnStandardBasis(KnSpace space) {
		super(space,null);
		
		KnVector[] basis = new KnVector[space.getDim()];
		for(int i = 0; i < space.getDim(); i++)
			basis[i] = space.getStandardBasisElement(i);
		this.setBaseVectors(basis);

	}

	public RingElement getCoordinate(Vector baseVector, Vector vec) {

		int i = 0;
		for(i = 0; i < this.getBaseVectors().length; i++)
			if(this.getBaseVectors()[i].equals(baseVector))
				break;
		return ((KnVector)vec).elements[i];
	}
	public RingElement getCoordinate(int position, Vector vector) {

		return ((KnVector)vector).elements[position];
	}
}
