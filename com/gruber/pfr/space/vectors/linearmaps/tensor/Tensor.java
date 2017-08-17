package com.gruber.pfr.space.vectors.linearmaps.tensor;

import com.gruber.pfr.space.vectors.VectorSpace;
import com.gruber.pfr.space.vectors.knspaces.KnSpace;
import com.gruber.pfr.space.vectors.linearmaps.homspace.DualSpace;

public abstract class Tensor extends MultiLinearMap {

	VectorSpace baseSpace;
	int covariance;
	int contravariance;

	public Tensor(VectorSpace baseSpace, int covariance, int contravariance) {

		super(new CartesianNaryProductOfModules(null), KnSpace.getKnSpace(baseSpace.getBaseField(), 1));
		
		this.covariance = covariance;
		this.contravariance = contravariance;
		this.baseSpace = baseSpace;

		VectorSpace[] baseSpaces = new VectorSpace[this.covariance + this.contravariance];

		for (int i = 0; i < this.covariance; i++)
			baseSpaces[i] = DualSpace.getDualSpace(this.baseSpace);

		for (int i = 0; i < this.contravariance; i++)
			baseSpaces[i] = this.baseSpace;

		this.domain.setBaseModules(baseSpaces);
	}

	public VectorSpace getBaseSpace() {
		return baseSpace;
	}

	public int getCovariance() {
		return covariance;
	}

	public int getContravariance() {
		return contravariance;
	}
}
