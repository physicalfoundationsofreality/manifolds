package com.gruber.pfr.space.numbers;

import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.numbers.rational.RationalNumber;
import com.gruber.pfr.space.numbers.rational.RationalNumbers;
import com.gruber.pfr.space.numbers.real.RealNumber;
import com.gruber.pfr.space.numbers.real.RealNumbers;
import com.gruber.pfr.space.rings.RingInjection;

public class RationalToRealInjection extends RingInjection {


	public RationalToRealInjection() {
		super(RationalNumbers.getInstance(), RealNumbers.getInstance());
	}

	public Set getPreImage(Set orig) {
		RealNumber num = (RealNumber)orig;
		return new RationalNumber(num.getBase());
	}

	public Set getImage(Set orig) {
		RationalNumber num = (RationalNumber) orig;
		return new RealNumber(num.getBase());
	}
}
