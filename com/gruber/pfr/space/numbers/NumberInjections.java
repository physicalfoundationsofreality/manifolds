package com.gruber.pfr.space.numbers;

import com.gruber.pfr.space.numbers.complex.ComplexNumber;
import com.gruber.pfr.space.numbers.integer.IntegerNumber;
import com.gruber.pfr.space.numbers.natural.NaturalNumber;
import com.gruber.pfr.space.numbers.rational.RationalNumber;
import com.gruber.pfr.space.numbers.real.RealNumber;

public class NumberInjections {
	
	public static IntegerNumber mapNaturalToInteger(NaturalNumber num) {
		
		return new IntegerNumber(num.getBase());
	}

	public static RationalNumber mapIntegerToRational(IntegerNumber num) {
		
		return new RationalNumber(num.getBase());
	}
	
	public static RealNumber mapRationalToReal(RationalNumber num) {
		
		return new RealNumber(num.getBase());
	}
	
	public static ComplexNumber mapRealToComplex(RealNumber num) {
		
		return new ComplexNumber(num.getBase(),0);
	}
}
