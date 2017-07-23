package com.gruber.pfr.graphics.visualizations.phasespace;

import com.gruber.pfr.graphics.visualizations.realbased.RealBasedVector;
import com.gruber.pfr.space.numbers.real.RealNumber;
import com.gruber.pfr.space.numbers.real.RealNumbers;
import com.gruber.pfr.space.numbers.real.RealVector;
import com.gruber.pfr.space.vectors.knspaces.phasespace.PhaseSpace;
import com.gruber.pfr.space.vectors.linearmaps.FiniteDimensionalLinearMap;
import com.gruber.pfr.space.vectors.linearmaps.FiniteMatrix;

public class PhaseSpaceMultiParticleConfiguration {

	PhaseSpaceParticleConfiguration[] configs;
	PhaseSpaceProjectionConfiguration[] projects;
	PhaseSpace space;
	PhaseSpace space3d;
	PhaseSpaceStartConfiguration start;

	public PhaseSpaceMultiParticleConfiguration(PhaseSpaceParticleConfiguration[] configs) {

		this.configs = configs;

		space = new PhaseSpace(3 * configs.length);
		space3d = new PhaseSpace(3);

		try {

			RealNumber[] nums = new RealNumber[3 * configs.length];
			for (int i = 0; i < configs.length; i++) {
				nums[i * 3] = configs[i].getStartCoordinates()[0];
				nums[i * 3 + 1] = configs[i].getStartCoordinates()[1];
				nums[i * 3 + 2] = configs[i].getStartCoordinates()[2];
			}
			RealBasedVector origin = new RealBasedVector(new RealVector(nums));
			
			nums = new RealNumber[3 * configs.length];
			for (int i = 0; i < configs.length; i++) {
				nums[i * 3] = configs[i].getStartVelocity()[0];
				nums[i * 3 + 1] = configs[i].getStartVelocity()[1];
				nums[i * 3 + 2] = configs[i].getStartVelocity()[2];
			}
			RealBasedVector direction = new RealBasedVector(new RealVector(nums));

			start = new PhaseSpaceStartConfiguration(null, null, origin, direction);

		} catch (Exception e) {
			e.printStackTrace();
		}

		projects = new PhaseSpaceProjectionConfiguration[configs.length];
		for (int i = 0; i < configs.length; i++) {

			RealNumber[][] array = new RealNumber[6][];
			for (int j = 0; j < 6; j++) {
				array[j] = new RealNumber[6 * configs.length];
				for (int k = 0; k < configs.length; k++) {
					for (int l = 0; l < 3; l++) {
						if (k == i && j == l) {
							array[j][3 * k + l] = new RealNumber(1);
							array[j][3 * k + l + configs.length * 3] = new RealNumber(0);
						} else if(k == i && j == l+3) {
							array[j][3 * k + l + configs.length * 3] = new RealNumber(1);
							array[j][3 * k + l] = new RealNumber(0);
						} else {
							array[j][3 * k + l] = new RealNumber(0);
							array[j][3 * k + l + configs.length * 3] = new RealNumber(0);
						}
					}
				}
			}

			FiniteMatrix matrix = new FiniteMatrix(RealNumbers.getInstance(), array);
			FiniteDimensionalLinearMap map = new FiniteDimensionalLinearMap(space.getStandardBasis(),
					space3d.getStandardBasis(), matrix);

			projects[i] = new PhaseSpaceProjectionConfiguration(configs[i].getOriginColor(),
					configs[i].getDirectionColor(), map);
		}
	}

	public PhaseSpaceProjectionConfiguration[] getProjects() {
		return projects;
	}
	
	public int getDimension() {
		return this.configs.length * 3;
	}

	public PhaseSpace getSpace() {
		return space;
	}

	public PhaseSpaceStartConfiguration getStart() {
		return start;
	}

	public PhaseSpace getSpace3d() {
		return space3d;
	}

	public PhaseSpaceParticleConfiguration[] getConfigs() {
		return configs;
	}

	public float[] getUnitStrengths() {

		float[] strengths = new float[this.configs.length];
		for (int i = 0; i < this.configs.length; i++)
			strengths[i] = this.configs[i].unitStrength;

		return strengths;
	}
	public float[] getWeights() {

		float[] weights = new float[this.configs.length];
		for (int i = 0; i < this.configs.length; i++)
			weights[i] = this.configs[i].weight;

		return weights;
	}
}
