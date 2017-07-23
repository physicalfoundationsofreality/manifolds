package com.gruber.pfr.space.numbers.integer;

import com.gruber.pfr.space.base.AutoOperation;
import com.gruber.pfr.space.base.Set;

public class IntegerMultiplication extends AutoOperation {
	
	public Set operate(Set op1, Set op2) throws OperantException {
		
		IntegerNumber c1 = null;
		IntegerNumber c2 = null;
		try{
			c1 = (IntegerNumber)op1;
			c2 = (IntegerNumber)op2;
			return new IntegerNumber(c1.base * c2.base);
		} catch(Exception e) {
			
			if(c1 == null)
				throw new OperantException(op1);
			else
				throw new OperantException(op2);
		}
	}
}
