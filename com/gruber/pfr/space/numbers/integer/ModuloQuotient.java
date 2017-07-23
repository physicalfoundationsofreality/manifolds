package com.gruber.pfr.space.numbers.integer;

import java.util.Random;
import java.util.stream.IntStream;

import com.gruber.pfr.space.base.quotient.QuotientRing;

public class ModuloQuotient extends QuotientRing {
	
	Random random = new Random(System.currentTimeMillis());
	int length;

	public ModuloQuotient(int length) {
		super(IntegerNumbers.instance, new ModuloRelation(length));
		this.length = length;
	}
	public ModuloClass getRandomElement() {
		
		int rand = Math.abs(random.nextInt() % length);
		return new ModuloClass(length,rand);
	}
}
