package com.gruber.pfr.graphics.visualizations;

import java.util.List;

import com.gruber.pfr.graphics.SimpleCurve;
import com.gruber.pfr.graphics.elements.SimplePoint;
import com.gruber.pfr.graphics.elements.SimpleVector;

public class SimpleNumericCurvesApproximation extends SimpleCurves {

	VectorField field;

	public SimpleNumericCurvesApproximation(List<SimpleCurve> curves, int length, int granularity, int dimension,
			VectorField field) {
		super(curves, length, granularity, dimension);
		this.field = field;
		this.calculateCurves();
	}
	
	public VectorField getField() {
		return this.field;
	}

	public SimpleVector getNextCurveValue(SimpleVector current) {

		float[] newOrigin = new float[this.coord.getDimension()];
		for (int i = 0; i < this.coord.getDimension(); i++)
			newOrigin[i] = current.getOrigin().getCoordinates()[i] + current.getDirection().getCoordinates()[i] / gran;

		SimpleVector newVector = field.getVector(new SimplePoint(newOrigin));

		return newVector;
	}
}
