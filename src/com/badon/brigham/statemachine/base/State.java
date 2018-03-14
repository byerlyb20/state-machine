package com.badon.brigham.statemachine.base;

/**
 * Represents a State held by a StateEntity
 * @author byerlybrigham
 *
 */
public class State {
	
	private final String mName;
	private final Class<? extends StateEntity> mParent;
	
	public State(String name, Class<? extends StateEntity> parent) {
		mName = name;
		mParent = parent;
	}
	
	/**
	 * Used to get the text name of the State
	 * 
	 * <br>NB: All states managed by a StateManager should have unique names
	 * @return the name of the state
	 */
	public String getName() {
		return mName;
	}
	
	/**
	 * Used to find the StateEntity parent which can hold this state
	 * @return the StateEntity parent that can hold this state
	 */
	public Class<? extends StateEntity> getParent() {
		return mParent;
	}
	
	@Override
	public boolean equals(Object in) {
		if (in instanceof State) {
			State a = (State) in;
			return a.getName().equals(mName);
		} else {
			return false;
		}
	}

}
