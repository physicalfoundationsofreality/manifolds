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

public class Sphere2ChartPolarAlt2 implements RnEmbeddedChart {

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
		float phi;
//		if (vec.getElements()[1].getBase() == 0)
//			phi = 0;
//		else 
		if (vec.getElements()[0].getBase() == 0 && vec.getElements()[1].getBase() < 0)
			phi = new Double(Math.PI * 3 / 2).floatValue();
		else if (vec.getElements()[0].getBase() == 0 && vec.getElements()[1].getBase() > 0)
			phi = new Double(Math.PI / 2).floatValue();
		else
			phi = new Double(Math.atan(vec.getElements()[1].getBase() / vec.getElements()[0].getBase())).floatValue();

		if(phi < 0)
			phi += Math.PI * 2;
		
		float psi = new Double(Math.asin(vec.getElements()[2].getBase())).floatValue();
		
		if(psi >  Math.PI / 2)
			psi = new Double(psi - Math.PI).floatValue();

		RealNumber[] elements = { new RealNumber(phi), new RealNumber(psi) };

		try {
			return new RealVector(elements);
		} catch (InvalidElementsException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Set getPreImage(RealVector vec) {

		float z = new Double(Math.sin(vec.getElements()[0].getBase())).floatValue()
				* new Double(Math.cos(vec.getElements()[1].getBase())).floatValue();

		float x = new Double(Math.sin(vec.getElements()[1].getBase())).floatValue();

		float y = new Double(Math.cos(vec.getElements()[0].getBase())).floatValue()
				* new Double(Math.cos(vec.getElements()[1].getBase())).floatValue();

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
//		if (!(vec.getDim() == 3 && vec.getLength() == 1))  We assume the point from manifold
//			return false;

		RealNumber nullEl = (RealNumber) RealNumbers.getInstance().getNullElement();
		return !vec.getElements()[1].equals(nullEl) || vec.getElements()[0].greaterThen(nullEl);
	}

	public boolean isInRange(RealVector vec) {

		return (vec.getDim() == 2 && vec.getElements()[0].getBase() > 0 && vec.getElements()[0].getBase() < 2 * Math.PI
				&& vec.getElements()[1].getBase() > -Math.PI / 2 && vec.getElements()[1].getBase() < Math.PI / 2);
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

				nums = new RealNumber[] {
						new RealNumber(
								new Double(i * 2 * Math.PI / RnEmbeddedManifold.numberOfCoordinateCurves).floatValue()),
						new RealNumber(new Double(-Math.PI / 2).floatValue()) };
				RealVector realVector = (RealVector) this.getPreImage(new RealVector(nums));
				RealBasedVector vec = new RealBasedVector(realVector);
				vec.setDirection(direction);
				RealBasedCurve curve = new RealBasedCurve();
				curve.setCoordinate(true);

				for (int j = 0; j < RnEmbeddedManifold.coordinateGranularity; j++) {

					nums = new RealNumber[] {
							new RealNumber(new Double(i * 2 * Math.PI / RnEmbeddedManifold.numberOfCoordinateCurves)
									.floatValue()),
							new RealNumber(
									new Double(-Math.PI / 2 + j * Math.PI / RnEmbeddedManifold.coordinateGranularity)
											.floatValue()) };
					realVector = (RealVector) this.getPreImage(new RealVector(nums));
					vec = new RealBasedVector(realVector);
					vec.setDirection(direction);
					curve.add(vec);
				}
				curves.add(curve);

				// the psi coordinates
				nums = new RealNumber[] { new RealNumber(0),
						new RealNumber(
								new Double(-Math.PI / 2 + i * Math.PI / RnEmbeddedManifold.numberOfCoordinateCurves)
										.floatValue()) };
				realVector = (RealVector) this.getPreImage(new RealVector(nums));
				vec = new RealBasedVector(realVector);
				vec.setDirection(direction);
				curve = new RealBasedCurve();
				curve.setCoordinate(true);

				for (int j = 0; j < RnEmbeddedManifold.coordinateGranularity; j++) {

					nums = new RealNumber[] {
							new RealNumber(new Double(j * 2 * Math.PI / RnEmbeddedManifold.coordinateGranularity)
									.floatValue()),
							new RealNumber(
									new Double(- Math.PI / 2 + i * Math.PI / RnEmbeddedManifold.numberOfCoordinateCurves)
											.floatValue()) };
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

	public RealVector getDirection(RealVector vector) {
		return vector.multiply(new RealNumber(1/vector.getLength()));
	}
}
