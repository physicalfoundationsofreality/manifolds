package com.gruber.pfr.space.manifold.realembedded.impl;

import java.util.List;

import com.gruber.pfr.graphics.visualizations.realbased.RealBasedCurve;
import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.manifold.Atlas;
import com.gruber.pfr.space.manifold.Chart;
import com.gruber.pfr.space.manifold.realembedded.RnEmbeddedChart;
import com.gruber.pfr.space.manifold.realembedded.RnEmbeddedManifold;
import com.gruber.pfr.space.numbers.real.RealNumber;
import com.gruber.pfr.space.numbers.real.RealVector;
import com.gruber.pfr.space.numbers.real.RnSpace;

public class Sphere2Manifold extends RnEmbeddedManifold {
	
// only coordinates
	public Sphere2Manifold(List<Chart> charts, int length, int granularity) {
		
		super(null, length, granularity);
		this.setAtlas(new Atlas(charts));
	}

	// starting point
	public Sphere2Manifold(RealVector startingPoint, List<Chart> charts, int length, int granularity) {
		
		super(startingPoint, length, granularity);
		this.setAtlas(new Atlas(charts));
	}
	
	public List<RealBasedCurve> getCoordinates() {

		RnEmbeddedChart chart = (RnEmbeddedChart)this.getAtlas().getCharts().get(0);
		return  chart.getCoordinates();
	}

	public boolean isElement(Set set) {
		
		RealVector vec = (RealVector) set;
		return (vec.getDim() == 3 && vec.getLength() == 1);
	}

	public Set getRandomElement() {

		RealVector vec = (RealVector)RnSpace.getInstance(3).getRandomElement();
		return vec.multiply(new RealNumber(1/vec.getLength()));
	}
}
