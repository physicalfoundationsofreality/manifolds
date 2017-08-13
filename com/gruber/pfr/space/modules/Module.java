package com.gruber.pfr.space.modules;

import com.gruber.pfr.space.base.AdditiveSpace;
import com.gruber.pfr.space.base.AutoOperation;
import com.gruber.pfr.space.base.Operation;
import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.rings.Ring;
import com.gruber.pfr.space.rings.RingElement;
import com.gruber.pfr.space.vectors.SubSpace;
import com.gruber.pfr.space.vectors.Vector;
import com.gruber.pfr.space.vectors.basis.VectorSpan;

public abstract class Module implements AdditiveSpace, Cloneable{

	protected Ring baseRing;
	ScalarMultiplication multiplication;
	AutoOperation addition;

	protected Module(Ring baseRing,AutoOperation addition,ScalarMultiplication multiplication) {

		this.baseRing = baseRing;
		this.multiplication = multiplication;
		this.addition = addition;
	}

	public Module clone() {
		try {
			return (Module)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
	public ScalarMultiplication getMultiplication() {
		return multiplication;
	}

	public void setBaseRing(Ring baseRing) {
		this.baseRing = baseRing;
	}

	public void setMultiplication(ScalarMultiplication multiplication) {
		this.multiplication = multiplication;
	}

	public void setAddition(AutoOperation addition) {
		this.addition = addition;
	}

	public Ring getBaseRing() {
		return baseRing;
	}
	
	public Set add(Set op1, Set op2) {

		try {
			return this.getAddition().operate(op1, op2);
		} catch (Exception e) {
			return null;
		}
	}

	public Set getNegative(Set element) {
		try {

			RingElement negOne = (RingElement) this.baseRing.getNegative(this.baseRing.getOneElement());
			return this.multiply(negOne, (ModuleElement) element);

		} catch (Exception e) {
			return null;
		}
	}

	public Operation getAddition() {

		return this.addition;
	}

	public ModuleElement multiply(RingElement el, ModuleElement vector) {
		try {

			return (ModuleElement) this.multiplication.operate(el, vector);

		} catch (Exception e) {
			return null;
		}
	}
	public abstract Module getNullSpace();
}
