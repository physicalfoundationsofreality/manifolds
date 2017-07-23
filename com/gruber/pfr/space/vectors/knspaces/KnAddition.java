package com.gruber.pfr.space.vectors.knspaces;

import java.util.Arrays;

import com.gruber.pfr.space.base.AutoOperation;
import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.rings.RingElement;

public class KnAddition extends AutoOperation {

	public Set operate(Set op1, Set op2) throws OperantException {
		
		try {
			KnSpace space = (KnSpace)this.getBase();
			KnVector ret = (KnVector)((KnVector)op2).clone();
			
			RingElement[] elements = Arrays.copyOf(ret.getElements(), ret.getElements().length);

			RingElement[] el1 = ((KnVector) op1).getElements();
			RingElement[] el2 = ((KnVector) op2).getElements();

			for (int i = 0; i < space.getDim(); i++) {
				elements[i] = el1[i].add(el2[i]);
			}

			ret.setElements(elements);
			ret.setSpace(space);
			return ret;
			
		} catch (Exception e) {
			throw new OperantException(op1);
		}
	}
}
