package com.badon.brigham.statemachine.examples.robot;

import com.badon.brigham.statemachine.base.State;
import com.badon.brigham.statemachine.base.StateEntity;
import com.badon.brigham.statemachine.base.StateManager;

public class Arm extends StateEntity {
	
	public static final State STATE_UP = new State("armUp", Arm.class);
	public static final State STATE_COLLECT = new State("collect", Arm.class);
	public static final State STATE_DOWN = new State("armDown", Arm.class);
	public static final State STATE_PULL_IN = new State("armPull", Arm.class);

	public Arm(StateManager stateManager) {
		super(stateManager);
	}

	@Override
	protected void enterState(State goal) {
		if (goal.equals(STATE_UP)) {
			System.out.println("Arm is up");
		} else if (goal.equals(STATE_COLLECT)) {
			System.out.println("Arm is down and collecting");
		} else if (goal.equals(STATE_DOWN)) {
			System.out.println("Arm is down, but not collecting");
		} else if (goal.equals(STATE_PULL_IN)) {
			System.out.println("The arm is pulling in a cube");
		}
	}

	@Override
	protected State getDefaultState() {
		return STATE_UP;
	}

}
