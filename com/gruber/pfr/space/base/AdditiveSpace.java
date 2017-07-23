package com.gruber.pfr.space.base;

public interface AdditiveSpace extends Set {

	public Set getNullElement();
	
	public Set getNegative(Set element);
	
	public Operation getAddition();

	public Set add(Set op1, Set op2);
}
