package com.badon.brigham.statemachine.examples.robot;

import com.badon.brigham.statemachine.base.State;
import com.badon.brigham.statemachine.base.StateEntity;
import com.badon.brigham.statemachine.base.StateManager;

public class ArmHorizontal extends StateEntity {
	
	public static final State STATE_OUT = new State("out", ArmHorizontal.class);
	public static final State STATE_IN = new State("in", ArmHorizontal.class);

	public ArmHorizontal(StateManager stateManager) {
		super(stateManager);
	}

	@Override
	protected void enterState(State goal) {
		if (goal.equals(STATE_OUT)) {
			System.out.println("Arms are out");
		} else if (goal.equals(STATE_IN)) {
			System.out.println("Arms are in");
		}
	}

	@Override
	protected State getDefaultState() {
		return STATE_OUT;
	}

}
