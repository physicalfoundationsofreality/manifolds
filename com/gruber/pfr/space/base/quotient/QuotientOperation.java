package com.gruber.pfr.space.base.quotient;

import com.gruber.pfr.space.base.AutoOperation;
import com.gruber.pfr.space.base.Operation;
import com.gruber.pfr.space.base.Set;

public class QuotientOperation extends AutoOperation {

	Operation baseOperation;
	QuotientProjection projection;

	public QuotientOperation(QuotientProjection projection, Operation baseOperation) {
		
		this.setBase(projection.getDomainBasis());
		this.baseOperation = baseOperation;
		this.projection = projection;
	}

	public Set operate(Set op1, Set op2) throws OperantException {

		EquivalenceClass cl1 = (EquivalenceClass)op1;
		EquivalenceClass cl2 = (EquivalenceClass)op2;
		
		Set image = this.baseOperation.operate(cl1.getBaseElement(), cl2.getBaseElement());
		
		return this.projection.getImage(image);
	}
}
