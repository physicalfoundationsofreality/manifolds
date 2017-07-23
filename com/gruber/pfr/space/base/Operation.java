package com.gruber.pfr.space.base;

public abstract interface Operation {
	
	public class OperantException extends Exception{  
		
		Set failedOperant;
		
		public OperantException(Set failedOperant) {
			this.failedOperant = failedOperant;
		}
		public Set getFailedOperant() {
			return this.failedOperant;
		}
	};

	public Set getFirstOperantDomain();
	
	public Set getSecondOperantDomain();
	
	public Set getOperationRange();
	
	public Set operate(Set op1, Set op2) throws OperantException;
}
