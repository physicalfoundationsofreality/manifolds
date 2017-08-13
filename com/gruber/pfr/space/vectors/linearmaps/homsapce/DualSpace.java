package com.gruber.pfr.space.vectors.linearmaps.homsapce;

import com.gruber.pfr.space.vectors.VectorSpace;
import com.gruber.pfr.space.vectors.basis.VectorSpaceBasis;
import com.gruber.pfr.space.vectors.knspaces.KnSpace;

public class DualSpace extends HomSpace {
	
	protected DualSpace(VectorSpace domain) {
		
		super(domain,KnSpace.getKnSpace(domain.getBaseField(), 1));
	}
	
	public static DualSpace getDualSpace(VectorSpace domain) {
		
		return (DualSpace)HomSpace.getHomSpace(domain, KnSpace.getKnSpace(domain.getBaseField(), 1));
	}
	
	public VectorSpaceBasis getDualBasis(VectorSpaceBasis basis) {
		return null;
	}
}
