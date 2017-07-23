package com.gruber.pfr.space.vectors.knspaces;

import java.util.Arrays;

import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.modules.ScalarMultiplication;
import com.gruber.pfr.space.rings.RingElement;

public class KnScalarMultiplication extends ScalarMultiplication {


	public Set operate(Set op1, Set op2) throws OperantException {
		
		try {
			KnSpace space = (KnSpace)this.getBase();
			KnVector ret = (KnVector)((KnVector)op2).clone();
			
			RingElement[] elements = Arrays.copyOf(ret.getElements(), ret.getElements().length);

			RingElement el1 = (RingElement) op1;
			RingElement[] el2 = ((KnVector) op2).getElements();

			for (int i = 0; i < space.getDim(); i++) {
				elements[i] = el1.multiply(el2[i]);
			}

			ret.setElements(elements);
			ret.setSpace(space);
			return ret;
		} catch (Exception e) {
			throw new OperantException(op1);
		}
	}
}
