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
	
	public List<Chart> getCoveringCharts(Set point) {
		
		ArrayList<Chart> cov = new ArrayList<Chart>();
		
		Iterator<Chart> iter = charts.iterator();
		while(iter.hasNext()) {
			Chart chart = iter.next();
			
			if(chart.isInDomain(point))
				cov.add(chart);
		}
		return cov;
	}

	public Manifold getManifold() {
		return manifold;
	}

	public void setManifold(Manifold manifold) {
		this.manifold = manifold;
	}
}
