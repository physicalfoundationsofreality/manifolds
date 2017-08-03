package com.gruber.pfr.graphics.visualizations.realbased;

import java.awt.Color;
import java.util.ArrayList;

public class RealBasedCurve {
	
	Color originColor;
	Color directionColor;
	RealBasedVector startingPoint;
	boolean isCoordinate;
	
	ArrayList<RealBasedVector> curve;
	
	public RealBasedCurve(Color originColor, Color directionColor, RealBasedVector startingPoint) {
		super();
		this.originColor = originColor;
		this.directionColor = directionColor;
		this.startingPoint = startingPoint;
		
		this.refreshCurve();
	}
	public RealBasedCurve(RealBasedVector startingPoint) {
		super();
		this.startingPoint = startingPoint;
		
		this.refreshCurve();
	}
	public RealBasedCurve() {
		super();		
		this.refreshCurve();
	}
	public boolean isCoordinate() {
		return isCoordinate;
	}

	public void setCoordinate(boolean isCoordinate) {
		this.isCoordinate = isCoordinate;
	}

	public void refreshCurve() {
		this.curve = new ArrayList<RealBasedVector>();
	}

	public boolean add(RealBasedVector vector) {
		return this.curve.add(vector);
	}
	
	public RealBasedVector get(int index) {
		return this.curve.get(index);
	}
	
	public ArrayList<RealBasedVector> getCurve() {
		return curve;
	}

	public RealBasedVector getStartingPoint() {
		return this.startingPoint;
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

	public RealBasedVector getLastPoint() {
		
		if(curve.isEmpty())
			return this.startingPoint;
		
		int last = this.curve.size() - 1;
		return this.curve.get(last);
	}
}
