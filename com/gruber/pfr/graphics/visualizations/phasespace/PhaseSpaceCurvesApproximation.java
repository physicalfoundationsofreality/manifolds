package com.gruber.pfr.graphics.visualizations.phasespace;

import java.util.ArrayList;
import java.util.List;

import com.gruber.pfr.graphics.visualizations.realbased.AdvancedNumericCurvesApproximation;
import com.gruber.pfr.graphics.visualizations.realbased.RealBasedCurve;
import com.gruber.pfr.graphics.visualizations.realbased.RealBasedVector;
import com.gruber.pfr.graphics.visualizations.realbased.RealBasedVectorField;
import com.gruber.pfr.space.vectors.knspaces.KnVector.InvalidElementsException;
import com.gruber.pfr.space.vectors.knspaces.phasespace.PhaseSpace;
import com.gruber.pfr.space.vectors.knspaces.phasespace.PhaseVector;

public class PhaseSpaceCurvesApproximation extends AdvancedNumericCurvesApproximation {

	PhaseSpace space = null;

	public PhaseSpaceCurvesApproximation(List<PhaseSpaceStartConfiguration> startConfigurations, int length,
			int granularity, int dimension, RealBasedVectorField field) {

		super(null, length, granularity, 2 * dimension, field);

		try {
			this.space = new PhaseSpace(dimension);

			ArrayList<RealBasedCurve> curves = new ArrayList<RealBasedCurve>();
			for (int i = 0; i < startConfigurations.size(); i++) {
				PhaseVector vector = new PhaseVector(this.space,
						startConfigurations.get(i).getStartingPoint().getOrigin().getElements(),
						startConfigurations.get(i).getStartingVelocity().getOrigin().getElements());
				RealBasedVector start = new RealBasedVector(vector);
				curves.add(new RealBasedCurve(startConfigurations.get(i).getOriginColor(),
						startConfigurations.get(i).getDirectionColor(), start));
			}
			this.setCurves(curves);
			this.calculateCurves();

		} catch (InvalidElementsException e) {
			e.printStackTrace();
		}
	}
}
