package com.gruber.pfr.graphics.visualizations;

import com.gruber.pfr.graphics.elements.SimplePoint;
import com.gruber.pfr.graphics.elements.SimpleVector;

public interface VectorField {

	public SimpleVector getVector(SimplePoint origin); 
}
