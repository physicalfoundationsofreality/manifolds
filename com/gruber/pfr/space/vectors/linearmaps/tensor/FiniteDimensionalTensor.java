package com.gruber.pfr.space.vectors.linearmaps.tensor;

import java.util.Arrays;

import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.base.Tupel;
import com.gruber.pfr.space.rings.Field;
import com.gruber.pfr.space.rings.RingElement;
import com.gruber.pfr.space.vectors.FiniteDimensionalVectorSpace;
import com.gruber.pfr.space.vectors.Vector;
import com.gruber.pfr.space.vectors.basis.FiniteDimensionalVectorSpaceBasis;
import com.gruber.pfr.space.vectors.knspaces.KnSpace;
import com.gruber.pfr.space.vectors.knspaces.KnVector;
import com.gruber.pfr.space.vectors.linearmaps.homspace.DualBasis;
import com.gruber.pfr.space.vectors.linearmaps.homspace.DualSpace;

public class FiniteDimensionalTensor extends Tensor {

	TensorComponents components;
	DualBasis dualBasis;
	FiniteDimensionalVectorSpaceBasis basis;

	public FiniteDimensionalTensor(TensorComponents components) {

		super(components.getBaseSpace(), components.getCovariance(), components.getContravariance());

		this.components = components;
		this.basis = (FiniteDimensionalVectorSpaceBasis)this.components.getBasis();
		this.dualBasis =  new DualBasis(this.basis);
	}

	public FiniteDimensionalVectorSpace getBaseSpace() {
		return (FiniteDimensionalVectorSpace) baseSpace;
	}

	public Set getImage(Set orig) {

		try {
			Tupel tupel = (Tupel) orig;
			int n = this.getCovariance() + this.getContravariance();

			RingElement[] factor = { getFactor(tupel, n, new int[0]) };

			Vector result = new KnVector(KnSpace.getKnSpace((Field) this.getBaseSpace().getBaseField(), 1), factor);
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private RingElement getFactor(Tupel tupel, int n, int[] is) {

		RingElement factor = (RingElement) this.getBaseSpace().getBaseField().getOneElement();
		DualSpace dual = DualSpace.getDualSpace(this.baseSpace);

		
		if (n > 0) 
			for (int i = 0; i < this.getBaseSpace().getDim(); i++) {
				int[] ints = Arrays.copyOf(is, is.length + 1);
				ints[is.length] = i;
				factor.add(getFactor(tupel,n-1,ints));
			}
		else {
			Vector[] cov = new Vector[this.covariance];
			Vector[] cont = new Vector[this.contravariance];

			for(int i = 0; i < this.covariance + this.contravariance; i++) {
				
				if(i < this.covariance) {
					
					Vector vec = (Vector)tupel.getConstituents()[i];
					Vector baseVector = this.basis.getBaseVectors()[is[i]];
					RingElement coord = this.dualBasis.getCoordinate(baseVector, vec);
					factor = factor.multiply(coord);
					
					cov[i] = this.dualBasis.getDualElement(baseVector);
				} else {
					
					Vector vec = (Vector)tupel.getConstituents()[i];
					Vector baseVector = this.basis.getBaseVectors()[is[i]];
					RingElement coord = this.components.getBasis().getCoordinate(baseVector, vec);
					factor = factor.multiply(coord);
					
					cont[i - this.covariance] = baseVector;
				}
				
				RingElement comp = this.components.getComponent(cov, cont);
				factor = factor.multiply(comp);
			}
		}
		return factor;
	}
}
