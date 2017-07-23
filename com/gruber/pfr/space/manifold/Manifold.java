package com.gruber.pfr.space.manifold;

import com.gruber.pfr.space.base.Set;

public abstract class Manifold implements Set {
	
	Atlas atlas;

	public Atlas getAtlas() {
		return atlas;
	}

	public void setAtlas(Atlas atlas) {
		this.atlas = atlas;
	}

}
