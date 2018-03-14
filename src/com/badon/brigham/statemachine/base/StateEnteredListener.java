package com.badon.brigham.statemachine.base;

/**
 * Used as a callback for events in the StateManager
 * @author byerlybrigham
 *
 */
public interface StateEnteredListener {
	
	/**
	 * Called when the requested State is now active
	 * 
	 * <br>NB: This method is called from the same thread on which the
	 * scheduled states are called from
	 */
	void onStateEntered();

}
