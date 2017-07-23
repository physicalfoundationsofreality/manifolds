package com.gruber.pfr.graphics.visualizations.realbased;

import java.awt.Color;

import com.gruber.pfr.graphics.elements.Coordinates2D;
import com.gruber.pfr.space.vectors.linearmaps.FiniteDimensionalLinearMap;

public class Projection2DConfiguration {

	Color originColor;
	Color directionColor;
	FiniteDimensionalLinearMap projection;
	Coordinates2D coord;
	
	public Projection2DConfiguration(Color originColor, Color directionColor, FiniteDimensionalLinearMap projection, Coordinates2D coord) {
		super();
		this.originColor = originColor;
		this.directionColor = directionColor;
		this.projection = projection;
		this.coord = coord;
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
	public Coordinates2D getCoord() {
		return coord;
	}
	public void setCoord(Coordinates2D coord) {
		this.coord = coord;
	}
	
}
