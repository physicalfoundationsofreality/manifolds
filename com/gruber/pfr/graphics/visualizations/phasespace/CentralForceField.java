package com.gruber.pfr.graphics.visualizations.phasespace;

import com.gruber.pfr.graphics.visualizations.realbased.RealBasedVector;
import com.gruber.pfr.space.numbers.real.RealNumber;
import com.gruber.pfr.space.vectors.knspaces.KnVector.InvalidElementsException;
import com.gruber.pfr.space.vectors.knspaces.phasespace.PhaseVector;

public class CentralForceField extends PhaseSpaceField {

	float unitStrength;
	float power;

	public CentralForceField(int dimension, float unitStrength, float power) {
		super(dimension);
		this.unitStrength = unitStrength;
		this.power = power - 1; // the vector will be used as is, so we have one factor of length
	}

	public RealBasedVector getVector(PhaseVector vector) {

		float length = vector.getPosition().getLength();
		if (length == 0 && power < 0)
			return null;

		RealNumber strength = new RealNumber(new Double(this.unitStrength * Math.pow(length, power)).floatValue());

		try {
			PhaseVector direction = new PhaseVector(vector.getSpace(), vector.getVelocity().getElements(),
					vector.getPosition().multiply(strength).getElements());
			
			RealBasedVector dir = new RealBasedVector(vector);
			dir.setDirection(direction);
			return dir;
			
		} catch (InvalidElementsException e) {
			e.printStackTrace();
			return null;
		}
	}
}
