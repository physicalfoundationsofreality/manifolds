package com.gruber.pfr.space.vectors.basis;

import com.gruber.pfr.space.base.AutoOperation;
import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.modules.Module;
import com.gruber.pfr.space.rings.Field;
import com.gruber.pfr.space.vectors.FiniteDimensionalVector;
import com.gruber.pfr.space.vectors.FiniteDimensionalVectorSpace;
import com.gruber.pfr.space.vectors.SubSpace;
import com.gruber.pfr.space.vectors.Vector;
import com.gruber.pfr.space.vectors.VectorSpace;

public class VectorSpan extends FiniteDimensionalVectorSpace implements SubSpace {

	FiniteDimensionalVectorSpaceBasis basis;
	VectorSpace baseSpace;

	public VectorSpan(Vector[] baseVectors, VectorSpace baseSpace) {

		super((Field) baseVectors[0].getSpace().getBaseRing(), (AutoOperation) baseSpace.getAddition(),
				baseSpace.getMultiplication(), baseVectors.length);
		this.baseSpace = baseSpace;
		this.basis = new DerivedFiniteVectorSpaceBasis(baseVectors, this);
	}

	public Set getNullElement() {
		return this.baseSpace.getNullElement();
	}

	public VectorSpaceBasis getStandardBasis() {
		return this.basis;
	}

	public void setStandardBasis(VectorSpaceBasis standardBasis) {
		this.basis = (FiniteDimensionalVectorSpaceBasis) standardBasis;
	}

	public boolean isElement(Set set) {

		Vector vector = (Vector) set;
		Vector vec = (Vector) this.getNullElement();
		for (int i = 0; i < this.getDim(); i++) {
			vec.add(this.basis.getBaseVectors()[i].multiply(this.basis.getCoordinate(i, vector)));
		}

		return vector.equals(vec);
	}

	public Set getRandomElement() {

		return this.basis.getRandomElement();
	}

	public FiniteDimensionalVectorSpaceBasis getBasis() {
		return basis;
	}

	public void setBasis(FiniteDimensionalVectorSpaceBasis basis) {
		this.basis = basis;
	}

	public void setBaseModule(Module baseSpace) {
		this.baseSpace = (VectorSpace)baseSpace;
	}

	public Module getBaseModule() {
		return this.baseSpace;
	}
}