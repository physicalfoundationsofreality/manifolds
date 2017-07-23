package com.gruber.pfr.graphics.visualizations;

import com.gruber.pfr.graphics.elements.SimplePoint;
import com.gruber.pfr.graphics.elements.SimpleVector;

public class CircleField2D implements VectorField {

	public SimpleVector getVector(SimplePoint origin) {
		
		SimpleVector vec = new SimpleVector(origin);
		float[] dir = new float[2];
		dir[0] = -origin.getCoordinates()[1];
		dir[1] = origin.getCoordinates()[0];
		SimplePoint direction = new SimplePoint(dir);
		vec.setDirection(direction);
		return vec;
	}

}
