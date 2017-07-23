package com.gruber.pfr.space.rings;

import com.gruber.pfr.space.base.Set;

public class RingElement implements Set, Cloneable {

	Ring ring;

	public RingElement(Ring ring) {
		this.ring = ring;
	}
	
	public Object clone() {

		return new RingElement(this.ring);
	}

	public Ring getRing() {
		return ring;
	}

	public boolean isElement(Set set) {
		return false;
	}
	
	public Set getRandomElement() {
		return null;
	}
	
	public RingElement add(RingElement el) {
		
		return (RingElement)this.ring.add(this, el);
	}
	
	public RingElement multiply(RingElement el) {
		
		return (RingElement)this.ring.multiply(this, el);
	}
	
	public RingElement getNegative() {
		
		return (RingElement)this.ring.getNegative(this);
	}
	
	public RingElement getInverse() {
		
		return (RingElement)this.ring.getInverse(this);
	}
}
