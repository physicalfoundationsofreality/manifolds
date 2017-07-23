package com.gruber.pfr.space.manifold.realembedded;

import com.gruber.pfr.graphics.visualizations.realbased.RealBasedVisualization;
import com.gruber.pfr.space.manifold.Manifold;

public abstract class RnEmbeddedManifold extends Manifold implements RealBasedVisualization {
	
	int length;
	int granularity;
	
	public  static int numberOfCoordinateCurves = 50;
	public  static int coordinateGranularity = 500;
	
	public RnEmbeddedManifold(int length, int granularity) {
		
		this.granularity = granularity;
		this.length = length;
	}

	public void setDisplayParameters(int granularity) {
		
		this.granularity = granularity;
	}

	public int getLength() {
		return length;
	}
}
