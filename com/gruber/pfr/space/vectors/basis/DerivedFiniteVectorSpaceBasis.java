package com.gruber.pfr.space.vectors.basis;

import com.gruber.pfr.space.rings.RingElement;
import com.gruber.pfr.space.vectors.FiniteDimensionalVector;
import com.gruber.pfr.space.vectors.FiniteDimensionalVectorSpace;
import com.gruber.pfr.space.vectors.Vector;

public class DerivedFiniteVectorSpaceBasis extends FiniteDimensionalVectorSpaceBasis {


	FiniteDimensionalVectorSpaceBasis supBasis;
	
	public DerivedFiniteVectorSpaceBasis(FiniteDimensionalVector[] baseVectors,FiniteDimensionalVectorSpace span, FiniteDimensionalVectorSpaceBasis supBasis) {
		
		super(span,baseVectors);
		this.supBasis = supBasis;
	}
	public RingElement getCoordinate(Vector baseVector, Vector vector) {
		// TODO Auto-generated method stub
		return null;
	}

}
