package com.gruber.pfr.graphics.visualizations.realbased;

import com.gruber.pfr.space.numbers.real.RealNumber;
import com.gruber.pfr.space.numbers.real.RealVector;

public class RealBasedCircleField2D implements RealBasedVectorField {

	public RealBasedVector getVector(RealVector origin) {

		try {
			RealBasedVector vec = new RealBasedVector(origin);
			RealNumber[] dir = new RealNumber[2];
			dir[0] = origin.getElements()[1].getNegative();
			dir[1] = origin.getElements()[0];
			RealVector direction = new RealVector(dir);
			vec.setDirection(direction);
			return vec;
		} catch (Exception e) {
			return null;
		}
	}

}
