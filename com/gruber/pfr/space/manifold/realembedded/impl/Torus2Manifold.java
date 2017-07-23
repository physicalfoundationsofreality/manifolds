package com.gruber.pfr.space.manifold.realembedded.impl;

import java.util.ArrayList;
import java.util.List;

import com.gruber.pfr.graphics.elements.Coordinates;
import com.gruber.pfr.graphics.visualizations.realbased.RealBasedCurve;
import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.manifold.Atlas;
import com.gruber.pfr.space.manifold.Chart;
import com.gruber.pfr.space.manifold.realembedded.RnEmbeddedChart;
import com.gruber.pfr.space.manifold.realembedded.RnEmbeddedManifold;
import com.gruber.pfr.space.numbers.real.RealNumber;
import com.gruber.pfr.space.numbers.real.RealVector;

public class Torus2Manifold extends RnEmbeddedManifold {
	
	List<RealBasedCurve> curves;
	
	public Torus2Manifold(List<Chart> charts, int length, int granularity) {
		
		super(length, granularity);
		this.setAtlas(new Atlas(charts));
	}

	public List<RealBasedCurve> getCurves() {
		
		if(curves != null)
			return this.curves;
		
// get the coordinates
		RnEmbeddedChart chart = (RnEmbeddedChart)this.getAtlas().getCharts().get(0);
		List<RealBasedCurve> coordCurves =  chart.getCoordinates();
		
		curves = new ArrayList<RealBasedCurve>();
		curves.addAll(coordCurves);
		
		return curves;
	}

	public boolean isElement(Set set) {
		
		RealVector vec = (RealVector) set;
		RealNumber[] els = vec.getElements();
		float right = ( els[0].getBase() * els[0].getBase() 
				+ els[1].getBase() * els[1].getBase() 
				+ els[2].getBase() * els[2].getBase() + 3 );
		if (!(vec.getDim() == 3 
				&& ( right * right == 16 * (els[0].getBase() * els[0].getBase() 
				+ els[2].getBase() * els[2].getBase()))))
			return false;
		
		return true;
	}

	public Set getRandomElement() {

		return null;
	}

	public Coordinates getCoordinates() {
		return null;
	}
}
