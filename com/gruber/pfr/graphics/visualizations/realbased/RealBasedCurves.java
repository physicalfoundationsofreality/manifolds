package com.gruber.pfr.graphics.visualizations.realbased;

import java.util.List;

import com.gruber.pfr.graphics.elements.Coordinates;

public abstract class RealBasedCurves implements RealBasedVisualization {

	Coordinates coord = null;
	int gran;
	int length;
	List<RealBasedCurve> curves;

	protected RealBasedCurves(List<RealBasedCurve> curves, int length, int granularity, int dimension) {

		this.coord = new Coordinates(new int[dimension], new int[dimension]);
		this.gran = granularity;
		this.curves = curves;
		this.length = length;

		// this.calculateCurves();
	}

	public abstract RealBasedVector getNextCurveValue(RealBasedVector current);

	public void setCurves(List<RealBasedCurve> curves) {
		this.curves = curves;
		// this.calculateCurves();
	}

	public int getLength() {
		return this.length;
	}

	public List<RealBasedCurve> getCurves() {
		return this.curves;
	}

	protected void calculateCurves() {

		if (gran == 0 || curves == null || curves.size() == 0)
			return;
		else {

			float inc = 1 / new Float(gran).floatValue();

			for (int i = 0; i < this.coord.getDimension(); i++) {
				coord.setMax(curves.get(0).getStartingPoint().getMaxInt()[i], i);
				coord.setMin(curves.get(0).getStartingPoint().getMinInt()[i], i);
			}

			for (int i = 0; i < curves.size(); i++) {

				for (int j = 0; j < this.coord.getDimension(); j++) {

					if (curves.get(i).getStartingPoint().getMinInt()[j] < coord.getMin(j))
						coord.setMin(curves.get(i).getStartingPoint().getMinInt()[j], j);

					if (curves.get(i).getStartingPoint().getMaxInt()[j] > coord.getMax(j))
						coord.setMax(curves.get(i).getStartingPoint().getMaxInt()[j], j);
				}

				// curves.get(i).refreshCurve();

				RealBasedVector current = curves.get(i).getStartingPoint();
				curves.get(i).add(current);

				for (float r = 0; r < length; r += inc) {

					current = this.getNextCurveValue(current);

					for (int j = 0; j < this.coord.getDimension(); j++) {

						if (current.getMinInt()[j] < coord.getMin(j))
							coord.setMin(current.getMinInt()[j], j);

						if (current.getMaxInt()[j] > coord.getMax(j))
							coord.setMax(current.getMaxInt()[j], j);
					}

					curves.get(i).add(current);
				}
			}
		}
	}

	public Coordinates getCoordinates() {
		return coord;
	}

	public void setDisplayParameters(int granularity) {
		this.gran = granularity;
	}
}
