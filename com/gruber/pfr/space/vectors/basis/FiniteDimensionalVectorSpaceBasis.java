package com.gruber.pfr.space.vectors.basis;

import java.util.Random;

import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.rings.RingElement;
import com.gruber.pfr.space.vectors.FiniteDimensionalVector;
import com.gruber.pfr.space.vectors.FiniteDimensionalVectorSpace;
import com.gruber.pfr.space.vectors.Vector;
import com.gruber.pfr.space.vectors.VectorSpace;

public abstract class FiniteDimensionalVectorSpaceBasis implements VectorSpaceBasis {

	FiniteDimensionalVector[] baseVectors;
	FiniteDimensionalVectorSpace space;
	Random rand = new Random(System.currentTimeMillis());

	public FiniteDimensionalVectorSpaceBasis(FiniteDimensionalVectorSpace space,
			FiniteDimensionalVector[] baseVectors) {

		this.space = space;
		this.baseVectors = baseVectors;
	}

	public FiniteDimensionalVector[] getBaseVectors() {
		return baseVectors;
	}

	public RingElement[] getCoordinates(Vector vector) {

		RingElement[] els = new RingElement[baseVectors.length];
		for (int i = 0; i < els.length; i++)
			els[i] = this.getCoordinate(i, vector);

		return els;
	}

	public void setBaseVectors(FiniteDimensionalVector[] baseVectors) {
		this.baseVectors = baseVectors;
	}

	public abstract RingElement getCoordinate(Vector baseVector, Vector vector);

	public RingElement getCoordinate(int position, Vector vector) {
		return this.getCoordinate(baseVectors[position], vector);
	}

	public VectorSpace getBaseSpace() {
		return this.space;
	}

	public void setBaseSpace(VectorSpace space) {

		this.space = (FiniteDimensionalVectorSpace) space;
	}

	public boolean isElement(Set set) {

		boolean isEl = false;

		for (int i = 0; i < this.baseVectors.length; i++)
			if (this.baseVectors[i].equals((Vector) set))
				return true;

		return isEl;
	}

	public Set getRandomElement() {

		return this.baseVectors[rand.nextInt(this.space.getDim())];
	}

	// Resort by exchanging two basis elements
	public void resortBasis(int i, int j) {

		FiniteDimensionalVector veci = this.baseVectors[i];
		this.baseVectors[i] = this.baseVectors[j];
		this.baseVectors[j] = veci;
	}

	// scale and add vector i with vector j, i not j
	public void modifyVector(int i, int j, RingElement scalar) {

		if (i == j && scalar.equals(scalar.getRing().getNegative(scalar.getRing().getOneElement())))
			return;

		this.baseVectors[i] = (FiniteDimensionalVector) this.baseVectors[i].add(baseVectors[j].multiply(scalar));
	}
}
