package com.gruber.pfr.space.vectors.knspaces;

import java.util.Arrays;
import java.util.Hashtable;

import com.gruber.pfr.space.base.AutoOperation;
import com.gruber.pfr.space.base.Operation.OperantException;
import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.modules.InnerProduct;
import com.gruber.pfr.space.modules.InnerProductSpace;
import com.gruber.pfr.space.modules.ModuleElement;
import com.gruber.pfr.space.modules.ScalarMultiplication;
import com.gruber.pfr.space.rings.Field;
import com.gruber.pfr.space.rings.RingElement;
import com.gruber.pfr.space.vectors.FiniteDimensionalVectorSpace;
import com.gruber.pfr.space.vectors.knspaces.KnVector.InvalidElementsException;

public class KnSpace extends FiniteDimensionalVectorSpace implements InnerProductSpace {

	KnInnerProduct innerProduct;
	
	static Hashtable<Field,KnSpace[]> spaces = new Hashtable<Field,KnSpace[]>();
	
	public static KnSpace getKnSpace(Field baseField, int dim) {
		
		KnSpace[] kn = spaces.get(baseField);
		
		if(kn == null) 
			kn = new KnSpace[dim + 1];

		if(kn.length <= dim)
			kn = Arrays.copyOf(kn, dim + 1);
		
		if(kn[dim] == null)
			kn[dim] = new KnSpace(baseField,dim);
		
			spaces.put(baseField, kn);
			
			return kn[dim];
		
	}

	protected KnSpace(Field baseField, int dim) {

		super(baseField, new KnAddition(), new KnScalarMultiplication(), dim);

		((AutoOperation) this.getAddition()).setBase(this);
		((ScalarMultiplication) this.getMultiplication()).setBase(this);

		this.innerProduct = new KnInnerProduct();
		this.innerProduct.setBase(this);
	}

	public KnVector newVector(RingElement[] elements) throws InvalidElementsException {
		
		return new KnVector(this,elements);
	}
	
	public Set getNullElement() {

		try {
			if (this.getBaseRing().getNullElement() == null)
				return null;

			RingElement[] elements = new RingElement[this.getDim()];
			for (int i = 0; i < elements.length; i++)
				elements[i] = (RingElement) this.getBaseRing().getNullElement();

			return this.newVector(elements);
		} catch (Exception e) {
			return null;
		}
	}

	public KnVector getStandardBasisElement(int position) {

		RingElement[] els = new RingElement[this.getDim()];
		for (int i = 0; i < this.getDim(); i++) {
			if (i == position)
				els[i] = (RingElement) this.baseRing.getOneElement();
			else
				els[i] = (RingElement) this.baseRing.getNullElement();
		}
		try {
			return this.newVector(els);
		} catch (InvalidElementsException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean isElement(Set set) {
		try {
			KnVector vec = (KnVector) set;
			KnSpace space = (KnSpace) vec.getSpace();
			if (space.getDim() == this.getDim() && space.getBaseRing() == this.getBaseRing())
				return true;
		} catch (Exception e) {
		}
		return false;
	}

	public Set getRandomElement() {
		try {
			RingElement[] elements = new RingElement[this.getDim()];

			for (int i = 0; i < this.getDim(); i++) {
				elements[i] = (RingElement) this.getBaseRing().getRandomElement();
			}

			return this.newVector(elements);
		} catch (Exception e) {
			return null;
		}
	}

	public InnerProduct getInnerProduct() {

		return this.innerProduct;
	}

	public RingElement innerProduct(ModuleElement vec1, ModuleElement vec2) {

		try {
			return (RingElement) this.innerProduct.operate(vec1, vec2);
		} catch (OperantException e) {
			e.printStackTrace();
			return null;
		}
	}
	public KnStandardBasis getStandardBasis() {
		
		return new KnStandardBasis(this);
	}
}
