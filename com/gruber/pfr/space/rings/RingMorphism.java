package com.gruber.pfr.space.rings;

import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.base.map.Map;

public abstract class RingMorphism implements Map {
	
	Ring domain;
	Ring range;
	
	public RingMorphism(Ring domain, Ring range) {
		
		this.domain = domain;
		this.range = range;
	}

	public boolean checkAdditionCompatibility(int noChecks) {
		
		for(int i = 0; i < noChecks; i++) {
			
			RingElement orig1 = (RingElement)this.domain.getRandomElement();
			RingElement orig2 = (RingElement)this.domain.getRandomElement();
			
			RingElement im1 = (RingElement)getImage(orig1);
			RingElement im2 = (RingElement)getImage(orig2);
			
			if(!getImage(orig1.add(orig2)).equals(im1.add(im2)))
				return false;
		}
		return true;
	}

	public boolean checkMultiplicationCompatibility(int noChecks) {
		
		for(int i = 0; i < noChecks; i++) {
			
			RingElement orig1 = (RingElement)this.domain.getRandomElement();
			RingElement orig2 = (RingElement)this.domain.getRandomElement();
			
			RingElement im1 = (RingElement)getImage(orig1);
			RingElement im2 = (RingElement)getImage(orig2);
			
			if(!getImage(orig1.multiply(orig2)).equals(im1.multiply(im2)))
				return false;
		}
		return true;
	}
	
	public Set getDomainBasis() {
		return domain;
	}

	public Set getRangeBasis() {
		return range;
	}
}
