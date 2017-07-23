package com.gruber.pfr.space.numbers.integer;

import java.util.Random;

import com.gruber.pfr.space.base.AdditiveSpace;
import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.numbers.rational.RationalNumber;
import com.gruber.pfr.space.rings.Ring;

public class IntegerNumbers extends Ring {
	
	static IntegerNumbers instance = null;
	
	Random random = new Random(System.currentTimeMillis());
	
	public Set getRandomElement() {
		
		return new IntegerNumber(random.nextInt());
	}
	
	public static IntegerNumbers getInstance() {
		
		if(instance == null) 
			instance =  new IntegerNumbers();
		
		return instance;
	}
	
	protected IntegerNumbers() {

		super(new IntegerAddition(), new IntegerMultiplication());
	}

	public boolean isElement(Set set) {

		try {
			IntegerNumber num = (IntegerNumber)set;
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public AdditiveSpace getAdditiveGroup() {

		return this;
	}

	public Set getNullElement() {
		return new IntegerNumber(0);
	}

	public Set getNegative(Set element) {
		
		try {
			IntegerNumber num = (IntegerNumber)element;
			return new IntegerNumber(-num.base);
					
		} catch(Exception e) {
			return null;
		}
	}

	public Set getOneElement() {

		return new IntegerNumber(1);
	}

	public Set getInverse(Set element) {

		try {
			IntegerNumber num = (IntegerNumber)element;
			if(Math.abs(num.base) == 1)
				return new IntegerNumber(1/num.base);
			else
				return null;
					
		} catch(Exception e) {
			return null;
		}
	}
}
