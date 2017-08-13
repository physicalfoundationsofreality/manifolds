package com.gruber.pfr.space.vectors.linearmaps.homsapce;

import com.gruber.pfr.space.base.AutoOperation;
import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.vectors.linearmaps.AddedMaps;

public class MapAddition extends AutoOperation {

	public Set operate(Set op1, Set op2) throws OperantException {
		
		HomVector map2 = null;
		HomVector map1 = null;
		try {
			map1 = (HomVector) op1;
		} catch(Exception e) {
			e.printStackTrace();
			throw new OperantException(op1);
		}
		try {
			
			map2 = (HomVector) op2;
		} catch(Exception e) {
			e.printStackTrace();
			throw new OperantException(op2);
		}
		
		if(!map1.getBaseSpace().equals(map2.getBaseSpace()))
			throw new OperantException(op1);
		
		return new HomVector(new AddedMaps(map1.getBaseMap(),map2.getBaseMap()),map1.baseSpace);
	}
}
