package com.gruber.pfr.graphics.visualizations.realbased;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.gruber.pfr.graphics.GraphWizard;
import com.gruber.pfr.graphics.SimpleCurve;
import com.gruber.pfr.graphics.Visualization2D;
import com.gruber.pfr.graphics.elements.Coordinates2D;
import com.gruber.pfr.space.numbers.real.RealVector;

public class RealBasedCurves2DMultiProjection implements Visualization2D {

	RealBasedVisualization vis;
	Projection2DConfiguration[] map2d;
	Coordinates2D coord;
	List<SimpleCurve> curves;
	GraphWizard.FunctionType functionTypeCoordinates = GraphWizard.FunctionType.SINGLE_POINT;
	GraphWizard.FunctionType functionTypeSpaceCurve = GraphWizard.FunctionType.LINE_STRAIGHT;

	public RealBasedCurves2DMultiProjection(RealBasedVisualization vis, Projection2DConfiguration[] map2d) {

		this.vis = vis;
		this.map2d = map2d;
	}

	public GraphWizard.FunctionType getFunctionTypeCoordinates() {
		return functionTypeCoordinates;
	}

	public void setFunctionTypeCoordinates(GraphWizard.FunctionType functionTypeCoordinates) {
		this.functionTypeCoordinates = functionTypeCoordinates;
	}

	public GraphWizard.FunctionType getFunctionTypeSpaceCurve() {
		return functionTypeSpaceCurve;
	}

	public void setFunctionTypeSpaceCurve(GraphWizard.FunctionType functionTypeSpaceCurve) {
		this.functionTypeSpaceCurve = functionTypeSpaceCurve;
	}

	public int getLength() {
		return this.vis.getLength();
	}

	public Coordinates2D getCoordinates() {

		if(this.map2d == null)
			return null;
		
		Coordinates2D coord = this.map2d[0].getCoord();
		for(int i = 1; i < this.map2d.length; i++) {
			coord.setMinX(Math.min(coord.getMinX(), this.map2d[i].getCoord().getMinX()));
			coord.setMaxX(Math.max(coord.getMaxX(), this.map2d[i].getCoord().getMaxX()));
			coord.setMinY(Math.min(coord.getMinY(), this.map2d[i].getCoord().getMinY()));
			coord.setMaxY(Math.max(coord.getMaxY(), this.map2d[i].getCoord().getMaxY()));
		}
		
		return coord;
//		if (coord != null)
//			return coord;
//
//		try {
//
//			int[][] projMin = new int[map2d.length][];
//			int[][] projMax = new int[map2d.length][];
//
//			for (int k = 0; k < this.map2d.length; k++) {
//
//				int[] min = this.vis.getCoordinates().getMin();
//				RealNumber[] numsMin = new RealNumber[min.length];
//				for (int i = 0; i < min.length; i++)
//					numsMin[i] = new RealNumber(min[i]);
//
//				RealVector vec = new RealVector(numsMin);
//				vec = (RealVector) this.map2d[k].getProjection().getImage(vec);
//				numsMin = vec.getElements();
//				projMin[k] = new int[numsMin.length];
//
//				for (int i = 0; i < numsMin.length; i++)
//					projMin[k][i] = new Double(Math.floor(new Double(numsMin[i].getBase()).doubleValue())).intValue();
//
//				int[] max = this.vis.getCoordinates().getMax();
//				RealNumber[] numsMax = new RealNumber[max.length];
//				for (int i = 0; i < max.length; i++)
//					numsMax[i] = new RealNumber(max[i]);
//
//				vec = new RealVector(numsMax);
//				vec = (RealVector) this.map2d[k].getProjection().getImage(vec);
//				numsMax = vec.getElements();
//				projMax[k] = new int[numsMax.length];
//
//				for (int i = 0; i < numsMax.length; i++)
//					projMax[k][i] = new Double(Math.floor(new Double(numsMax[i].getBase()).doubleValue())).intValue();
//
//			}
//			int[] totMin = Arrays.copyOf(projMin[0], projMin[0].length);
//			int[] totMax = Arrays.copyOf(projMax[0], projMax[0].length);
//
//			for (int k = 1; k < this.map2d.length; k++) {
//
//				for (int i = 0; i < projMin[k].length; i++) {
//					totMin[i] = Math.min(totMin[i], projMin[k][i]);
//					totMax[i] = Math.max(totMax[i], projMax[k][i]);
//				}
//			}
//
//			coord = new Coordinates2D(totMin[0], totMax[0], totMin[1], totMax[1]);
//			return coord;
//
//		} catch (
//
//		InvalidElementsException e) {
//			e.printStackTrace();
//			return null;
//		}
	}

	public void setDisplayParameters(int granularity) {
		this.vis.setDisplayParameters(granularity);
	}

	public List<SimpleCurve> getCurves() {

		if (curves != null)
			return curves;

		List<RealBasedCurve> realCurves = this.vis.getCurves();
		curves = new ArrayList<SimpleCurve>();

		for (int i = 0; i < this.map2d.length; i++) {

			Iterator<RealBasedCurve> curvesIter = realCurves.iterator();
			while (curvesIter.hasNext()) {

				// draw coordinates only once
				RealBasedCurve realCurve = curvesIter.next();
				
				if (realCurve.isCoordinate() && i > 0)
					continue;

				SimpleCurve curve = new SimpleCurve(map2d[i].getOriginColor(), map2d[i].getDirectionColor(), functionTypeSpaceCurve);
				if (realCurve.isCoordinate())
					curve.setFunctionType(this.functionTypeCoordinates);
				else
					curve.setFunctionType(this.functionTypeSpaceCurve);
				
				if(curve.getFunctionType().equals(GraphWizard.FunctionType.NONE))
					continue;

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
							(RealVector) map2d[i].getProjection().getImage(vector.getOrigin()));
					projVect.setDirection((RealVector) map2d[i].getProjection().getImage(vector.getDirection()));
					curve.add(projVect.asVector());
				}
				curves.add(curve);
			}
		}
		return curves;
	}
}
