package com.gruber.pfr.space.vectors;

import com.gruber.pfr.space.base.AutoOperation;
import com.gruber.pfr.space.modules.ScalarMultiplication;
import com.gruber.pfr.space.rings.Field;

public abstract class FiniteDimensionalVectorSpace extends VectorSpace {
	
	int dim;

	protected FiniteDimensionalVectorSpace(Field baseField, AutoOperation addition, ScalarMultiplication multiplication, int dim) {
		super(baseField,addition,multiplication);
		this.dim = dim;
	}

	public int getDim() {
		return dim;
	}
}