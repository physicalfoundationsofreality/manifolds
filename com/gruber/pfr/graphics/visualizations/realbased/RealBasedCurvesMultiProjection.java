package com.gruber.pfr.graphics.visualizations.realbased;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.gruber.pfr.graphics.elements.Coordinates;
import com.gruber.pfr.graphics.visualizations.phasespace.PhaseSpaceProjectionConfiguration;
import com.gruber.pfr.space.numbers.real.RealNumber;
import com.gruber.pfr.space.numbers.real.RealVector;
import com.gruber.pfr.space.vectors.knspaces.KnVector.InvalidElementsException;
import com.gruber.pfr.space.vectors.linearmaps.FiniteDimensionalLinearMap;
import com.gruber.pfr.space.vectors.linearmaps.FiniteMatrix;

public class RealBasedCurvesMultiProjection implements RealBasedVisualization {

	RealBasedVisualization vis;
	PhaseSpaceProjectionConfiguration[] projections;
	Coordinates coord;
	List<RealBasedCurve> curves;
	FiniteDimensionalLinearMap map2d;

	public RealBasedCurvesMultiProjection(RealBasedVisualization vis, PhaseSpaceProjectionConfiguration[] projections,
			FiniteDimensionalLinearMap map2d) {
		this.vis = vis;
		this.projections = new PhaseSpaceProjectionConfiguration[projections.length];
		for (int i = 0; i < this.projections.length; i++) {
			FiniteDimensionalLinearMap orig = projections[i].getProjection();
			FiniteMatrix matrix = (FiniteMatrix) map2d.getMatrix().multiply(orig.getMatrix());
			FiniteDimensionalLinearMap map = new FiniteDimensionalLinearMap(orig.getDomainBasis(), map2d.getRangeBasis(), matrix);
			this.projections[i] = new PhaseSpaceProjectionConfiguration(projections[i].getOriginColor(), projections[i].getDirectionColor(), map);
		}
		this.map2d = map2d;
	}

	public Coordinates getCoordinates() {

		if (coord != null)
			return coord;

		try {

			int[][] projMin = new int[projections.length][];
			int[][] projMax = new int[projections.length][];
			
			for (int k = 0; k < this.projections.length; k++) {

				int[] min = this.vis.getCoordinates().getMin();
				RealNumber[] numsMin = new RealNumber[min.length];
				for (int i = 0; i < min.length; i++)
					numsMin[i] = new RealNumber(min[i]);
				
				RealVector vec = new RealVector(numsMin);
				vec = (RealVector) this.projections[k].getProjection().getImage(vec);
				numsMin = vec.getElements();
				projMin[k] = new int[numsMin.length];

				for (int i = 0; i < numsMin.length; i++)
					projMin[k][i] = new Double(Math.floor(new Double(numsMin[i].getBase()).doubleValue())).intValue();

				int[] max = this.vis.getCoordinates().getMax();
				RealNumber[]  numsMax = new RealNumber[max.length];
				for (int i = 0; i < max.length; i++)
					numsMax[i] = new RealNumber(max[i]);
				
				vec = new RealVector(numsMax);
				vec = (RealVector) this.projections[k].getProjection().getImage(vec);
				numsMax = vec.getElements();
				projMax[k] = new int[numsMax.length];

				for (int i = 0; i < numsMax.length; i++)
					projMax[k][i] = new Double(Math.floor(new Double(numsMax[i].getBase()).doubleValue())).intValue();

			}
			int[] totMin = Arrays.copyOf(projMin[0], projMin[0].length);
			int[] totMax = Arrays.copyOf(projMax[0], projMax[0].length);

			for (int k = 1; k < this.projections.length; k++) {

				for (int i = 0; i < projMin[k].length; i++) {
					totMin[i] = Math.min(totMin[i], projMin[k][i]);
					totMax[i] = Math.max(totMax[i], projMax[k][i]);
				}
			}

			coord = new Coordinates(totMin, totMax);
			return coord;

		} catch (

		InvalidElementsException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void setDisplayParameters(int granularity) {
		this.vis.setDisplayParameters(granularity);
	}

	public List<RealBasedCurve> getCurves() {

		if (curves != null)
			return curves;

		List<RealBasedCurve> realCurves = this.vis.getCurves();
		curves = new ArrayList<RealBasedCurve>();

		for (int i = 0; i < this.projections.length; i++) {

			Iterator<RealBasedCurve> curvesIter = realCurves.iterator();
			while (curvesIter.hasNext()) {

				RealBasedCurve realCurve = curvesIter.next();
				RealBasedCurve curve = new RealBasedCurve(realCurve.originColor, realCurve.directionColor,
						realCurve.getStartingPoint());

				Iterator<RealBasedVector> curveIter = realCurve.getCurve().iterator();
				while (curveIter.hasNext()) {
					RealBasedVector vector = curveIter.next();
					// System.out.println("Origin:
					// "+vector.getOrigin().getElements()[0].getBase()
					// +" "+vector.getOrigin().getElements()[1].getBase()
					// +" "+vector.getOrigin().getElements()[2].getBase()
					// +" "+vector.getOrigin().getElements()[3].getBase()
					// +" Direction:
					// "+vector.getDirection().getElements()[0].getBase()
					// +" "+vector.getDirection().getElements()[1].getBase()
					// +" "+vector.getDirection().getElements()[2].getBase()
					// +" "+vector.getDirection().getElements()[3].getBase());
					RealBasedVector projVect = new RealBasedVector(
							(RealVector) projections[i].getProjection().getImage(vector.getOrigin()));
					projVect.setDirection((RealVector) projections[i].getProjection().getImage(vector.getDirection()));
					curve.add(projVect);
					curve.setDirectionColor(this.projections[i].getDirectionColor());
					curve.setOriginColor(this.projections[i].getOriginColor());
				}
				curves.add(curve);
			}
		}
		return curves;
	}
	public int getLength() {

		return this.vis.getLength();
	}
}
