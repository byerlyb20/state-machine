package com.badon.brigham.statemachine.examples.robot;

import com.badon.brigham.statemachine.base.StateManager;
import com.badon.brigham.statemachine.base.Transition;

/**
 * This is a pseudo-robot class example that illustrates how one might
 * implement these state libraries with the actual WPILib Robot class
 * @author byerlybrigham
 *
 */
public class Robot {
	
	private StateManager mStateManager;

	// NB: The following are of type StateEntity, although they are
	// intended to model the type Subsystem
	/**
	 * Top-level StateEntity which manages all of the lower level states
	 * for the Arm assembly
	 */
	public static Arm arm;
	private static ArmMotor armMotor;
	private static ArmHorizontal armHorizontal;
	private static ArmVertical armVertical;
	
	/**
	 * Top-level StateEntity which manages all of the lower level states
	 * for the Carriage assembly
	 */
	public static Carriage carriage;
	private static CarriageFire carriageFire;
	private static CarriageVertical carriageVertical;
	
	public Robot() {
		mStateManager = new StateManager();
		
		arm = new Arm(mStateManager);
		armMotor = new ArmMotor(mStateManager);
		armHorizontal = new ArmHorizontal(mStateManager);
		armVertical = new ArmVertical(mStateManager);
		
		carriage = new Carriage(mStateManager);
		carriageFire = new CarriageFire(mStateManager);
		carriageVertical = new CarriageVertical(mStateManager);
		
		registerDependencies();
	}
	
	private void registerDependencies() {
		// Pair all lower-level state entities with their parents to simulate
		// a sort of state hierarchy
		mStateManager.registerDependency(Arm.STATE_COLLECT, ArmHorizontal.STATE_OUT);
		mStateManager.registerDependency(Arm.STATE_COLLECT, ArmVertical.STATE_DOWN);
		mStateManager.registerDependency(Arm.STATE_COLLECT, ArmMotor.STATE_ON);

		mStateManager.registerDependency(Arm.STATE_DOWN, ArmMotor.STATE_OFF);
		mStateManager.registerDependency(Arm.STATE_DOWN, ArmHorizontal.STATE_OUT);
		mStateManager.registerDependency(Arm.STATE_DOWN, ArmVertical.STATE_DOWN);

		mStateManager.registerDependency(Arm.STATE_UP, ArmMotor.STATE_OFF);
		mStateManager.registerDependency(Arm.STATE_UP, ArmHorizontal.STATE_OUT);
		mStateManager.registerDependency(Arm.STATE_UP, ArmVertical.STATE_UP);

		mStateManager.registerDependency(Arm.STATE_PULL_IN, ArmHorizontal.STATE_IN);
		mStateManager.registerDependency(Arm.STATE_PULL_IN, ArmVertical.STATE_DOWN);
		mStateManager.registerDependency(Arm.STATE_PULL_IN, ArmMotor.STATE_ON);
		
		mStateManager.registerDependency(Carriage.STATE_FIRE_UP, CarriageVertical.STATE_UP);
		mStateManager.registerDependency(Carriage.STATE_FIRE_UP, CarriageFire.STATE_FIRE);

		mStateManager.registerDependency(Carriage.STATE_FIRE_DOWN, CarriageVertical.STATE_DOWN);
		mStateManager.registerDependency(Carriage.STATE_FIRE_DOWN, CarriageFire.STATE_FIRE);

		mStateManager.registerDependency(Carriage.STATE_DORMANT, CarriageFire.STATE_RETRACT);

		// Whenever the arms are moving up or down, they must be in
		Transition goingUp = new Transition(ArmVertical.STATE_DOWN, ArmVertical.STATE_UP);
		mStateManager.registerDependency(goingUp, ArmHorizontal.STATE_IN);
		Transition goingDown = new Transition(ArmVertical.STATE_UP, ArmVertical.STATE_DOWN);
		mStateManager.registerDependency(goingDown, ArmHorizontal.STATE_IN);
		
		// Whenever we fire a cube, we want the arms to be out so that the cube's
		// trajectory is not blocked
		mStateManager.registerDependency(CarriageFire.STATE_FIRE, ArmHorizontal.STATE_OUT);

		// Whenever the carriage is moving up or down, the arms must be out
		Transition carriageGoingUp = new Transition(CarriageVertical.STATE_DOWN, CarriageVertical.STATE_UP);
		mStateManager.registerDependency(carriageGoingUp, ArmHorizontal.STATE_OUT);
		Transition carriageGoingDown = new Transition(CarriageVertical.STATE_UP, CarriageVertical.STATE_DOWN);
		mStateManager.registerDependency(carriageGoingDown, ArmHorizontal.STATE_OUT);
	}
}
