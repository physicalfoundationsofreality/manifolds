package com.gruber.pfr.graphics.visualizations.phasespace;

import java.awt.Color;

import com.gruber.pfr.space.numbers.real.RealNumber;

public class PhaseSpaceParticleConfiguration {

	Color originColor;
	Color directionColor;
	RealNumber[] startCoordinates;
	RealNumber[] startVelocity;
	float unitStrength;
	float weight;
	
	public PhaseSpaceParticleConfiguration(Color originColor, Color directionColor, RealNumber[] startCoordinates,
			RealNumber[] startVelocity, float unitStrength, float weight) {

		this.originColor = originColor;
		this.directionColor = directionColor;
		this.startCoordinates = startCoordinates;
		this.startVelocity = startVelocity;
		this.unitStrength = unitStrength;
		this.weight = weight;
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
	public RealNumber[] getStartCoordinates() {
		return startCoordinates;
	}
	public RealNumber[] getStartVelocity() {
		return startVelocity;
	}
	public float getUnitStrength() {
		return unitStrength;
	}
}
