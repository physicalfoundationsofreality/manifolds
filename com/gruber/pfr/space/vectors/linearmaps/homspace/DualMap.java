package com.gruber.pfr.space.vectors.linearmaps.homspace;

import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.rings.RingElement;
import com.gruber.pfr.space.vectors.Vector;
import com.gruber.pfr.space.vectors.basis.VectorSpaceBasis;
import com.gruber.pfr.space.vectors.knspaces.KnSpace;
import com.gruber.pfr.space.vectors.knspaces.KnStandardBasis;
import com.gruber.pfr.space.vectors.linearmaps.LinearMap;

public class DualMap extends LinearMap {

	VectorSpaceBasis basis;
	Vector basisElement;
	KnStandardBasis knBase;
	
	public DualMap(VectorSpaceBasis basis, Vector basisElement) {
		super(basis.getBaseSpace(), KnSpace.getKnSpace(basis.getBaseSpace().getBaseField(), 1));
		this.basis = basis;
		this.basisElement = basisElement;
		this.knBase = KnSpace.getKnSpace(basis.getBaseSpace().getBaseField(), 1).getStandardBasis();
	}

	public Set getImage(Set orig) {

		Vector vec = (Vector)orig;
		RingElement coord = this.basis.getCoordinate(this.basisElement, vec);
		return this.knBase.getBaseVectors()[0].multiply(coord);
	}

	public VectorSpaceBasis getBasis() {
		return basis;
	}

	public Vector getBasisElement() {
		return basisElement;
	}

	public KnStandardBasis getKnBase() {
		return knBase;
	}
	
}
