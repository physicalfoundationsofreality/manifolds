package com.gruber.pfr.graphics.visualizations.phasespace;

import com.gruber.pfr.graphics.visualizations.realbased.RealBasedVector;
import com.gruber.pfr.space.numbers.real.RealNumber;
import com.gruber.pfr.space.numbers.real.RealVector;
import com.gruber.pfr.space.numbers.real.RnSpace;
import com.gruber.pfr.space.vectors.knspaces.KnVector.InvalidElementsException;
import com.gruber.pfr.space.vectors.knspaces.phasespace.PhaseVector;

public class MultiPointParticle extends PhaseSpaceField {

	float[] unitStrength;
	float[] weights;
	float power;
	int sign;

	public MultiPointParticle(float[] unitStrength, float[] weights, float power, int sign) {
		
		super(unitStrength.length * 3);
		this.unitStrength = unitStrength;
		this.weights = new float[weights.length];
		for(int i = 0; i < weights.length; i++)
			this.weights[i] = 1 / weights[i];
		this.sign = sign;
		this.power = power - 1; // the vector will be used as is, so we have one
								// factor of length
	}

	// the vector is supposed to be in the form of x,y,z particle 1, x,y,z
	// particle 2 etc
	public RealBasedVector getVector(PhaseVector vector) {

		try {

			RealBasedVector dir = null;
			RealVector[] vecs = new RealVector[this.unitStrength.length];
			RealVector[] accel = new RealVector[this.unitStrength.length];

			for (int i = 0; i < this.unitStrength.length; i++) {
				RealNumber[] coord = { vector.getElements()[3 * i], vector.getElements()[3 * i + 1],
						vector.getElements()[3 * i + 2] };
				vecs[i] = new RealVector(coord);
				accel[i] = (RealVector) RnSpace.getInstance(3).getNullElement();
			}

			for (int i = 0; i < this.unitStrength.length; i++) {

				for (int j = i + 1; j < this.unitStrength.length; j++) {

					RealVector dist = vecs[i].add(vecs[j].getNegative());

					float length = dist.getLength();
					if (length == 0 && power < 0)
						return null;

					// strength in direction of the j-component
					RealNumber strength = new RealNumber(
							new Double(this.unitStrength[i] * this.unitStrength[j] * Math.pow(length, power))
									.floatValue() * sign * this.weights[i]);
					accel[i] = new RealVector(dist.multiply(strength).getElements()).getNegative().add(accel[i]);
					
					strength = new RealNumber(
							new Double(this.unitStrength[i] * this.unitStrength[j] * Math.pow(length, power))
									.floatValue() * sign * this.weights[j]);
					accel[j] = new RealVector(dist.multiply(strength).getElements()).add(accel[j]);
				}
			}
			RealNumber[] forces = new RealNumber[unitStrength.length * 3];
			for (int i = 0; i < this.unitStrength.length; i++) {
				forces[i * 3] = accel[i].getElements()[0];
				forces[i * 3 + 1] = accel[i].getElements()[1];
				forces[i * 3 + 2] = accel[i].getElements()[2];
			}
			PhaseVector direction = new PhaseVector(vector.getSpace(), vector.getVelocity().getElements(),forces);
			dir = new RealBasedVector(vector);
			dir.setDirection(direction);

			return dir;

		} catch (InvalidElementsException e) {
			e.printStackTrace();
			return null;
		}
	}
}
