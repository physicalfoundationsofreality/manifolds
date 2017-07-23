package com.gruber.pfr.graphics.visualizations;

import java.util.List;

import com.gruber.pfr.graphics.SimpleCurve;
import com.gruber.pfr.graphics.Visualization2D;
import com.gruber.pfr.graphics.elements.Coordinates;
import com.gruber.pfr.graphics.elements.Coordinates2D;

public class SimpleCurves2D implements Visualization2D {

	SimpleCurves curves;
	public SimpleCurves2D(SimpleCurves curves) {
		this.curves = curves;
	}

	public List<SimpleCurve> getCurves() {
		
		return this.curves.getCurves();
	}

	public Coordinates2D getCoordinates() {
		
		Coordinates coord = this.curves.coord;
		return new Coordinates2D(coord.getMin(0),coord.getMax(0),coord.getMin(1),coord.getMax(1));
	}

	public void setDisplayParameters(int granularity) {
		this.curves.setDisplayParameters(granularity);
	}
	public int getLength() {
		return this.curves.getLength();
	}
}
