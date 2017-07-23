package com.gruber.pfr.space.manifold.realembedded;

import java.util.List;

import com.gruber.pfr.graphics.visualizations.realbased.RealBasedCurve;
import com.gruber.pfr.space.manifold.Chart;

public interface RnEmbeddedChart extends Chart {

	public List<RealBasedCurve> getCoordinates();
}
