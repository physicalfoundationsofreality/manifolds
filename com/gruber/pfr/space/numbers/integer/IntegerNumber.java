package com.gruber.pfr.space.numbers.integer;

import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.numbers.real.RealNumber;
import com.gruber.pfr.space.rings.RingElement;

public class IntegerNumber extends RingElement {

	int base;

	public IntegerNumber(int base) {

		super(IntegerNumbers.getInstance());
		
		this.base = base;
	}
	
	public Object clone() {
		
		return new IntegerNumber(this.base);
	}

	public int getBase() {
		return base;
	}

	public boolean isElement(Set set) {
		return false;
	}
	
	public boolean equals(Object obj) {
		
		try {
			IntegerNumber num = (IntegerNumber) obj;
			if (base == num.base)
				return true;
		} catch (Exception e) {
		}
		
		return false;
	}
}
