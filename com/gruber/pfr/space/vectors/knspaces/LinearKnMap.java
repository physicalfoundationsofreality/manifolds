package com.gruber.pfr.space.vectors.knspaces;

import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.modules.Module;
import com.gruber.pfr.space.rings.RingElement;
import com.gruber.pfr.space.vectors.linearmaps.LinearMap;

public abstract class LinearKnMap extends LinearMap {
	
	RingElement[][] matrix;
	
	public LinearKnMap(KnSpace domain, KnSpace range, RingElement[][] matrix) {
		super(domain, range);
		this.matrix = matrix;
	}

	public RingElement[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(RingElement[][] matrix) {
		this.matrix = matrix;
	}

	public Set getImage(Set orig) {

		KnVector vec1 = (KnVector)orig;
		RingElement[] els1 = vec1.getElements();
		
		KnSpace range = (KnSpace)this.getRangeBasis();
		KnVector vec2 = (KnVector)range.getNullElement();
		RingElement[] els2 = vec2.getElements();
		
		for(int i = 0; i < els2.length; i++)
			for(int j = 0; j < els1.length; j++) 
				els2[i] = els2[i].add(matrix[i][j].multiply(els1[j]));
		
		return vec2;
	}

	@Override
	public Set getPreImage(Set image) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Module getKernel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Module getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinearMap getInverse() {
		// TODO Auto-generated method stub
		return null;
	}

}
