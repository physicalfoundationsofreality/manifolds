package com.gruber.pfr.space.vectors;

import com.gruber.pfr.space.base.AutoOperation;
import com.gruber.pfr.space.modules.Module;
import com.gruber.pfr.space.modules.ScalarMultiplication;
import com.gruber.pfr.space.rings.Field;

public abstract class VectorSpace extends Module {
	
	VectorSpace baseSpace;
	SubSpace subSpace;
	
	public VectorSpace(Field baseField,AutoOperation addition,ScalarMultiplication multiplication) {
		
		super(baseField,addition,multiplication);
	}
	
	public VectorSpace(VectorSpace baseSpace, SubSpace subSpace) {
		
		super(baseSpace.baseRing,(AutoOperation) baseSpace.getAddition(),baseSpace.getMultiplication());
		
		this.baseSpace = baseSpace;
		this.subSpace = subSpace;
	}
	public Field getBaseField() {
		return (Field)this.baseRing;
	}
}
