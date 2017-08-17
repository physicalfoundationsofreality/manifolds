package com.gruber.pfr.space.vectors.linearmaps.homspace;

import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.modules.ScalarMultiplication;
import com.gruber.pfr.space.rings.RingElement;
import com.gruber.pfr.space.vectors.linearmaps.ScaledMap;

public class MapMultiplication extends ScalarMultiplication{

	public Set operate(Set op1, Set op2) throws OperantException {
		
		HomVector map = null;
		RingElement scalar = null;
		try {
			map = (HomVector) op2;
		} catch(Exception e) {
			e.printStackTrace();
			throw new OperantException(op1);
		}
		try {
			
			scalar = (RingElement) op1;
			return new HomVector(new ScaledMap(map.getBaseMap(),scalar),map.baseSpace);
		} catch(Exception e) {
			e.printStackTrace();
			throw new OperantException(op2);
		}
	}
}
