package com.gruber.pfr.space.numbers.integer;

import com.gruber.pfr.space.base.quotient.EquivalenceClass;

public class ModuloClass extends EquivalenceClass {
	
	int length;
	int intBase;

	public ModuloClass(int length, int base) {
		
		super(IntegerNumbers.getInstance(), new ModuloRelation(length), new IntegerNumber(base));
		this.length = length;
		this.intBase = base % length;
		if(this.intBase < 0)
			intBase += length;
	}

	public int getLength() {
		return length;
	}

	public int getIntBase() {
		return intBase;
	}
}