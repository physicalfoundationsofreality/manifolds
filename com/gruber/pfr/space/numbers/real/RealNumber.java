package com.gruber.pfr.space.numbers.real;

import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.rings.RingElement;

public class RealNumber extends RingElement {

// We use double values, however, we instatiate only with float
// so calculations are done with higher precssions than stored
	double base;

	public RealNumber(float base) {

		super(RealNumbers.getInstance());
		
		this.base = base;
	}

	public float getBase() {
		return new Double(base).floatValue();
	}

	public Object clone() {
		
		return new RealNumber(new Double(this.base).floatValue());
	}
	
	public boolean greaterThen(RealNumber num) {
		
		return(this.base > num.base);
		
	}
	public boolean greaterOrEqualThen(RealNumber num) {
		
		return(this.base >= num.base);
		
	}
	public boolean lessThen(RealNumber num) {
		
		return(this.base < num.base);
		
	}
	public boolean lessOrEqualThen(RealNumber num) {
		
		return(this.base <= num.base);
		
	}
	
	public boolean isElement(Set set) {
		return false;
	}
	
	public boolean equals(Object obj) {
// to avoid rounding error caused imprecission, we compare only float values
		try {
			RealNumber num = (RealNumber) obj;
			if (this.getBase() == num.getBase())
				return true;
		} catch (Exception e) {
		}
		
		return false;
	}
	
	public RealNumber add(RealNumber el) {
		
		return (RealNumber)this.add((RingElement)el);
	}
	
	public RealNumber multiply(RealNumber el) {
		
		return (RealNumber)this.multiply((RingElement)el);
	}
	
	public RealNumber getNegative() {
		
		return (RealNumber)super.getNegative();
	}
	
	public RealNumber getInverse() {
		
		return (RealNumber)super.getInverse();
	}
}
