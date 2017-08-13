package com.gruber.pfr.space.vectors;

import com.gruber.pfr.space.modules.SubModule;

public interface SubSpace extends SubModule {

	default public VectorSpace getBaseSpace() {
		return (VectorSpace) this.getBaseModule();
	}
	
	default public void setBaseSpace(VectorSpace space) {
		this.setBaseModule(space);
	}
}
