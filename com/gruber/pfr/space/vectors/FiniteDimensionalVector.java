package com.gruber.pfr.space.vectors;

public class FiniteDimensionalVector extends Vector {

	public FiniteDimensionalVector(FiniteDimensionalVectorSpace space) {
		super(space);
	}
	public int getDim() {
		
		return ((FiniteDimensionalVectorSpace)this.getSpace()).getDim();
	}
}
