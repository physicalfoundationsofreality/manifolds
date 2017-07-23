package com.gruber.pfr.space.numbers.natural;

import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.numbers.real.RealNumber;
import com.gruber.pfr.space.rings.RingElement;

public class NaturalNumber extends RingElement {

	int base;

	public NaturalNumber(int base) {
		
		super(NaturalNumbers.getInstance());
		
		if(base < 0)
			throw new ClassCastException();
		
		this.base = base;
	}

	public int getBase() {
		return base;
	}

	public boolean isElement(Set set) {
		return false;
	}
	
	public boolean equals(Object obj) {
		
		try {
			NaturalNumber num = (NaturalNumber) obj;
			if (base == num.base)
				return true;
		} catch (Exception e) {
		}
		
		return false;
	}
}
