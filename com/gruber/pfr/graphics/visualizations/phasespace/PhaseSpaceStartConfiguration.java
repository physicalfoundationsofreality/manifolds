package com.gruber.pfr.graphics.visualizations.phasespace;

import java.awt.Color;

import com.gruber.pfr.graphics.visualizations.realbased.RealBasedVector;

public class PhaseSpaceStartConfiguration {

	Color originColor;
	Color directionColor;
	RealBasedVector startingPoint;
	RealBasedVector startingVelocity;
	public PhaseSpaceStartConfiguration(Color originColor, Color directionColor, RealBasedVector startingPoint,
			RealBasedVector startingVelocity) {

		this.originColor = originColor;
		this.directionColor = directionColor;
		this.startingPoint = startingPoint;
		this.startingVelocity = startingVelocity;
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
	public RealBasedVector getStartingPoint() {
		return startingPoint;
	}
	public RealBasedVector getStartingVelocity() {
		return startingVelocity;
	}
}
