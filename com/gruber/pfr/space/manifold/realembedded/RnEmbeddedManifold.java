package com.gruber.pfr.space.manifold.realembedded;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.gruber.pfr.graphics.visualizations.realbased.RealBasedCurve;
import com.gruber.pfr.graphics.visualizations.realbased.RealBasedVector;
import com.gruber.pfr.graphics.visualizations.realbased.RealBasedVisualization;
import com.gruber.pfr.space.manifold.Chart;
import com.gruber.pfr.space.manifold.Manifold;
import com.gruber.pfr.space.numbers.real.RealVector;

public abstract class RnEmbeddedManifold extends Manifold implements RealBasedVisualization {

	int length;
	int granularity;
	List<RealBasedCurve> curves;
	RealVector startingPoint;

	public static int numberOfCoordinateCurves = 50;
	public static int coordinateGranularity = 500;

	public RnEmbeddedManifold(RealVector startingPoint, int length, int granularity) {

		this.length = length;
		this.startingPoint = startingPoint;
		this.granularity = granularity;
	}

	public void setDisplayParameters(int granularity) {

		this.granularity = granularity;
	}

	public int getLength() {
		return length;
	}

	public abstract List<RealBasedCurve> getCoordinates();

	public List<RealBasedCurve> getCurves() {

		if (curves != null)
			return this.curves;

		this.curves = new ArrayList<RealBasedCurve>();

		// get the coordinates
		List<RealBasedCurve> coordCurves = this.getCoordinates();
		this.curves.addAll(coordCurves);

		if (this.startingPoint == null || this.granularity == 0)
			return curves;

		RealVector current = this.startingPoint;
		RealBasedCurve curve = new RealBasedCurve(new RealBasedVector(current));
		RnEmbeddedChart currentChart = null;
		
		int size =   this.length * this.granularity;

		while(curve.getCurve().size() < size) {
			
			current = curve.getLastPoint().getOrigin();
			Iterator<Chart> charts = this.getAtlas().getCoveringCharts(current).iterator();
			while(charts.hasNext()) {
				if(curve.getCurve().size() > size)
					break;
				
				RnEmbeddedChart chart = (RnEmbeddedChart)charts.next();
				chart.extendCurve(curve, this.granularity, this.length);
			}
		}
		
		curves.add(curve);

		return curves;
	}
	private Chart getCoveringChart(RealVector point, Chart current) {
		
		if(current != null)
			if(current.isInDomain(point))
				return current;
		
		return this.getAtlas().getCoveringCharts(point).get(0);
	}
}
