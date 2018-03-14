package com.badon.brigham.statemachine.examples.robot;

import com.badon.brigham.statemachine.base.State;
import com.badon.brigham.statemachine.base.StateEntity;
import com.badon.brigham.statemachine.base.StateManager;

public class CarriageVertical extends StateEntity {
	
	public static final State STATE_UP = new State("carriageUp", CarriageVertical.class);
	public static final State STATE_DOWN = new State("carriageDown", CarriageVertical.class);

	public CarriageVertical(StateManager stateManager) {
		super(stateManager);
	}

	@Override
	protected void enterState(State goal) {
		if (goal.equals(STATE_UP)) {
			System.out.println("Carriage is up");
		} else if (goal.equals(STATE_DOWN)) {
			System.out.println("Carriage is down");
		}
	}

	@Override
	protected State getDefaultState() {
		return STATE_DOWN;
	}

}
