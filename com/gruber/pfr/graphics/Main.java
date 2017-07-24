package com.gruber.pfr.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.gruber.pfr.graphics.elements.Coordinates2D;
import com.gruber.pfr.graphics.elements.SimplePoint;
import com.gruber.pfr.graphics.elements.SimpleVector;
import com.gruber.pfr.graphics.visualizations.CircleField2D;
import com.gruber.pfr.graphics.visualizations.Polynomial;
import com.gruber.pfr.graphics.visualizations.SimpleCurves;
import com.gruber.pfr.graphics.visualizations.SimpleCurves2D;
import com.gruber.pfr.graphics.visualizations.SimpleNumericCurvesApproximation;
import com.gruber.pfr.graphics.visualizations.phasespace.CentralForceField;
import com.gruber.pfr.graphics.visualizations.phasespace.MultiPointParticle;
import com.gruber.pfr.graphics.visualizations.phasespace.PhaseSpaceCurvesApproximation;
import com.gruber.pfr.graphics.visualizations.phasespace.PhaseSpaceMultiParticleConfiguration;
import com.gruber.pfr.graphics.visualizations.phasespace.PhaseSpaceParticleConfiguration;
import com.gruber.pfr.graphics.visualizations.phasespace.PhaseSpaceStartConfiguration;
import com.gruber.pfr.graphics.visualizations.realbased.AdvancedNumericCurvesApproximation;
import com.gruber.pfr.graphics.visualizations.realbased.Projection2DConfiguration;
import com.gruber.pfr.graphics.visualizations.realbased.RealBasedCircleField2D;
import com.gruber.pfr.graphics.visualizations.realbased.RealBasedCurve;
import com.gruber.pfr.graphics.visualizations.realbased.RealBasedCurves;
import com.gruber.pfr.graphics.visualizations.realbased.RealBasedCurves2D;
import com.gruber.pfr.graphics.visualizations.realbased.RealBasedCurves2DMultiProjection;
import com.gruber.pfr.graphics.visualizations.realbased.RealBasedCurvesMultiProjection;
import com.gruber.pfr.graphics.visualizations.realbased.RealBasedCurvesProjection;
import com.gruber.pfr.graphics.visualizations.realbased.RealBasedVector;
import com.gruber.pfr.graphics.visualizations.realbased.RealBasedVisualization;
import com.gruber.pfr.space.manifold.Chart;
import com.gruber.pfr.space.manifold.realembedded.impl.Sphere2ChartProj;
import com.gruber.pfr.space.manifold.realembedded.impl.Sphere2Manifold;
import com.gruber.pfr.space.numbers.real.RealNumber;
import com.gruber.pfr.space.numbers.real.RealNumbers;
import com.gruber.pfr.space.numbers.real.RealVector;
import com.gruber.pfr.space.numbers.real.RnSpace;
import com.gruber.pfr.space.vectors.knspaces.phasespace.PhaseSpace;
import com.gruber.pfr.space.vectors.linearmaps.FiniteDimensionalLinearMap;
import com.gruber.pfr.space.vectors.linearmaps.FiniteMatrix;

public class Main {

	static GraphWizard.FunctionType functionType = GraphWizard.FunctionType.SINGLE_POINT;

	public static void main(String[] args) {

		JFrame f = new JFrame("Graph Wizard");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GraphWizard ap = new GraphWizard(manifoldCoordinates(), new Dimension(1100, 1100), false);

		ap.init();
		ap.start();
		f.add("Center", ap);
		f.pack();
		f.setVisible(true);

	}

	public static Visualization2D testSetup() {

		return new Polynomial(new float[] { 0, 63, -3, 5, -1 }, -2, 6, 5, functionType);
	}

