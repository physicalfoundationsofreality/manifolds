package com.gruber.pfr.space.modules;

import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.modules.Module;
import com.gruber.pfr.space.rings.RingElement;

public abstract class ModuleElement implements Set, Cloneable {
	
	Module space;
	
	public ModuleElement clone() {
		
		try {
			return (ModuleElement)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ModuleElement(Module space) {
		this.space = space;
	}

	public boolean isElement(Set set) {
		return false;
	}
	
	public Set getRandomElement() {
		return null;
	}
	
	public ModuleElement add(ModuleElement el) {
		
		return (ModuleElement)this.space.add(this, el);
	}
	public ModuleElement multiply(RingElement scalar) {
		
		return this.space.multiply(scalar, this);
	}

	public Module getSpace() {
		return space;
	}

	public void setSpace(Module space) {
		this.space = space;
	}
}
