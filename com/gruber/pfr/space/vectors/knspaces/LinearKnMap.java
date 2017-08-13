package com.gruber.pfr.space.vectors.knspaces;

import com.gruber.pfr.space.vectors.linearmaps.FiniteDimensionalLinearMap;
import com.gruber.pfr.space.vectors.linearmaps.FiniteMatrix;

public abstract class LinearKnMap extends FiniteDimensionalLinearMap {
	
	public LinearKnMap(KnSpace domain, KnSpace range, FiniteMatrix matrix) {
		super(domain.getStandardBasis(), range.getStandardBasis(), matrix);
	}
}
