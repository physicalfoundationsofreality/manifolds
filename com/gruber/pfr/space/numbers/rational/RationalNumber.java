package com.gruber.pfr.space.numbers.rational;

import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.rings.RingElement;

public class RationalNumber extends RingElement {

	float base;

	public RationalNumber(float base) {

		super(RationalNumbers.getInstance());
		
		this.base = base;
	}

	public float getBase() {
		return base;
	}

	public Object clone() {
		
		return new RationalNumber(this.base);
	}
	
	public boolean isElement(Set set) {
		return false;
	}
	
	public boolean equals(Object obj) {
		
		try {
			RationalNumber num = (RationalNumber) obj;
			if (base == num.base)
				return true;
		} catch (Exception e) {
		}
		
		return false;
	}
}
