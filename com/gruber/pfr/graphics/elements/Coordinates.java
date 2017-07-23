package com.gruber.pfr.graphics.elements;

public class Coordinates {

	int[] min;
	int[] max;
	
	public Coordinates(int min[], int max[]) {
		
		this.min = min;
		this.max = max;
	}
	public int getMin(int i) {
		return min[i];
	}
	public void setMin(int min, int i) {
		this.min[i] = min;
	}
	public int getMax(int i) {
		return max[i];
	}
	public void setMax(int max, int i) {
		this.max[i] = max;
	}	
	public int getDimension() {
		return min.length;
	}
	public int[] getMin() {
		return min;
	}
	public int[] getMax() {
		return max;
	}
}
