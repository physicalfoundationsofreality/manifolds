package com.gruber.pfr.space.manifold.realembedded;

import java.util.List;

import com.gruber.pfr.graphics.visualizations.realbased.RealBasedCurve;
import com.gruber.pfr.graphics.visualizations.realbased.RealBasedVector;
import com.gruber.pfr.space.manifold.Chart;
import com.gruber.pfr.space.numbers.real.RealNumber;
import com.gruber.pfr.space.numbers.real.RealVector;

public interface RnEmbeddedChart extends Chart {

	public List<RealBasedCurve> getCoordinates();

	public default void extendCurve(RealBasedCurve curve, int granularity, int length) {

		RealBasedVector current = null;
		RealNumber increment = new RealNumber(1 / (new Float(granularity).floatValue()));
		RealNumber factor = new RealNumber(new Float(1).floatValue() / 2);
		factor = increment.multiply(factor);
		int dim = 0;

		while (curve.getCurve().size() < granularity * length) {

			// get next point from curve in the chart
			if (current == null) {
				RealVector origin = getImage(curve.getLastPoint().getOrigin());
				current = new RealBasedVector(origin);
				current.setDirection(this.getDirection(origin));
				dim = current.getOrigin().getElements().length;
			}

			// get the next point with Rn
			try {
				RealNumber[] newOriginEls = new RealNumber[dim];

				for (int i = 0; i < dim; i++)
					newOriginEls[i] = current.getOrigin().getElements()[i]
							.add(current.getDirection().getElements()[i].multiply(increment));

				RealVector newOrigin = new RealVector(newOriginEls);

				RealVector newDirection = this.getDirection(newOrigin);

				// correct the approximation
				for (int i = 0; i < dim; i++)
					newOriginEls[i] = current.getOrigin().getElements()[i].add(factor
							.multiply(newDirection.getElements()[i].add(current.getDirection().getElements()[i])));

				newOrigin = new RealVector(newOriginEls);
				if (!this.isInRange(newOrigin))
					return;

				current = new RealBasedVector(newOrigin);
				current.setDirection(this.getDirection(newOrigin));

				RealBasedVector newPoint = new RealBasedVector((RealVector) this.getPreImage(newOrigin));
				newPoint.setDirection((RealVector) this.getPreImage(this.getDirection(newOrigin)));
				curve.add(newPoint);

			} catch (Exception e) {
				return;
			}
		}
	}
}
