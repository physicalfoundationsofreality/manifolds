package com.gruber.pfr.graphics.visualizations.realbased;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.gruber.pfr.graphics.elements.Coordinates;
import com.gruber.pfr.space.numbers.real.RealNumber;
import com.gruber.pfr.space.numbers.real.RealVector;
import com.gruber.pfr.space.vectors.knspaces.KnVector.InvalidElementsException;
import com.gruber.pfr.space.vectors.linearmaps.FiniteDimensionalLinearMap;

public class RealBasedCurvesProjection implements RealBasedVisualization {

	RealBasedVisualization vis;
	FiniteDimensionalLinearMap projection;
	Coordinates coord;
	List<RealBasedCurve> curves;

	public RealBasedCurvesProjection(RealBasedVisualization vis, FiniteDimensionalLinearMap projection) {
		this.vis = vis;
		this.projection = projection;
	}

	public Coordinates getCoordinates() {

		if (coord != null)
			return coord;

		try {
			int[] min = this.vis.getCoordinates().getMin();
			RealNumber[] nums = new RealNumber[min.length];
			for (int i = 0; i < min.length; i++)
				nums[i] = new RealNumber(min[i]);

			RealVector vec = new RealVector(nums);
			vec = (RealVector) this.projection.getImage(vec);
			nums = vec.getElements();
			int[] projMin = new int[nums.length];

			for (int i = 0; i < nums.length; i++)
				projMin[i] = new Double(Math.floor(new Double(nums[i].getBase()).doubleValue())).intValue();

			int[] max = this.vis.getCoordinates().getMax();
			nums = new RealNumber[max.length];
			for (int i = 0; i < max.length; i++)
				nums[i] = new RealNumber(max[i]);

			vec = new RealVector(nums);
			vec = (RealVector) this.projection.getImage(vec);
			nums = vec.getElements();
			int[] projMax = new int[nums.length];

			for (int i = 0; i < nums.length; i++)
				projMax[i] = new Double(Math.floor(new Double(nums[i].getBase()).doubleValue())).intValue();

			coord = new Coordinates(projMin, projMax);
			return coord;

		} catch (InvalidElementsException e) {
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
				RealBasedVector projVect = new RealBasedVector((RealVector) projection.getImage(vector.getOrigin()));
				projVect.setDirection((RealVector) projection.getImage(vector.getDirection()));
				curve.add(projVect);
			}

			curves.add(curve);
		}
		return curves;
	}

	public int getLength() {

		return this.vis.getLength();
	}
}
