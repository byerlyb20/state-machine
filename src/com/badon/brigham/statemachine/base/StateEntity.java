package com.badon.brigham.statemachine.base;

/**
 * Superclass used to define a state-based object
 * @author byerlybrigham
 *
 */
public abstract class StateEntity {
	
	private State mCurrentState;
	
	private StateManager mStateManager;
	
	public StateEntity(StateManager stateManager) {
		mCurrentState = getDefaultState();
		
		mStateManager = stateManager;
		mStateManager.registerStateEntity(this);
	}
	
	/**
	 * Will add a given State to the StateManager queue, ensuring that all
	 * dependencies are satisfied prior to the activation of the given State
	 * @param goal the State to enter
	 * @param listener Optional callback which will be called when the State
	 * becomes active
	 */
	public void requestState(State goal, StateEnteredListener listener) {
		mStateManager.queue(new StateRequest(goal, listener));
	}
	
	/**
	 * Used to get the current State of the StateEntity
	 * 
	 * <br>NB: This could be a transitioning State that is enforced by the 
	 * StateManager
	 * @return the current State
	 */
	public State getCurrentState() {
		return mCurrentState;
	}
	
	protected abstract void enterState(State goal);
	
	protected abstract State getDefaultState();
	
	void forceState(State in) {
		mCurrentState = in;
	}

}
