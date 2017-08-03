package com.gruber.pfr.graphics;

import java.awt.Color;
import java.util.ArrayList;

import com.gruber.pfr.graphics.elements.SimpleVector;

public class SimpleCurve {

	Color originColor;
	Color directionColor;
	SimpleVector startingPoint;
	int position;
	GraphWizard.FunctionType functionType;

	ArrayList<SimpleVector> curve = new ArrayList<SimpleVector>();

	public SimpleCurve(Color originColor, Color directionColor, GraphWizard.FunctionType functionType) {

		super();
		this.originColor = originColor;
		this.directionColor = directionColor;
		this.position = 0;

		this.functionType = functionType;
	}

	public SimpleCurve(Color originColor, Color directionColor, SimpleVector startingPoint,
			GraphWizard.FunctionType functionType) {

		super();
		this.originColor = originColor;
		this.directionColor = directionColor;
		this.startingPoint = startingPoint;
		this.position = -1;

		this.functionType = functionType;
	}

	public GraphWizard.FunctionType getFunctionType() {
		return functionType;
	}

	public void setFunctionType(GraphWizard.FunctionType functionType) {
		this.functionType = functionType;
	}

	public SimpleVector getStartingPoint() {
		return startingPoint;
	}

	public boolean add(SimpleVector vector) {
		return this.curve.add(vector);
	}

	public boolean hasNext() {

		if (this.position < this.curve.size())
			return true;
		else
			return false;
	}

	public SimpleVector get(int index) {
		if (index < 0)
			return this.startingPoint;
		else
			return this.curve.get(index);
	}

	public SimpleVector getNext() {

		return this.get(this.position++);
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

	public ArrayList<SimpleVector> getVectors() {
		return curve;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getLength() {
		return this.curve.size();
	}
}
