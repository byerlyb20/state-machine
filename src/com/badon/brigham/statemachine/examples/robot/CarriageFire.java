package com.badon.brigham.statemachine.examples.robot;

import com.badon.brigham.statemachine.base.State;
import com.badon.brigham.statemachine.base.StateEntity;
import com.badon.brigham.statemachine.base.StateManager;

public class CarriageFire extends StateEntity {
	
	public static final State STATE_FIRE = new State("fire", CarriageFire.class);
	public static final State STATE_RETRACT = new State("retract", CarriageFire.class);

	public CarriageFire(StateManager stateManager) {
		super(stateManager);
	}

	@Override
	protected void enterState(State goal) {
		if (goal.equals(STATE_FIRE)) {
			System.out.println("Carriage is firing");
		} else if (goal.equals(STATE_RETRACT)) {
			System.out.println("Carriage is not firing");
		}
	}

	@Override
	protected State getDefaultState() {
		return STATE_RETRACT;
	}

}
