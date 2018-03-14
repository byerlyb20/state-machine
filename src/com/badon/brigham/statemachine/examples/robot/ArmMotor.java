package com.badon.brigham.statemachine.examples.robot;

import com.badon.brigham.statemachine.base.State;
import com.badon.brigham.statemachine.base.StateEntity;
import com.badon.brigham.statemachine.base.StateManager;

public class ArmMotor extends StateEntity {
	
	public static final State STATE_ON = new State("on", ArmMotor.class);
	public static final State STATE_OFF = new State("off", ArmMotor.class);

	public ArmMotor(StateManager stateManager) {
		super(stateManager);
	}

	@Override
	protected void enterState(State goal) {
		if (goal.equals(STATE_ON)) {
			System.out.println("Collector motors are on");
		} else if (goal.equals(STATE_OFF)) {
			System.out.println("Collector motors are off");
		}
	}

	@Override
	protected State getDefaultState() {
		return STATE_OFF;
	}

}
