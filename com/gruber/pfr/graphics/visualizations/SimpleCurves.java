package com.gruber.pfr.graphics.visualizations;

import java.util.List;

import com.gruber.pfr.graphics.SimpleCurve;
import com.gruber.pfr.graphics.Visualization;
import com.gruber.pfr.graphics.elements.Coordinates;
import com.gruber.pfr.graphics.elements.SimpleVector;

public abstract class SimpleCurves implements Visualization {

	Coordinates coord = null;
	int gran;
	int length;
	List<SimpleCurve> curves;

	protected SimpleCurves(List<SimpleCurve> curves, int length, int granularity, int dimension) {

		this.coord = new Coordinates(new int[dimension], new int[dimension]);
		this.gran = granularity;
		this.length = length;
		this.curves = curves;

//		this.calculateCurves();
	}

	public void setCurves(List<SimpleCurve> curves) {
		this.curves = curves;
		this.calculateCurves();
	}

	public List<SimpleCurve> getCurves() {
		return this.curves;
	}
	
	public int getLength() {
		return this.length;
	}

	public abstract SimpleVector getNextCurveValue(SimpleVector current);

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

//				this.curves.get(i).refreshCurve();

				SimpleVector current = curves.get(i).getStartingPoint();
				this.curves.get(i).add(current);

				for (float r = 0; r < length; r += inc) {

					current = this.getNextCurveValue(current);

					for (int j = 0; j < this.coord.getDimension(); j++) {

						if (current.getMinInt()[j] < coord.getMin(j))
							coord.setMin(current.getMinInt()[j], j);

						if (current.getMaxInt()[j] > coord.getMax(j))
							coord.setMax(current.getMaxInt()[j], j);
					}
					this.curves.get(i).add(current);
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
