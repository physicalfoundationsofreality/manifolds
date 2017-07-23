package com.gruber.pfr.graphics.visualizations.realbased;

import java.util.List;

import com.gruber.pfr.graphics.Visualization;

public interface RealBasedVisualization extends Visualization {

	List<RealBasedCurve> getCurves();
}
