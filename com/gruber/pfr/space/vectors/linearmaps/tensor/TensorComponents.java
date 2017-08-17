package com.gruber.pfr.space.vectors.linearmaps.tensor;

import com.gruber.pfr.space.rings.Ring;
import com.gruber.pfr.space.rings.RingElement;
import com.gruber.pfr.space.vectors.Vector;
import com.gruber.pfr.space.vectors.VectorSpace;
import com.gruber.pfr.space.vectors.basis.VectorSpaceBasis;

public interface TensorComponents {
	
	public VectorSpaceBasis getBasis();

	public int getCovariance();
	
	public int getContravariance();
	
	default public Ring getBaseField() {
		return this.getBasis().getBaseSpace().getBaseRing();
	}
	
	default public VectorSpace getBaseSpace() {
		return this.getBasis().getBaseSpace();
	}
	
	public RingElement getComponent(Vector[] covariantIndex, Vector[] contravariantIndex);
}
