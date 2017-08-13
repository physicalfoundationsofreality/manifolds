package com.gruber.pfr.space.vectors.linearmaps.homsapce;

import java.util.Hashtable;

import com.gruber.pfr.space.base.Operation.OperantException;
import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.rings.RingElement;
import com.gruber.pfr.space.vectors.Vector;
import com.gruber.pfr.space.vectors.VectorSpace;
import com.gruber.pfr.space.vectors.knspaces.KnSpace;
import com.gruber.pfr.space.vectors.linearmaps.LinearMap;
import com.gruber.pfr.space.vectors.linearmaps.NullMap;

public class HomSpace extends VectorSpace {
	
	NullMap nullMap;
	VectorSpace domain;
	VectorSpace range;
	
	static Hashtable<VectorSpace[],HomSpace> spaces = new Hashtable<VectorSpace[],HomSpace>();
	
	public static HomSpace getHomSpace(VectorSpace domain,VectorSpace range) {
		
		if(!domain.getBaseField().equals(range.getBaseField()))
			return null;
		
		VectorSpace[] key = { domain, range };
		HomSpace space = spaces.get(key);
		
		if(space == null) {
			
			if(range.equals(KnSpace.getKnSpace(range.getBaseField(), 1)))
				space = new DualSpace(domain);
			else
				space = new HomSpace(domain,range);
			
			spaces.put(key, space);
		}
		
		return space;
	}
	  
	
	protected HomSpace(VectorSpace domain,VectorSpace range) {
		
		super(domain.getBaseField(),null, null);
		
		this.nullMap = new NullMap(domain,range);
		this.domain = domain;
		this.range = range;
		
		this.setAddition(new MapAddition());
		this.setMultiplication(new MapMultiplication());
	}

	public Set getNullElement() {
		return this.asVector(this.nullMap);
	}
	
	public Vector asVector(LinearMap map) {
		return new HomVector(map, this);
	}

	public boolean isElement(Set set) {
		
		try{
			
			HomVector map = (HomVector) set;
			if(map.getBaseMap().getDomainBasis().equals(this.domain) && map.getBaseMap().getRangeBasis().equals(this.range))
				return true;
			else
				return false;
			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Set getRandomElement() {
		return null;
	}
	
	public HomVector add(HomVector map1, HomVector map2) {
		
		try {
			return (HomVector)this.getAddition().operate(map1, map2);
		} catch (OperantException e) {
			e.printStackTrace();
			return null;
		}
	}
	public HomVector multiply(RingElement scalar, HomVector map) {
		
		try {
			return (HomVector)this.getMultiplication().operate(scalar, map);
		} catch (OperantException e) {
			e.printStackTrace();
			return null;
		}
	}
}
