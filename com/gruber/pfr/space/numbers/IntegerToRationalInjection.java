package com.gruber.pfr.space.numbers;

import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.numbers.integer.IntegerNumber;
import com.gruber.pfr.space.numbers.integer.IntegerNumbers;
import com.gruber.pfr.space.numbers.rational.RationalNumber;
import com.gruber.pfr.space.numbers.rational.RationalNumbers;
import com.gruber.pfr.space.numbers.real.RealNumber;
import com.gruber.pfr.space.rings.RingInjection;

public class IntegerToRationalInjection extends RingInjection {


	public IntegerToRationalInjection() {
		super(IntegerNumbers.getInstance(), RationalNumbers.getInstance());
	}

	public Set getPreImage(Set orig) {
		RationalNumber num = (RationalNumber)orig;
		return new RationalNumber(num.getBase());
	}

	public Set getImage(Set orig) {
		IntegerNumber num = (IntegerNumber) orig;
		return new RealNumber(num.getBase());
	}
}
