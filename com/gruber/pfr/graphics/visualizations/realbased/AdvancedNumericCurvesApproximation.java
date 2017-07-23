package com.gruber.pfr.graphics.visualizations.realbased;

import java.util.List;

import com.gruber.pfr.space.numbers.real.RealNumber;
import com.gruber.pfr.space.numbers.real.RealVector;

public class AdvancedNumericCurvesApproximation extends RealBasedCurves {

	RealBasedVectorField field;
	RealNumber increment;

	public AdvancedNumericCurvesApproximation(List<RealBasedCurve> curves, int length, int granularity,
			int dimension, RealBasedVectorField field) {
		super(curves, length, granularity, dimension);
		this.field = field;
		this.increment = new RealNumber(1 / (new Float(granularity).floatValue()));
		this.calculateCurves();
	}
	

	public RealBasedVector getNextCurveValue(RealBasedVector current) {

		RealNumber[] newOrigin = new RealNumber[this.coord.getDimension()];

		for (int i = 0; i < this.coord.getDimension(); i++)
			newOrigin[i] = current.getOrigin().getElements()[i]
					.add(current.getDirection().getElements()[i].multiply(this.increment));

		try {
			RealVector newReal = (RealVector)current.getOrigin().clone();
			newReal.setElements(newOrigin);
			RealBasedVector newVector = field.getVector(newReal);

			RealNumber factor = new RealNumber(new Float(1).floatValue() / 2);
			factor = this.increment.multiply(factor);
			// correct the approximation
			for (int i = 0; i < this.coord.getDimension(); i++)
				newOrigin[i] = current.getOrigin().getElements()[i].add(factor.multiply(
						newVector.getDirection().getElements()[i].add(current.getDirection().getElements()[i])));

			newReal = (RealVector)current.getOrigin().clone();
			newReal.setElements(newOrigin);
			newVector = field.getVector(newReal);

			return newVector;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
