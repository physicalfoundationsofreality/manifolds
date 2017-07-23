package com.gruber.pfr.space.numbers.real;

import java.util.Random;

import com.gruber.pfr.space.base.AdditiveSpace;
import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.rings.Field;
import com.gruber.pfr.space.rings.RingElement;

public class RealNumbers extends Field {
	
	static RealNumbers instance = null;
	
	Random random = new Random(System.currentTimeMillis());
	
	public Set getRandomElement() {
		
		return new RealNumber(random.nextFloat() * random.nextInt());
	}
	
	public static RealNumbers getInstance() {
		
		if(instance == null) 
			instance =  new RealNumbers();
		
		return instance;
	}
	
	protected RealNumbers() {

		super(new RealAddition(), new RealMultiplication());
	}

	public boolean isElement(Set set) {

		try {
			RealNumber num = (RealNumber)set;
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public AdditiveSpace getAdditiveGroup() {

		return this;
	}

	public Set getNullElement() {
		return new RealNumber(0);
	}

	public Set getNegative(Set element) {
		
		try {
			RealNumber num = (RealNumber)element;
			return new RealNumber(new Double(-num.base).floatValue());
					
		} catch(Exception e) {
			return null;
		}
	}

	public Set getOneElement() {

		return new RealNumber(1);
	}
	
	public RealNumber add(RealNumber op1, RealNumber op2) {
		return (RealNumber)this.add((RingElement)op1, (RingElement)op2);
	}

	public RealNumber multiply(RealNumber op1, RealNumber op2) {
		return (RealNumber)this.multiply((RingElement)op1, (RingElement)op2);
	}
	
	public Set getInverse(Set element) {

		try {
			RealNumber num = (RealNumber)element;
			return new RealNumber(new Double(1/num.base).floatValue());
					
		} catch(Exception e) {
			return null;
		}
	}
	
	public RealNumber getNegative(RealNumber element) {
		
		return (RealNumber)this.getNegative((Set)element);
	}
	
	public RealNumber getInverse(RealNumber element) {
		
		return (RealNumber)this.getInverse((Set)element);
	}
}
