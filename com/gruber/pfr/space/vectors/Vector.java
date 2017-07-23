package com.gruber.pfr.space.vectors;

import com.gruber.pfr.space.modules.ModuleElement;
import com.gruber.pfr.space.rings.RingElement;

public abstract class Vector extends ModuleElement {

	public Vector clone() {

		return (Vector) super.clone();
	}

	public Vector(VectorSpace space) {
		
		super(space);
	}

	public Vector add(Vector el) {

		return (Vector) this.getSpace().add(this, el);
	}

	public Vector multiply(RingElement scalar) {

		return (Vector)this.getSpace().multiply(scalar, this);
	}
}
