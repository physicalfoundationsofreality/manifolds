package com.gruber.pfr.space.numbers.complex;

import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.rings.RingElement;

public class ComplexNumber extends RingElement {

	double re;
	double im;

	public ComplexNumber(float re, float im) {

		super(ComplexNumbers.getInstance());

		this.re = re;
		this.im = im;
	}

	public float getRealPart() {
		return new Double(re).floatValue();
	}

	public Object clone() {
		
		return new ComplexNumber(new Double(this.re).floatValue(),new Double(this.im).floatValue());
	}
	
	public double getImaginaryPart() {
		return new Double(im).floatValue();
	}

	public boolean isElement(Set set) {
		return false;
	}

	public boolean equals(Object obj) {

		try {
			ComplexNumber num = (ComplexNumber) obj;
			if ((re == num.re) && (im == num.im))
				return true;
		} catch (Exception e) {
		}

		return false;
	}
}
