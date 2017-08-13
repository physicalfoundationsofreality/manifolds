package com.gruber.pfr.space.vectors.linearmaps;

import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.modules.Module;

public class NullMap extends LinearMap {

	public NullMap(Module domain, Module range) {
		super(domain, range);
	}

	public Set getImage(Set orig) {
		return this.range.getNullElement();
	}

	public Set getPreImage(Set image) {
		try {
			if(image.equals(this.range.getNullElement()))
				return this.getDomainBasis();
			
			return null;
		
		} catch(Exception e) {
			return null;
		}
	}

	public Module getKernel() {
		return this.domain;
	}

	public Module getImage() {
		return this.range.getNullSpace();
	}

	public LinearMap getInverse() {
		return null;
	}

}
