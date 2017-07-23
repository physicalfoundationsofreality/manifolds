package com.gruber.pfr.space.rings;

import java.util.ArrayList;
import java.util.List;

import com.gruber.pfr.space.base.AdditiveSpace;
import com.gruber.pfr.space.base.AutoOperation;
import com.gruber.pfr.space.base.MultiplicativeSpace;
import com.gruber.pfr.space.base.Operation;
import com.gruber.pfr.space.base.Set;

public abstract class Ring implements AdditiveSpace, MultiplicativeSpace {

	AutoOperation addition;
	AutoOperation multiplication;

	protected Ring(AutoOperation addition, AutoOperation multiplication) {

		this.addition = addition;
		this.multiplication = multiplication;

		this.addition.setBase(this);
		this.multiplication.setBase(this);
	}

	static Ring ring;

	public Operation getMultiplication() {

		return this.multiplication;
	}

	public RingElement multiply(RingElement op1, RingElement op2) {

		try {
			return (RingElement) this.multiplication.operate(op1, op2);
		} catch (Exception e) {
			return null;
		}
	}

	public Operation getAddition() {

		return this.addition;
	}

	public RingElement add(RingElement op1, RingElement op2) {

		try {
			return (RingElement) this.addition.operate(op1, op2);
		} catch (Exception e) {
			return null;
		}
	}

	public Set multiply(Set op1, Set op2) {

		return this.multiply((RingElement) op1, (RingElement) op2);
	}

	public Set add(Set op1, Set op2) {

		return this.add((RingElement) op1, (RingElement) op2);
	}

	public List<Operation> getOperations() {

		ArrayList<Operation> list = new ArrayList<Operation>();
		list.add(this.addition);
		list.add(this.multiplication);

		return list;
	}
}
