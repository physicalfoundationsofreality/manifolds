package com.gruber.pfr.space.manifold.realembedded.impl;

import java.util.ArrayList;
import java.util.List;

import com.gruber.pfr.graphics.visualizations.realbased.RealBasedCurve;
import com.gruber.pfr.graphics.visualizations.realbased.RealBasedVector;
import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.manifold.realembedded.RnEmbeddedChart;
import com.gruber.pfr.space.manifold.realembedded.RnEmbeddedManifold;
import com.gruber.pfr.space.numbers.real.RealNumber;
import com.gruber.pfr.space.numbers.real.RealVector;
import com.gruber.pfr.space.numbers.real.RnSpace;
import com.gruber.pfr.space.vectors.knspaces.KnVector.InvalidElementsException;

public class Torus2Chart implements RnEmbeddedChart {

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
		if (vec.getElements()[0].getBase() == 0)
			phi = 0;
		else if (vec.getElements()[2].getBase() == 0 && vec.getElements()[0].getBase() < 0)
			phi = -1;
		else if (vec.getElements()[2].getBase() == 0 && vec.getElements()[0].getBase() > 0)
			phi = 1;
		else
			phi = new Double(Math.atan(vec.getElements()[0].getBase() / vec.getElements()[2].getBase())).floatValue();

		float psi = new Double(Math.asin(vec.getElements()[1].getBase())).floatValue();

		RealNumber[] elements = { new RealNumber(phi), new RealNumber(psi) };

		try {
			return new RealVector(elements);
		} catch (InvalidElementsException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Set getPreImage(RealVector vec) {

		float x = ( new Double(Math.cos(vec.getElements()[1].getBase())).floatValue() + 2)
				* new Double(Math.sin(vec.getElements()[0].getBase())).floatValue();

		float y = new Double(Math.sin(vec.getElements()[1].getBase())).floatValue();

		float z = (new Double(Math.cos(vec.getElements()[1].getBase())).floatValue() + 2)
				* new Double(Math.cos(vec.getElements()[0].getBase())).floatValue();

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
		RealNumber[] els = vec.getElements();
		float right = ( els[0].getBase() * els[0].getBase() 
				+ els[1].getBase() * els[1].getBase() 
				+ els[2].getBase() * els[2].getBase() + 3 );
		if (!(vec.getDim() == 3 
				&& ( right * right == 16 * (els[0].getBase() * els[0].getBase() 
				+ els[2].getBase() * els[2].getBase()))))
			return false;
		
		return true;
	}

	public boolean isInRange(RealVector vec) {

		return (vec.getDim() == 2 && vec.getElements()[0].getBase() > 0 && vec.getElements()[0].getBase() < 2 * Math.PI
				&& vec.getElements()[1].getBase() > 0 && vec.getElements()[1].getBase() < 2 * Math.PI);
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
				RealBasedCurve curve = new RealBasedCurve(vec);
				curve.setCoordinate(true);

				for (int j = 0; j < RnEmbeddedManifold.coordinateGranularity; j++) {

					nums = new RealNumber[] {
							new RealNumber(new Double(i * 2 * Math.PI / RnEmbeddedManifold.numberOfCoordinateCurves)
									.floatValue()),
							new RealNumber(
									new Double(2 * j * Math.PI / RnEmbeddedManifold.coordinateGranularity).floatValue()) };
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
				curve = new RealBasedCurve(vec);
				curve.setCoordinate(true);

				for (int j = 0; j < RnEmbeddedManifold.coordinateGranularity; j++) {

					nums = new RealNumber[] {
							new RealNumber(new Double(j * 2 * Math.PI / RnEmbeddedManifold.coordinateGranularity)
									.floatValue()),
							new RealNumber(
									new Double(2 * i * Math.PI / RnEmbeddedManifold.numberOfCoordinateCurves).floatValue()) };
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
