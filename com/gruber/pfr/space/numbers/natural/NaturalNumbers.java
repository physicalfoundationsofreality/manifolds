package com.gruber.pfr.space.numbers.natural;

import java.util.Random;

import com.gruber.pfr.space.base.AdditiveSpace;
import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.numbers.rational.RationalNumber;
import com.gruber.pfr.space.rings.Ring;

public class NaturalNumbers extends Ring { // abuse of notation!
	
	static NaturalNumbers instance = null;
	
	Random random = new Random(System.currentTimeMillis());
	
	public Set getRandomElement() {
		
		return new NaturalNumber(Math.abs(random.nextInt()));
	}
	
	public static NaturalNumbers getInstance() {
		
		if(instance == null) 
			instance =  new NaturalNumbers();
		
		return instance;
	}
	
	protected NaturalNumbers() {

		super(new NaturalAddition(), new NaturalMultiplication());
	}

	public boolean isElement(Set set) {

		try {
			NaturalNumber num = (NaturalNumber)set;
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public AdditiveSpace getAdditiveGroup() {

		return this;
	}

	public Set getNullElement() {
		return new NaturalNumber(0);
	}

	public Set getNegative(Set element) {
		
		try {
			NaturalNumber num = (NaturalNumber)element;
			return new NaturalNumber(-num.base);
					
		} catch(Exception e) {
			return null;
		}
	}

	public Set getOneElement() {

		return new NaturalNumber(1);
	}

	public Set getInverse(Set element) {

		try {
			NaturalNumber num = (NaturalNumber)element;
			if(Math.abs(num.base) == 1)
				return new NaturalNumber(1/num.base);
			else
				return null;
					
		} catch(Exception e) {
			return null;
		}
	}
}
