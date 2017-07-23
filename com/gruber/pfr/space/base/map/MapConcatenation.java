package com.gruber.pfr.space.base.map;

import com.gruber.pfr.space.base.Set;

public class MapConcatenation implements Map {
	
	public static class MapIncompatibilityException extends Exception {	}
	
	Map map1;
	Map map2;
	
	protected MapConcatenation(Map map1, Map map2) {
		
		this.map1 = map1;
		this.map2 = map2;
	}

	public static Map concatenateMaps(Map map1, Map map2) throws MapIncompatibilityException {
		
		if(!map1.getRangeBasis().equals(map2.getDomainBasis()))
				throw new MapIncompatibilityException();
		
		return new MapConcatenation(map1, map2);
	}

	public Set getDomainBasis() {
		
		return map1.getDomainBasis();
	}

	public Set getRangeBasis() {
		
		return map2.getRangeBasis();
	}

	public Set getImage(Set orig) {

		return map2.getImage(map1.getImage(orig));
	}

	public Set getPreImage(Set image) {
		return map1.getPreImage(map2.getPreImage(image));
	}
}
