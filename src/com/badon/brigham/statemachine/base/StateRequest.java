package com.badon.brigham.statemachine.base;

/**
 * Represents an intent to enter a state
 * @author byerlybrigham
 *
 */
public class StateRequest {
	
	private State mState;
	private StateEnteredListener mListener;
	
	public StateRequest(State state) {
		mState = state;
	}
	
	public StateRequest(State state, StateEnteredListener listener) {
		mState = state;
		mListener = listener;
	}
	
	/**
	 * Gets the intended goal State
	 * @return the intended goal
	 */
	public State getState() {
		return mState;
	}
	
	StateEnteredListener getListener() {
		return mListener;
	}

}
