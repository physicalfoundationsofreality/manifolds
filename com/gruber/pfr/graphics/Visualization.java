package com.gruber.pfr.graphics;

import com.gruber.pfr.graphics.elements.Coordinates;

public interface Visualization {
	
	public Coordinates getCoordinates();
	
	public void setDisplayParameters(int granularity);
	
	public int getLength();
}
