package com.gruber.pfr.space.numbers.rational;

import java.util.Random;

import com.gruber.pfr.space.base.AdditiveSpace;
import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.rings.Field;

public class RationalNumbers extends Field {
	
	static RationalNumbers instance = null;
	
	Random random = new Random(System.currentTimeMillis());
	
	public Set getRandomElement() {
		
		return new RationalNumber(random.nextFloat() * random.nextInt());
	}
	
	public static RationalNumbers getInstance() {
		
		if(instance == null) 
			instance =  new RationalNumbers();
		
		return instance;
	}
	
	protected RationalNumbers() {

		super(new RationalAddition(), new RationalMultiplication());
	}

	public boolean isElement(Set set) {

		try {
			RationalNumber num = (RationalNumber)set;
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public AdditiveSpace getAdditiveGroup() {

		return this;
	}

	public Set getNullElement() {
		return new RationalNumber(0);
	}

	public Set getNegative(Set element) {
		
		try {
			RationalNumber num = (RationalNumber)element;
			return new RationalNumber(-num.base);
					
		} catch(Exception e) {
			return null;
		}
	}

	public Set getOneElement() {

		return new RationalNumber(1);
	}

	public Set getInverse(Set element) {

		try {
			RationalNumber num = (RationalNumber)element;
			return new RationalNumber(1/num.base);
					
		} catch(Exception e) {
			return null;
		}
	}
}
