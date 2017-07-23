package com.gruber.pfr.space.vectors.knspaces.phasespace;

import java.util.ArrayList;
import java.util.Arrays;

import com.gruber.pfr.space.numbers.real.RealNumber;
import com.gruber.pfr.space.numbers.real.RealVector;

public class PhaseVector extends RealVector {

//	RealVector position;
//	RealVector velocity;
	
	int phaseDim;

	public PhaseVector(PhaseSpace space, RealNumber[] position, RealNumber[] velocity) throws InvalidElementsException {

		super(new RealNumber[position.length + velocity.length]);
		
		phaseDim = position.length;

//		this.position = new RealVector(position);
//		this.velocity = new RealVector(velocity);

		int len = position.length + velocity.length;
		ArrayList<RealNumber> list = new ArrayList<RealNumber>(len);
		list.addAll(Arrays.asList(position));
		list.addAll(Arrays.asList(velocity));
		RealNumber[] elements = list.toArray(new RealNumber[len]);

		this.setSpace(space);
		this.setElements(elements);
	}

	public RealVector getPosition() {
		
		try {
			RealNumber[] nums = new RealNumber[phaseDim];
			for(int i = 0; i < phaseDim; i ++)
				nums[i] = this.getElements()[i];
			
			return new RealVector(nums);
		} catch (InvalidElementsException e) {
			e.printStackTrace();
		return null;
		}
	}

	public RealVector getVelocity() {
		
		try {
			RealNumber[] nums = new RealNumber[phaseDim];
			for(int i = 0; i < phaseDim; i ++)
				nums[i] = this.getElements()[i + phaseDim];
			
			return new RealVector(nums);
		} catch (InvalidElementsException e) {
			e.printStackTrace();
		return null;
		}
	}

	public PhaseSpace getSpace() {
		return (PhaseSpace) super.getSpace();
	}
	
	public PhaseVector add(PhaseVector vector) {

		try {
			return new PhaseVector(this.getSpace(), this.getPosition().add(vector.getPosition()).getElements(),
					this.getVelocity().add(vector.getVelocity()).getElements());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public PhaseVector multiply(RealNumber scalar) {

		try {
			return new PhaseVector(this.getSpace(), this.getPosition().multiply(scalar).getElements(),
					this.getVelocity().multiply(scalar).getElements());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
