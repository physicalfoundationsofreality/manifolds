package com.gruber.pfr.space.numbers.complex;

import com.gruber.pfr.space.base.AutoOperation;
import com.gruber.pfr.space.base.Set;

public class ComplexMultiplication extends AutoOperation {

	public Set operate(Set op1, Set op2) throws OperantException {

		ComplexNumber c1 = null;
		ComplexNumber c2 = null;
		try {
			c1 = (ComplexNumber) op1;
			c2 = (ComplexNumber) op2;
			return new ComplexNumber(new Double(c1.re * c2.re - c1.im * c2.im).floatValue(),
					new Double(c1.re * c2.im + c1.im * c2.re).floatValue());
		} catch (Exception e) {

			if (c1 == null)
				throw new OperantException(op1);
			else
				throw new OperantException(op2);
		}
	}
}