	public static Visualization2D vectorTest1() {

		ArrayList<SimpleCurve> curves = new ArrayList<SimpleCurve>(3);

		SimpleVector start = new SimpleVector(new SimplePoint(new float[] { 1, 0 }));
		start.setDirection(new SimplePoint(new float[] { 0, 1 }));
		curves.add(new SimpleCurve(Color.RED, Color.BLACK, start, functionType));

		start = new SimpleVector(new SimplePoint(new float[] { 2, 0 }));
		start.setDirection(new SimplePoint(new float[] { 0, 2 }));
		curves.add(new SimpleCurve(Color.BLUE, Color.BLACK, start, functionType));

		start = new SimpleVector(new SimplePoint(new float[] { 4, 0 }));
		start.setDirection(new SimplePoint(new float[] { 0, 4 }));
		curves.add(new SimpleCurve(Color.MAGENTA, Color.BLACK, start, functionType));

		SimpleCurves ret = new SimpleNumericCurvesApproximation(curves, 50, 10, 2, new CircleField2D());

		return new SimpleCurves2D(ret);
	}

	public static Visualization2D vectorTest2() {

		// RealBasedVector[] start = new RealBasedVector[3];
		ArrayList<RealBasedCurve> curves = new ArrayList<RealBasedCurve>(3);

		try {
			/*
			 * start[0] = new RealBasedVector(new RealVector( new RealNumber[] {
			 * new RealNumber(1), new RealNumber(0) }));
			 * start[0].setDirection(new RealVector(new RealNumber[] { new
			 * RealNumber(0), new RealNumber(1) }));
			 * 
			 * start[1] = new RealBasedVector(new RealVector(new RealNumber[] {
			 * new RealNumber(2), new RealNumber(0) }));
			 * start[1].setDirection(new RealVector(new RealNumber[] { new
			 * RealNumber(0), new RealNumber(2) }));
			 * 
			 * start[2] = new RealBasedVector(new RealVector(new RealNumber[] {
			 * new RealNumber(4), new RealNumber(0) }));
			 * start[2].setDirection(new RealVector(new RealNumber[] { new
			 * RealNumber(0), new RealNumber(4) }));
			 */

			RealBasedVector start = new RealBasedVector(
					new RealVector(new RealNumber[] { new RealNumber(1), new RealNumber(0) }));
			start.setDirection(new RealVector(new RealNumber[] { new RealNumber(0), new RealNumber(1) }));
			curves.add(new RealBasedCurve(Color.CYAN, Color.BLACK, start));

			start = new RealBasedVector(new RealVector(new RealNumber[] { new RealNumber(2), new RealNumber(0) }));
			start.setDirection(new RealVector(new RealNumber[] { new RealNumber(0), new RealNumber(2) }));
			curves.add(new RealBasedCurve(Color.BLUE, Color.BLACK, start));

			start = new RealBasedVector(new RealVector(new RealNumber[] { new RealNumber(4), new RealNumber(0) }));
			start.setDirection(new RealVector(new RealNumber[] { new RealNumber(0), new RealNumber(4) }));
			curves.add(new RealBasedCurve(Color.GREEN, Color.BLACK, start));

			RealBasedCurves ret = new AdvancedNumericCurvesApproximation(curves, 7, 10, 2,
					new RealBasedCircleField2D());

			return new RealBasedCurves2D(ret, functionType);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Visualization2D phaseSpaceTest() {

		// RealVector[] start = new RealVector[1];
		// RealVector[] vel = new RealVector[1];
		ArrayList<PhaseSpaceStartConfiguration> configs = new ArrayList<PhaseSpaceStartConfiguration>();

		try {
			// start[0] = new RealVector(new RealNumber[] { new RealNumber(10)
			// });
			// vel[0] = new RealVector(new RealNumber[] { new RealNumber(1) });
			// start[1] = new RealVector(new RealNumber[] { new RealNumber(2)
			// });
			// vel[1] = new RealVector(new RealNumber[] { new RealNumber(0) });
			//
			// start[2] = new RealVector(new RealNumber[] { new RealNumber(4)
			// });
			// vel[2] = new RealVector(new RealNumber[] { new RealNumber(0) });

			RealBasedVector start = new RealBasedVector(new RealVector(new RealNumber[] { new RealNumber(10) }));
			RealBasedVector vel = new RealBasedVector(new RealVector(new RealNumber[] { new RealNumber(1) }));

			configs.add(new PhaseSpaceStartConfiguration(Color.BLUE, Color.BLACK, start, vel));

			return new RealBasedCurves2D(new PhaseSpaceCurvesApproximation(configs, 10, 10, 1,
					new CentralForceField(1, -1, new Double(0).floatValue())), functionType);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Visualization2D phaseSpaceProjectionTest() {

		// RealVector[] start = new RealVector[3];
		// RealVector[] vel = new RealVector[3];
		ArrayList<PhaseSpaceStartConfiguration> configs = new ArrayList<PhaseSpaceStartConfiguration>();

		try {
			// start[0] = new RealVector(new RealNumber[] { new RealNumber(10),
			// new RealNumber(0) });
			// vel[0] = new RealVector(new RealNumber[] { new RealNumber(-4),
			// new RealNumber(3) });
			//
			// start[1] = new RealVector(new RealNumber[] { new RealNumber(9),
			// new RealNumber(0) });
			// vel[1] = new RealVector(new RealNumber[] { new RealNumber(-4),
			// new RealNumber(-3) });
			//
			// start[2] = new RealVector(new RealNumber[] { new RealNumber(8),
			// new RealNumber(0) });
			// vel[2] = new RealVector(new RealNumber[] { new RealNumber(-10),
			// new RealNumber(-2) });

			// projection onto space coordinates
			RealNumber[][] proj1 = { { new RealNumber(1), new RealNumber(0), new RealNumber(0), new RealNumber(0) },
					{ new RealNumber(0), new RealNumber(1), new RealNumber(0), new RealNumber(0), } };

			// projection onto velocity coordinates
			RealNumber[][] proj2 = { { new RealNumber(0), new RealNumber(0), new RealNumber(1), new RealNumber(0) },
					{ new RealNumber(0), new RealNumber(0), new RealNumber(0), new RealNumber(1), } };

			// projection onto X space/velocity coordinates
			RealNumber[][] proj3 = { { new RealNumber(1), new RealNumber(0), new RealNumber(0), new RealNumber(0) },
					{ new RealNumber(0), new RealNumber(0), new RealNumber(1), new RealNumber(0), } };

			// projection onto Y space/velocity coordinates
			RealNumber[][] proj4 = { { new RealNumber(0), new RealNumber(1), new RealNumber(0), new RealNumber(0) },
					{ new RealNumber(0), new RealNumber(0), new RealNumber(0), new RealNumber(1), } };

			// projection onto X-Y space/velocity coordinates
			RealNumber[][] proj5 = { { new RealNumber(new Double(1 / Math.sqrt(2)).floatValue()),
					new RealNumber(new Double(1 / Math.sqrt(2)).floatValue()), new RealNumber(0), new RealNumber(0) },
					{ new RealNumber(0), new RealNumber(0), new RealNumber(new Double(1 / Math.sqrt(2)).floatValue()),
							new RealNumber(new Double(1 / Math.sqrt(2)).floatValue()) } };

			FiniteMatrix projection = new FiniteMatrix(RealNumbers.getInstance(), proj1);

			RealBasedVector start = new RealBasedVector(
					new RealVector(new RealNumber[] { new RealNumber(10), new RealNumber(0) }));
			RealBasedVector vel = new RealBasedVector(
					new RealVector(new RealNumber[] { new RealNumber(-4), new RealNumber(3) }));

			configs.add(new PhaseSpaceStartConfiguration(Color.BLUE, Color.BLACK, start, vel));

			start = new RealBasedVector(new RealVector(new RealNumber[] { new RealNumber(9), new RealNumber(0) }));
			vel = new RealBasedVector(new RealVector(new RealNumber[] { new RealNumber(-4), new RealNumber(-3) }));

			configs.add(new PhaseSpaceStartConfiguration(Color.GREEN, Color.BLACK, start, vel));

			start = new RealBasedVector(new RealVector(new RealNumber[] { new RealNumber(8), new RealNumber(0) }));
			vel = new RealBasedVector(new RealVector(new RealNumber[] { new RealNumber(-10), new RealNumber(-2) }));

			configs.add(new PhaseSpaceStartConfiguration(Color.RED, Color.BLACK, start, vel));

			FiniteDimensionalLinearMap projMap = new FiniteDimensionalLinearMap(
					RnSpace.getInstance(projection.getColumnNumber()).getStandardBasis(),
					RnSpace.getInstance(2).getStandardBasis(), projection);

			RealBasedVisualization curves = new RealBasedCurvesProjection(new PhaseSpaceCurvesApproximation(configs, 2,
					100, 2, new CentralForceField(2, 500, new Double(-2).floatValue())), projMap);

			return new RealBasedCurves2D(curves, functionType);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Visualization2D phaseSpaceMultiparticle() {

		PhaseSpaceParticleConfiguration[] config = new PhaseSpaceParticleConfiguration[3];

		try {

			// 2 Körper mit vergleichbaren Parameters
			// RealNumber[] origin = { new RealNumber(5), new RealNumber(0), new
			// RealNumber(1) };
			// RealNumber[] velocity = { new RealNumber(0), new RealNumber(0),
			// new RealNumber(0) };
			// config[0] = new PhaseSpaceParticleConfiguration(Color.BLUE,
			// Color.BLACK, origin, velocity, 500, 100);
			//
			// origin = new RealNumber[]{ new RealNumber(0), new RealNumber(5),
			// new RealNumber(10) };
			// velocity = new RealNumber[]{ new RealNumber(-5), new
			// RealNumber(0), new RealNumber(1) };
			// config[1] = new PhaseSpaceParticleConfiguration(Color.CYAN,
			// Color.BLACK, origin, velocity, 100, 100);

			// Ein schwerer, ein leichter Körper
			// RealNumber[] origin = { new RealNumber(0), new RealNumber(0), new
			// RealNumber(0) };
			// RealNumber[] velocity = { new RealNumber(0), new RealNumber(0),
			// new RealNumber(0) };
			// config[0] = new PhaseSpaceParticleConfiguration(Color.BLUE,
			// Color.BLACK, origin, velocity, 1000, 1000);
			//
			// origin = new RealNumber[]{ new RealNumber(10), new RealNumber(0),
			// new RealNumber(10) };
			// velocity = new RealNumber[]{ new RealNumber(0), new
			// RealNumber(10), new RealNumber(0) };
			// config[1] = new PhaseSpaceParticleConfiguration(Color.CYAN,
			// Color.BLACK, origin, velocity, new Double(10).floatValue(), 10);

			// 3 Körper: Ein schwerer, zwei leichte Körper Mache zuerst beide
			// unabhängig voneinander
			RealNumber[] origin = { new RealNumber(0), new RealNumber(0), new RealNumber(0) };
			RealNumber[] velocity = { new RealNumber(0), new RealNumber(0), new RealNumber(0) };
			config[0] = new PhaseSpaceParticleConfiguration(Color.BLUE, Color.BLACK, origin, velocity, 100000,
					10000000);

			origin = new RealNumber[] { new RealNumber(10), new RealNumber(0), new RealNumber(0) };
			velocity = new RealNumber[] { new RealNumber(0), new RealNumber(110), new RealNumber(0) };
			config[1] = new PhaseSpaceParticleConfiguration(Color.CYAN, Color.BLACK, origin, velocity,
					new Double(10).floatValue(), 10);
			//
			origin = new RealNumber[] { new RealNumber(-40), new RealNumber(0), new RealNumber(0) };
			velocity = new RealNumber[] { new RealNumber(0), new RealNumber(new Double(40).floatValue()),
					new RealNumber(0) };
			config[2] = new PhaseSpaceParticleConfiguration(Color.red, Color.BLACK, origin, velocity,
					new Double(10).floatValue(), 10);

			PhaseSpaceMultiParticleConfiguration configs = new PhaseSpaceMultiParticleConfiguration(config);

			ArrayList<PhaseSpaceStartConfiguration> starts = new ArrayList<PhaseSpaceStartConfiguration>(1);
			starts.add(configs.getStart());

			// Projection from 3-Phase Space to x-y coordinates
			RealNumber[][] proj1 = {
					{ new RealNumber(1), new RealNumber(0), new RealNumber(0), new RealNumber(0), new RealNumber(0),
							new RealNumber(0) },
					{ new RealNumber(0), new RealNumber(1), new RealNumber(0), new RealNumber(0), new RealNumber(0),
							new RealNumber(0), } };

			// projection onto x y velocity coordinates
			RealNumber[][] proj2 = {
					{ new RealNumber(0), new RealNumber(0), new RealNumber(0), new RealNumber(1), new RealNumber(0),
							new RealNumber(0) },
					{ new RealNumber(0), new RealNumber(0), new RealNumber(0), new RealNumber(0), new RealNumber(1),
							new RealNumber(0) } };

			// projection onto X space/velocity coordinates
			RealNumber[][] proj3 = {
					{ new RealNumber(1), new RealNumber(0), new RealNumber(0), new RealNumber(0), new RealNumber(0),
							new RealNumber(0) },
					{ new RealNumber(0), new RealNumber(0), new RealNumber(0), new RealNumber(1), new RealNumber(0),
							new RealNumber(0), } };

			FiniteMatrix matrix = new FiniteMatrix(RealNumbers.getInstance(), proj1);
			FiniteDimensionalLinearMap map2d = new FiniteDimensionalLinearMap(new PhaseSpace(3).getStandardBasis(),
					RnSpace.getInstance(2).getStandardBasis(), matrix);

			RealBasedVisualization curves = new RealBasedCurvesMultiProjection(
					new PhaseSpaceCurvesApproximation(starts, 100, 500, configs.getDimension(),
							new MultiPointParticle(configs.getUnitStrengths(), configs.getWeights(), -2, 1)),
					configs.getProjects(), map2d);

			return new RealBasedCurves2D(curves, functionType);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static RealBasedCurves2DMultiProjection manifoldCoordinates() {

		RealNumber[][] proj1 = { { new RealNumber(1), new RealNumber(0), new RealNumber(0) },
				{ new RealNumber(0), new RealNumber(1), new RealNumber(0) } };

		RealNumber[][] proj2 = { { new RealNumber(1), new RealNumber(0), new RealNumber(0) },
				{ new RealNumber(0), new RealNumber(0), new RealNumber(1) } };

		RealNumber[][] proj3 = {
				{ new RealNumber(new Double(0.5).floatValue()), new RealNumber(new Double(0.5).floatValue()), 
					new RealNumber(new Double(1 / Math.sqrt(2)).floatValue()) },
				{ new RealNumber(new Double(-0.5).floatValue()), new RealNumber(new Double(-0.5).floatValue()), 
						new RealNumber(new Double(1 / Math.sqrt(2)).floatValue()) } };
//		RealNumber[][] proj3 = {
//				{ new RealNumber(1/2), new RealNumber(1/2), new RealNumber(new Double(1 / Math.sqrt(2)).floatValue()) },
//				{ new RealNumber(-1/2), new RealNumber(-1/2), new RealNumber(new Double(1 / Math.sqrt(2)).floatValue()) } };
		
		FiniteMatrix matrix = new FiniteMatrix(RealNumbers.getInstance(), proj1);
		FiniteDimensionalLinearMap map2d = new FiniteDimensionalLinearMap(RnSpace.getInstance(3).getStandardBasis(),
				RnSpace.getInstance(2).getStandardBasis(), matrix);

		Projection2DConfiguration[] config = {
				new Projection2DConfiguration(Color.BLACK, Color.BLACK, map2d, new Coordinates2D(-1, 1, -1, 1)) };

		ArrayList<Chart> charts = new ArrayList<Chart>();
//		charts.add(new Sphere2ChartPolar());
//		charts.add(new Torus2Chart());
		charts.add(new Sphere2ChartProj());

		RealBasedCurves2DMultiProjection proj = new RealBasedCurves2DMultiProjection(new Sphere2Manifold(charts, 0, 10), config);
		proj.setFunctionTypeCoordinates(GraphWizard.FunctionType.LINE_STRAIGHT);;
		return proj;
	}
}
