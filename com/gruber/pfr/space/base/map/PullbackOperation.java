package com.gruber.pfr.space.base.map;

import com.gruber.pfr.space.base.Operation;
import com.gruber.pfr.space.base.Set;

public class PullbackOperation implements Operation {

	Map map;
	Map map1;
	Map map2;
	Operation op;

	public PullbackOperation(Map operant1Map, Map operant2Map, Map rangeMap, Operation op) {
		
		this.map1 = operant1Map;
		this.map2 = operant2Map;
		this.map = rangeMap;
		this.op = op;
	}

	public Set getFirstOperantDomain() {
		
		return map1.getDomainBasis();
	}

	public Set getSecondOperantDomain() {
		
		return map2.getDomainBasis();
	}

	public Set operate(Set op1, Set op2) throws OperantException {

		if(!map1.getDomainBasis().isElement(op1))
			throw new OperantException(op1);
		
		if(!map2.getDomainBasis().isElement(op2))
			throw new OperantException(op2);
		
		return map.getPreImage(this.op.operate(map1.getImage(op1), map2.getImage(op2)));
	}

	public Set getOperationRange() {

		return map.getDomainBasis();
	}
}
