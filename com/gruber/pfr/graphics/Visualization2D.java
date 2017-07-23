package com.gruber.pfr.graphics;

import java.util.List;

import com.gruber.pfr.graphics.elements.Coordinates2D;
import com.gruber.pfr.graphics.elements.SimpleVector;

public interface Visualization2D {

	public List<SimpleCurve> getCurves();
	
	public Coordinates2D getCoordinates();
	
	public void setDisplayParameters(int granularity);
	
	public int getLength();
}
