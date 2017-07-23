package com.gruber.pfr.space.manifold.realembedded.impl;

import java.util.ArrayList;
import java.util.List;

import com.gruber.pfr.graphics.visualizations.realbased.RealBasedCurve;
import com.gruber.pfr.graphics.visualizations.realbased.RealBasedVector;
import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.manifold.realembedded.RnEmbeddedChart;
import com.gruber.pfr.space.manifold.realembedded.RnEmbeddedManifold;
import com.gruber.pfr.space.numbers.real.RealNumber;
import com.gruber.pfr.space.numbers.real.RealNumbers;
import com.gruber.pfr.space.numbers.real.RealVector;
import com.gruber.pfr.space.numbers.real.RnSpace;
import com.gruber.pfr.space.vectors.knspaces.KnVector.InvalidElementsException;

public class Sphere2ChartProj implements RnEmbeddedChart {

	ArrayList<RealBasedCurve> curves;

	public Set getDomainBasis() {
		return RnSpace.getInstance(3);
	}

	public Set getRangeBasis() {
		return RnSpace.getInstance(2);
	}

	public Set getPreImage(Set image) {

		return this.getPreImage((RealVector) image);
	}

	public RealVector getImage(Set point) {

		RealVector vec = (RealVector) point;
		float x = vec.getElements()[0].getBase() / (new Double(1.0 - vec.getElements()[2].getBase()).floatValue());
		float y = vec.getElements()[1].getBase() / (new Double(1.0 - vec.getElements()[2].getBase()).floatValue());

		RealNumber[] elements = { new RealNumber(x), new RealNumber(y) };

		try {
			return new RealVector(elements);
		} catch (InvalidElementsException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Set getPreImage(RealVector vec) {

		float sqr = vec.getElements()[0].getBase() * vec.getElements()[0].getBase()
				+ vec.getElements()[1].getBase() * vec.getElements()[1].getBase();

		float x = 2 * vec.getElements()[0].getBase() / (1 + sqr);

		float y = 2 * vec.getElements()[1].getBase() / (1 + sqr);

		float z = (sqr - 1) / (1 + sqr);

		RealNumber[] elements = { new RealNumber(x), new RealNumber(y), new RealNumber(z) };

		try {
			return new RealVector(elements);
		} catch (InvalidElementsException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean isInDomain(Set point) {

		RealVector vec = (RealVector) point;

		if (!(vec.getDim() == 3 && vec.getLength() == 1))
			return false;

		return vec.getElements()[0].equals((RealNumber) RealNumbers.getInstance().getOneElement());
	}

	public boolean isInRange(RealVector vec) {

		return vec.getDim() == 2;
	}

	public List<RealBasedCurve> getCoordinates() {

		if (curves != null)
			return curves;

		try {

			curves = new ArrayList<RealBasedCurve>(2 * RnEmbeddedManifold.numberOfCoordinateCurves);
			RealNumber[] nums = { new RealNumber(0), new RealNumber(0), new RealNumber(0) };
			RealVector direction = new RealVector(nums);

			// the phi coordinates
			for (int i = 0; i < RnEmbeddedManifold.numberOfCoordinateCurves; i++) {

				nums = new RealNumber[] { new RealNumber(-RnEmbeddedManifold.numberOfCoordinateCurves / 2 + i),
						new RealNumber(-RnEmbeddedManifold.numberOfCoordinateCurves / 2) };
				RealVector realVector = (RealVector) this.getPreImage(new RealVector(nums));
				RealBasedVector vec = new RealBasedVector(realVector);
				vec.setDirection(direction);
				RealBasedCurve curve = new RealBasedCurve(vec);
				curve.setCoordinate(true);

				for (int j = 0; j < RnEmbeddedManifold.coordinateGranularity; j++) {

					nums = new RealNumber[] { new RealNumber(-RnEmbeddedManifold.numberOfCoordinateCurves / 2 + i),
							new RealNumber(-RnEmbeddedManifold.numberOfCoordinateCurves / 2
									+ j * RnEmbeddedManifold.numberOfCoordinateCurves
											/ RnEmbeddedManifold.coordinateGranularity) };
					realVector = (RealVector) this.getPreImage(new RealVector(nums));
					vec = new RealBasedVector(realVector);
					vec.setDirection(direction);
					curve.add(vec);
				}
				curves.add(curve);

				// the psi coordinates
				nums = new RealNumber[] { new RealNumber(-RnEmbeddedManifold.numberOfCoordinateCurves / 2),
						new RealNumber(-RnEmbeddedManifold.numberOfCoordinateCurves / 2 + i) };
				realVector = (RealVector) this.getPreImage(new RealVector(nums));
				vec = new RealBasedVector(realVector);
				vec.setDirection(direction);
				curve = new RealBasedCurve(vec);
				curve.setCoordinate(true);

				for (int j = 0; j < RnEmbeddedManifold.coordinateGranularity; j++) {

					nums = new RealNumber[] {
							new RealNumber(-RnEmbeddedManifold.numberOfCoordinateCurves / 2
									+ j * RnEmbeddedManifold.numberOfCoordinateCurves
											/ RnEmbeddedManifold.coordinateGranularity),
							new RealNumber(-RnEmbeddedManifold.numberOfCoordinateCurves / 2 + i) };
					realVector = (RealVector) this.getPreImage(new RealVector(nums));
					vec = new RealBasedVector(realVector);
					vec.setDirection(direction);
					curve.add(vec);
				}
				curves.add(curve);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return curves;
	}
}
