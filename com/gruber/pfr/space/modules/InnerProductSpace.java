package com.gruber.pfr.space.modules;

import com.gruber.pfr.space.rings.RingElement;

public interface InnerProductSpace {

	public InnerProduct getInnerProduct();
	
	public RingElement innerProduct(ModuleElement vec1, ModuleElement vec2);
}
