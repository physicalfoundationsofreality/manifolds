package com.gruber.pfr.space.vectors.knspaces;

import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.modules.InnerProduct;
import com.gruber.pfr.space.rings.RingElement;

public class KnInnerProduct extends InnerProduct {

	public Set operate(Set op1, Set op2) throws OperantException {

		RingElement length = (RingElement)this.getBase().getBaseRing().getNullElement();
		KnSpace space = (KnSpace)this.getBase();
		KnVector vec1 = (KnVector)op1;
		KnVector vec2 = (KnVector)op2;
		
		for(int i = 0; i < space.getDim(); i++)
			length = length.add(vec1.elements[i].multiply(vec2.elements[i]));
		
		return length;
	}
}
