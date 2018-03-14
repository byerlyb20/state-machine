package com.badon.brigham.statemachine.base;

/**
 * Represents a Transition State, which the StateManager will subject any
 * StateEntity to when transfering from one State to another
 * @author byerlybrigham
 *
 */
public class Transition extends State {

	private final State mA;
	private final State mB;
	
	public Transition(State a, State b) {
		super(a.getName() + "-to-" + b.getName(), a.getParent());
		
		mA = a;
		mB = b;
	}
	
	/**
	 * Gets the first State in the Transition
	 * @return the first State in the Transition
	 */
	public State getFirstState() {
		return mA;
	}
	
	/**
	 * Gets the second State in the Transition
	 * @return the second State in the Transition
	 */
	public State getSecondState() {
		return mB;
	}
	
	@Override
	public boolean equals(Object in) {
		if (in instanceof Transition) {
			Transition a = (Transition) in;
			return a.getFirstState().equals(mA) && a.getSecondState().equals(mB);
		} else {
			return false;
		}
	}
}
