package com.gruber.pfr.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.BoxLayout;
import javax.swing.JApplet;
import javax.swing.JPanel;

import com.gruber.pfr.graphics.elements.Coordinates2D;
import com.gruber.pfr.graphics.elements.SimpleVector;

public class GraphWizard extends JApplet {

	public static enum FunctionType {

		SINGLE_POINT, SINGLE_POINT_TANGENT, LINE_STRAIGHT, LINE_STRAIGHT_TANGENT;// ,
																					// LINE_QUADRATIC,
																					// LINE_CUBIC
	}

	class GraphicsSettings {

		Visualization2D vis;
		Dimension dim;
//		FunctionType type;
		int totalLength;
		List<SimpleCurve> paths;

		public List<SimpleCurve> getPaths() {
			return paths;
		}

		public void setPaths(List<SimpleCurve> paths) {
			this.paths = paths;
		}

		public void setVis(Visualization2D vis) {
			this.vis = vis;
		}

		public Dimension getDim() {
			return dim;
		}

		public void setDim(Dimension dim) {
			this.dim = dim;
		}

//		public FunctionType getType() {
//			return type;
//		}
//
//		public void setType(FunctionType type) {
//			this.type = type;
//		}

		public int getTotalLength() {
			return totalLength;
		}

		public void setTotalLength(int totalLength) {
			this.totalLength = totalLength;
		}

	}

	class GraphPainter extends Component {

		GraphicsSettings settings;

		public GraphPainter(GraphicsSettings settings) {
			super();
			this.settings = settings;
		}

		public Dimension getPreferredSize() {
			return this.settings.dim;
		}

		public void paint(Graphics g) {

			Graphics2D g2 = (Graphics2D) g;
			Composite origComposite;

			origComposite = g2.getComposite();

			// draw the functions
			Iterator<SimpleCurve> paths = this.settings.getPaths().iterator();

			float[] sizeFact = drawCoordinates(g2);

			Coordinates2D coord = this.settings.vis.getCoordinates();
			float offX = coord.getMinX(); 
			float offY = coord.getMaxY();

		while (paths.hasNext()) {

				SimpleCurve curve = paths.next();
				// List<SimpleVector> points = curve.getVectors().iterator();
				if (!curve.hasNext())
					continue;

				SimpleVector vec = curve.getNext(); // the first point

				if (vec != null) {

					GeneralPath path = new GeneralPath();

					for (int count = 0; count < curve.getLength() / this.settings.getTotalLength(); count++) {

						if (curve.hasNext()) {
							vec = curve.getNext();
							float posX = (vec.getOrigin().getCoordinates()[0] - offX) * sizeFact[0] + 10;
							float posY = (offY - vec.getOrigin().getCoordinates()[1]) * sizeFact[1] + 10;

							if (curve.getFunctionType().equals(FunctionType.SINGLE_POINT)
									|| curve.getFunctionType().equals(FunctionType.SINGLE_POINT_TANGENT))
								path.moveTo(posX, posY);

							g2.setColor(curve.getOriginColor());
							g2.draw(new Ellipse2D.Float(posX - 1, posY - 1, 2, 2));
							path.lineTo(posX, posY);

							if (vec.getDirection() != null
									&& (curve.getFunctionType().equals(FunctionType.SINGLE_POINT_TANGENT)
											|| curve.getFunctionType().equals(FunctionType.LINE_STRAIGHT_TANGENT))) {
								float vecX = posX + vec.getDirection().getCoordinates()[0] * sizeFact[0];
								float vecY = posY - vec.getDirection().getCoordinates()[1] * sizeFact[1];
								g2.setColor(curve.getDirectionColor());
								g2.draw(new Line2D.Float(posX, posY, vecX, vecY));
							}
						}
					}
					g2.setColor(curve.getOriginColor());
					g2.draw(path);
				}
			}
			g2.setComposite(origComposite);

		}
	}

	GraphicsSettings settings;
	int length;

	public GraphWizard(Visualization2D vis, Dimension dim, boolean animate) {

		super();

		this.settings = new GraphicsSettings();
		this.settings.setVis(vis);
		this.settings.setDim(dim);
//		this.settings.setType(type);
		this.settings.setPaths(vis.getCurves());

		if (animate)
			this.length = vis.getLength();
		else
			this.length = 1;
		this.settings.setTotalLength(this.length);

	}

	protected float[] drawCoordinates(Graphics2D g2) {

		Coordinates2D coord = this.settings.vis.getCoordinates();

		// transform the coordinates
		int offX = coord.getMinX();
		int offY = coord.getMaxY();
		int sizeX = coord.getMaxX() - coord.getMinX();
		int sizeY = coord.getMaxY() - coord.getMinY();

		float[] sizeFact = new float[2];
		sizeFact[0] = new Float(this.settings.dim.getWidth() - 100).floatValue() / new Float(sizeX).floatValue();
		sizeFact[1] = new Float(this.settings.dim.getHeight() - 100).floatValue() / new Float(sizeY).floatValue();

		// draw the coordinate system
		g2.setColor(Color.BLACK);
		float cX;
		float cY;

		if (coord.getMaxX() > 0 && coord.getMinX() < 0)
			cX = -offX * sizeFact[0];
		else if (coord.getMaxX() < 0)
			cX = 1;
		else
			cX = sizeX - 1;

		if (coord.getMaxY() > 0 && coord.getMinY() < 0)
			cY = offY * sizeFact[1];
		else if (coord.getMaxY() < 0)
			cY = sizeY;
		else
			cY = 0;
		g2.draw(new Line2D.Float(cX + 10, 0, cX + 10, new Float(this.settings.dim.getHeight()).floatValue()));
		g2.draw(new Line2D.Float(0, cY + 10, new Float(this.settings.dim.getWidth()).floatValue(), cY + 10));

		return sizeFact;
	}

	GraphPainter graphP;

	public void start() {

		setLayout(new BorderLayout());

		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		add("North", p);

		graphP = new GraphPainter(this.settings);
		p.add("Center", graphP);
	}

	public void paint(Graphics g) {

		while (length > 0) {
			super.paint(g);
			length--;
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
