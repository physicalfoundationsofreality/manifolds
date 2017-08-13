package com.gruber.pfr.space.modules;

import com.gruber.pfr.space.base.Set;

public interface SubModule {

	public Module getBaseModule();

	public void setBaseModule(Module baseModule);

	default public Set getNullElement() {
		return this.getBaseModule().getNullElement();
	}
}