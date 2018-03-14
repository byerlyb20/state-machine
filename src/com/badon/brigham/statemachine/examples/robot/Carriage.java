package com.badon.brigham.statemachine.examples.robot;

import com.badon.brigham.statemachine.base.State;
import com.badon.brigham.statemachine.base.StateEntity;
import com.badon.brigham.statemachine.base.StateManager;

public class Carriage extends StateEntity {
	
	public static final State STATE_FIRE_UP = new State("fireUp", Carriage.class);
	public static final State STATE_FIRE_DOWN = new State("fireDown", Carriage.class);
	public static final State STATE_DORMANT = new State("dormant", Carriage.class);

	public Carriage(StateManager stateManager) {
		super(stateManager);
	}

	@Override
	protected void enterState(State goal) {
		if (goal.equals(STATE_FIRE_UP)) {
			System.out.println("We are firing into the switch");
		} else if (goal.equals(STATE_FIRE_DOWN)) {
			System.out.println("We are firing into the vault");
		} else if (goal.equals(STATE_DORMANT)) {
			System.out.println("We are not firing");
		}
	}

	@Override
	protected State getDefaultState() {
		return STATE_DORMANT;
	}

}
