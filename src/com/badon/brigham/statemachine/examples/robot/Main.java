package com.badon.brigham.statemachine.examples.robot;

import com.badon.brigham.statemachine.base.StateEnteredListener;

public class Main {
	
	public static StateEnteredListener mListener = new StateEnteredListener() {

		@Override
		public void onStateEntered() {
			waitForCommand();
		}
		
	};

	public static void main(String[] args) {
		Robot robot = new Robot();
		
		printInstructions();
		waitForCommand();
	}
	
	private static void printInstructions() {
		System.out.println("Use the following commands to test the StateMachine library:");
		System.out.print("armCollect    ");
		System.out.print("armDown    ");
		System.out.print("armUp    ");
		System.out.print("armPullIn    ");
		System.out.print("carriageDormant    ");
		System.out.print("carriageFireUp    ");
		System.out.print("carriageFireDown\n");
		System.out.println("========");
	}
	
	private static void waitForCommand() {
		System.out.print("\nEnter a command: ");
		String input = System.console().readLine();
		switch (input) {
		case "armCollect": {
			Robot.arm.requestState(Arm.STATE_COLLECT, mListener);
			break;
		}
		case "armDown": {
			Robot.arm.requestState(Arm.STATE_DOWN, mListener);
			break;
		}
		case "armUp": {
			Robot.arm.requestState(Arm.STATE_UP, mListener);
			break;
		}
		case "armPullIn": {
			Robot.arm.requestState(Arm.STATE_PULL_IN, mListener);
			break;
		}
		case "carriageDormant": {
			Robot.carriage.requestState(Carriage.STATE_DORMANT, mListener);
			break;
		}
		case "carriageFireUp": {
			Robot.carriage.requestState(Carriage.STATE_FIRE_UP, mListener);
			break;
		}
		case "carriageFireDown": {
			Robot.carriage.requestState(Carriage.STATE_FIRE_DOWN, mListener);
			break;
		}
		}
	}
}
