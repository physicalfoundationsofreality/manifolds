package com.gruber.pfr.space.base.quotient;

import com.gruber.pfr.space.base.AdditiveSpace;
import com.gruber.pfr.space.base.MultiplicativeSpace;
import com.gruber.pfr.space.base.Operation;
import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.rings.Ring;
import com.gruber.pfr.space.rings.RingElement;

public class QuotientRing extends QuotientSpace implements AdditiveSpace, MultiplicativeSpace {

	Ring base;

	public QuotientRing(Set base, EquivalenceRelation relat) {
		super(base, relat);
		this.base = (Ring)base;
	}

	public Set getOneElement() {

		return this.base.getOneElement();
	}

	public Set getInverse(Set element) {

		return this.getRelation().getEquivalenceClass(base.getInverse(((EquivalenceClass)element).getBaseElement()));
	}

	public Operation getMultiplication() {
		
		return new QuotientOperation(this.getProjection(), this.base.getMultiplication());
	}

	public Set multiply(Set op1, Set op2) {
		RingElement el1 = (RingElement)((EquivalenceClass)op1).getBaseElement();
		RingElement el2 = (RingElement)((EquivalenceClass)op2).getBaseElement();
		RingElement el = base.multiply(el1, el2);
		
		return this.getRelation().getEquivalenceClass(el);
	}

	public Set getNullElement() {

		return this.getRelation().getEquivalenceClass(base.getNullElement());
	}

	public Set getNegative(Set element) {
		
		return this.getRelation().getEquivalenceClass(base.getNegative(((EquivalenceClass)element).getBaseElement()));
	}

	public Operation getAddition() {
		
		return new QuotientOperation(this.getProjection(), this.base.getAddition());
	}

	public Set add(Set op1, Set op2) {
		RingElement el1 = (RingElement)((EquivalenceClass)op1).getBaseElement();
		RingElement el2 = (RingElement)((EquivalenceClass)op2).getBaseElement();
		RingElement el = base.add(el1, el2);
		
		return this.getRelation().getEquivalenceClass(el);
	}
}
