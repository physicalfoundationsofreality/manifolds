package com.gruber.pfr.space.numbers;

import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.numbers.complex.ComplexNumber;
import com.gruber.pfr.space.numbers.complex.ComplexNumbers;
import com.gruber.pfr.space.numbers.real.RealNumber;
import com.gruber.pfr.space.numbers.real.RealNumbers;
import com.gruber.pfr.space.rings.RingInjection;

public class RealToComplexInjection extends RingInjection {

	public RealToComplexInjection() {
		super(RealNumbers.getInstance(), ComplexNumbers.getInstance());
	}

	public Set getPreImage(Set orig) {
		ComplexNumber num = (ComplexNumber) orig;
		if (num.getImaginaryPart() != 0)
			return null;
		else
			return new RealNumber(num.getRealPart());
	}

	public Set getImage(Set orig) {
		RealNumber num = (RealNumber) orig;
		return new ComplexNumber(num.getBase(), 0);
	}

}
