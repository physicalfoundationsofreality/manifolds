package com.gruber.pfr.space.numbers.real;

import com.gruber.pfr.space.rings.RingElement;
import com.gruber.pfr.space.vectors.Vector;
import com.gruber.pfr.space.vectors.knspaces.KnVector;

public class RealVector extends KnVector {

	public RealVector(RealNumber[] elements) throws InvalidElementsException {
		super(RnSpace.getInstance(elements.length), elements);
	}

	public RealVector add(RealVector el) {

		return (RealVector) this.add((Vector) el);
	}

	public RealVector multiply(RealNumber scalar) {

		return (RealVector) this.multiply((RingElement)scalar);
	}
	
	public RealNumber[] getElements() {

		return (RealNumber[]) super.getElements();
	}
	public RnSpace getSpace() {
		return (RnSpace) super.getSpace();
	}
	public float getLength() {
		
		return new Double(Math.sqrt(this.innerProduct(this).base)).floatValue();
	}
	public RealNumber innerProduct(RealVector vector) {

		return this.getSpace().innerProduct(vector, vector);
	}
	public RealVector getNegative() {
		return this.multiply(((RealNumber)RealNumbers.getInstance().getOneElement()).getNegative());
	}
}
