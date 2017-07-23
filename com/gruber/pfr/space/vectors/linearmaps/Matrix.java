package com.gruber.pfr.space.vectors.linearmaps;

import com.gruber.pfr.space.rings.Ring;

public interface Matrix {

	public Ring getBaseRing();
	
	public Matrix getTransposed();
	
	public Matrix getInverse();
	
	public Matrix multiply(Matrix rightMultiplicant);
}