package com.gruber.pfr.space.vectors;

import com.gruber.pfr.space.base.AutoOperation;
import com.gruber.pfr.space.modules.Module;
import com.gruber.pfr.space.modules.ScalarMultiplication;
import com.gruber.pfr.space.rings.Field;
import com.gruber.pfr.space.vectors.basis.VectorSpan;

public abstract class VectorSpace extends Module {

	
	public VectorSpace(Field baseField,AutoOperation addition,ScalarMultiplication multiplication) {
		
		super(baseField,addition,multiplication);
	}
	
	public Field getBaseField() {
		return (Field)this.baseRing;
	}
	
	public Module getNullSpace() {
		return new VectorSpan(new Vector[0], this);
	}
}
