package com.gruber.pfr.space.manifold;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.gruber.pfr.space.base.Set;

public class Atlas {

	List<Chart> charts;
	Manifold manifold;

	public Atlas(List<Chart> charts) {
		this.charts = charts;
	}

	public List<Chart> getCharts() {
		return charts;
	}

	public Manifold getManifold() {
		return manifold;
	}

	public void setManifold(Manifold manifold) {
		this.manifold = manifold;
	}

	public List<Chart> getChartsContainingPoint(Set point) {

		ArrayList<Chart> pointCharts = new ArrayList<Chart>();
		Iterator<Chart> iter = this.charts.iterator();
		while(iter.hasNext()) {
			
			Chart chart = iter.next();
			if(chart.isInDomain(point))
				pointCharts.add(chart);
		}
		
		return pointCharts;
	}
}
