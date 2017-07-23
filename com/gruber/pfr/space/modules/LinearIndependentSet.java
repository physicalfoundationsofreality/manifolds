package com.gruber.pfr.space.modules;

import com.gruber.pfr.space.base.Set;

public interface LinearIndependentSet extends Set {

	public Module getBaseSpace();

	public void setBaseSpace(Module space);

}

