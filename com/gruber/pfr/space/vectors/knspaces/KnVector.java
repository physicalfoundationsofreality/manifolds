package com.gruber.pfr.space.vectors.knspaces;

import com.gruber.pfr.space.rings.RingElement;
import com.gruber.pfr.space.vectors.FiniteDimensionalVector;
import com.gruber.pfr.space.vectors.FiniteDimensionalVectorSpace;

public class KnVector extends FiniteDimensionalVector {
	
	RingElement[] elements;
	
	public class InvalidElementsException extends Exception {};
	
	public KnVector(KnSpace space, RingElement[] elements) throws InvalidElementsException {
		super(space);
		
		if(elements.length != ((FiniteDimensionalVectorSpace)space).getDim())
			throw new InvalidElementsException();
		
		for(int i = 0; i < elements.length; i++) 
			if(!space.getBaseRing().isElement(elements[i]))
				throw new InvalidElementsException();
		
		this.elements = elements;
	}

	public RingElement[] getElements() {
		return elements;
	}

	public void setElements(RingElement[] elements) {
		this.elements = elements;
	}
	public boolean equals(Object obj) {
		
		try {
			KnVector vec = (KnVector)obj;
			for(int i = 0; i < elements.length; i++) 
				if(!elements[i].equals(vec.elements[i]))
					return false;
			
			return true;
			
		} catch(Exception e) {
			return false;
		}
	}
}
