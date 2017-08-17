package com.gruber.pfr.space.vectors.linearmaps.homspace;

import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.rings.RingElement;
import com.gruber.pfr.space.vectors.Vector;
import com.gruber.pfr.space.vectors.VectorSpace;
import com.gruber.pfr.space.vectors.basis.FiniteDimensionalVectorSpaceBasis;
import com.gruber.pfr.space.vectors.basis.VectorSpaceBasis;
import com.gruber.pfr.space.vectors.knspaces.KnSpace;
import com.gruber.pfr.space.vectors.knspaces.KnStandardBasis;
import com.gruber.pfr.space.vectors.linearmaps.LinearMap;

public class DualBasis implements VectorSpaceBasis {

	DualSpace baseSpace;
	VectorSpaceBasis baseBasis;
	KnStandardBasis knBase;

	public DualBasis(VectorSpaceBasis basis) {

		this.baseBasis = basis;
		this.baseSpace = (DualSpace) HomSpace.getHomSpace(basis.getBaseSpace(),
				KnSpace.getKnSpace(basis.getBaseSpace().getBaseField(), 1));
		this.knBase = ((KnSpace) this.baseSpace.range).getStandardBasis();
	}

	public boolean isElement(Set set) {

		try {

			HomVector hom = (HomVector) set;
			LinearMap map = hom.getBaseMap();
			if (map.equals(baseSpace.getNullElement()))
				return false;

			try {
				// for a finite basis a rigorous check is possible
				FiniteDimensionalVectorSpaceBasis finiteBase = (FiniteDimensionalVectorSpaceBasis) this.baseBasis;
				RingElement el = (RingElement) this.knBase.getBaseSpace().getNullElement();
				
				for (int i = 0; i < finiteBase.getBaseVectors().length; i++) {
					
					Vector vec = finiteBase.getBaseVectors()[i];
					RingElement value = this.knBase.getCoordinate(0, vec);
					
					if (!value.equals(this.knBase.getBaseSpace().getBaseField().getNullElement())
							&& !value.equals(this.knBase.getBaseSpace().getBaseField().getOneElement()))
						return false;
					
					el.add(value);
				}
				if (el.equals(this.knBase.getBaseSpace().getBaseField().getOneElement()))
					return true;

			} catch (Exception e) {
// for non-finite just check 100 random elements of basis				
				RingElement el = (RingElement) this.knBase.getBaseSpace().getNullElement();
				for(int i = 0; i < 100; i++) {
					
					Vector vec = (Vector)this.baseBasis.getRandomElement();
					RingElement value = this.knBase.getCoordinate(0, vec);
					
					if (!value.equals(this.knBase.getBaseSpace().getBaseField().getNullElement())
							&& !value.equals(this.knBase.getBaseSpace().getBaseField().getOneElement()))
						return false;
					
					el.add(value);
				}
				if (el.equals(this.knBase.getBaseSpace().getBaseField().getOneElement()))
					return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
// works only for elements in baseBasis
	public HomVector getDualElement(Vector basisElement) {
		return new HomVector(new DualMap(this.baseBasis,basisElement), this.baseSpace);
	}

	public RingElement getCoordinate(Vector baseVector, Vector vector) {
		
		HomVector hom = (HomVector)baseVector;
		HomVector vec = (HomVector)vector;
		
		DualMap map = (DualMap)hom.baseMap;
		vec.getBaseMap().getImage(map.getBasisElement());
		
		return this.knBase.getCoordinate(0, vec);
	}

	public VectorSpace getBaseSpace() {
		return this.baseSpace;
	}

	public void setBaseSpace(VectorSpace space) {
		this.baseSpace = (DualSpace) space;
	}

}
