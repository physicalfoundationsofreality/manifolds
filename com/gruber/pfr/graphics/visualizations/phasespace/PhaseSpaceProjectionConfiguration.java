package com.gruber.pfr.graphics.visualizations.phasespace;

import java.awt.Color;

import com.gruber.pfr.space.vectors.linearmaps.FiniteDimensionalLinearMap;

public class PhaseSpaceProjectionConfiguration {

	Color originColor;
	Color directionColor;
	FiniteDimensionalLinearMap projection;
	
	public PhaseSpaceProjectionConfiguration(Color originColor, Color directionColor, FiniteDimensionalLinearMap projection) {

		this.originColor = originColor;
		this.directionColor = directionColor;
		this.projection = projection;
	}
	public Color getOriginColor() {
		return originColor;
	}
	public void setOriginColor(Color originColor) {
		this.originColor = originColor;
	}
	public Color getDirectionColor() {
		return directionColor;
	}
	public void setDirectionColor(Color directionColor) {
		this.directionColor = directionColor;
	}
	public FiniteDimensionalLinearMap getProjection() {
		return projection;
	}
	public void setProjection(FiniteDimensionalLinearMap projection) {
		this.projection = projection;
	}
}
