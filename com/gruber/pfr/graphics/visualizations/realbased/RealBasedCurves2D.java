package com.gruber.pfr.graphics.visualizations.realbased;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.gruber.pfr.graphics.GraphWizard;
import com.gruber.pfr.graphics.SimpleCurve;
import com.gruber.pfr.graphics.Visualization2D;
import com.gruber.pfr.graphics.elements.Coordinates;
import com.gruber.pfr.graphics.elements.Coordinates2D;

public class RealBasedCurves2D implements Visualization2D {

	RealBasedVisualization curves;
	GraphWizard.FunctionType functionType;

	public RealBasedCurves2D(RealBasedVisualization curves, GraphWizard.FunctionType functionType) {
		this.curves = curves;
		this.functionType = functionType;
	}

	public List<SimpleCurve> getCurves() {

		List<RealBasedCurve> realCurves = this.curves.getCurves();
		List<SimpleCurve> curves = new ArrayList<SimpleCurve>();

		Iterator<RealBasedCurve> curvesIter = realCurves.iterator();
		while (curvesIter.hasNext()) {

			RealBasedCurve realCurve = curvesIter.next();
			SimpleCurve curve = new SimpleCurve(realCurve.getOriginColor(), realCurve.getDirectionColor(),
					realCurve.getStartingPoint().asVector(), this.functionType);

			Iterator<RealBasedVector> curveIter = realCurve.getCurve().iterator();
			while (curveIter.hasNext())
				curve.add(curveIter.next().asVector());

			curves.add(curve);
		}
		return curves;
	}

	public int getLength() {
		return this.curves.getLength();
	}
	
	public Coordinates2D getCoordinates() {

		Coordinates coord = this.curves.getCoordinates();
		return new Coordinates2D(coord.getMin(0), coord.getMax(0), coord.getMin(1), coord.getMax(1));
	}

	public void setDisplayParameters(int granularity) {
		this.curves.setDisplayParameters(granularity);
	}

}
