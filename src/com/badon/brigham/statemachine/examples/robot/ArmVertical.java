package com.badon.brigham.statemachine.examples.robot;

import com.badon.brigham.statemachine.base.State;
import com.badon.brigham.statemachine.base.StateEntity;
import com.badon.brigham.statemachine.base.StateManager;

public class ArmVertical extends StateEntity {
	
	public static final State STATE_UP = new State("up", ArmVertical.class);
	public static final State STATE_DOWN = new State("down", ArmVertical.class);

	public ArmVertical(StateManager stateManager) {
		super(stateManager);
	}

	@Override
	protected void enterState(State goal) {
		if (goal.equals(STATE_UP)) {
			System.out.println("Arms are up");
		} else if (goal.equals(STATE_DOWN)) {
			System.out.println("Arms are down");
		}
	}

	@Override
	protected State getDefaultState() {
		return STATE_UP;
	}

}
