package com.gruber.pfr.space.vectors.basis;

import com.gruber.pfr.space.rings.RingElement;
import com.gruber.pfr.space.vectors.FiniteDimensionalVectorSpace;
import com.gruber.pfr.space.vectors.Vector;

public class DerivedFiniteVectorSpaceBasis extends FiniteDimensionalVectorSpaceBasis {

	public DerivedFiniteVectorSpaceBasis(Vector[] baseVectors, FiniteDimensionalVectorSpace span) {

		super(span, baseVectors);
	}

	public RingElement getCoordinate(Vector baseVector, Vector vector) {
		return null;
	}
}
