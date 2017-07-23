package com.gruber.pfr.space.numbers.real;

import com.gruber.pfr.space.base.AutoOperation;
import com.gruber.pfr.space.base.Set;

public class RealMultiplication extends AutoOperation {

	// protected RealMultiplication() {
	//
	// super(RealNumber.class);
	// }

	public Set operate(Set op1, Set op2) throws OperantException {

		RealNumber c1 = null;
		RealNumber c2 = null;
		try {
			c1 = (RealNumber) op1;
			c2 = (RealNumber) op2;
			// we assume precission only up to float precissio
			return new RealNumber(new Double(c1.base * c2.base).floatValue());
		} catch (Exception e) {

			if (c1 == null)
				throw new OperantException(op1);
			else
				throw new OperantException(op2);
		}
	}
}
