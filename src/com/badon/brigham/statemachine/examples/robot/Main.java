package com.badon.brigham.statemachine.examples.robot;

import java.io.Console;

import com.badon.brigham.statemachine.base.StateEnteredListener;

public class Main {
	
	private static final boolean DEBUG = true;
	private static final String[] COMMAND_LIST = {"armCollect", "armUp", "carriageDormant"};
	private static int commandIteration = 0;
	
	public static StateEnteredListener mListener = new StateEnteredListener() {

		@Override
		public void onStateEntered() {
			waitForCommand();
		}
		
	};

	public static void main(String[] args) {
		Robot robot = new Robot();
		
		if (!checkRunningContext()) {
			printInstructions();
			waitForCommand();
		}
	}
	
	@SuppressWarnings("unused")
	private static boolean checkRunningContext() {
		Console console = System.console();
		if (!DEBUG && console == null) {
			System.out.println("Demo must be run outside of the IDE");
			return true;
		}
		return false;
	}
	
	private static void printInstructions() {
		if (DEBUG) {
			return;
		}
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
		String input;
		if (DEBUG) {
			if (commandIteration < COMMAND_LIST.length) {
				input = COMMAND_LIST[commandIteration];
				commandIteration++;
			} else {
				return;
			}
		} else {
			System.out.print("\nEnter a command: ");
			Console console = System.console();
			input = System.console().readLine();
		}
		
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
		default: {
			System.out.println(" *** Unknown command *** ");
			waitForCommand();
		}
		}
	}
}
