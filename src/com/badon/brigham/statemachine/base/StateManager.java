package com.badon.brigham.statemachine.base;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Manages all states, ensuring all dependencies are met and executing requests
 * in a queue running on a separate Thread
 * 
 * <br>NB: It is recommended that every application create only one instance of
 * StateManager
 * @author byerlybrigham
 *
 */
public class StateManager implements Runnable {

	private volatile boolean mIsRunning;
	private volatile ArrayList<StateRequest> mQueue;
	private volatile HashMap<String, ArrayList<State>> mDependencies;

	private volatile HashMap<Class<? extends StateEntity>, StateEntity> mKnownStateEntities;

	public StateManager() {
		mIsRunning = true;
		mQueue = new ArrayList<StateRequest>();
		mDependencies = new HashMap<String, ArrayList<State>>();

		mKnownStateEntities = new HashMap<Class<? extends StateEntity>, StateEntity>();

		Thread thread = new Thread(this);
		thread.start();
	}

	/**
	 * Links a StateEntity to the StateManager
	 * 
	 * <br><br><b>MUST</b> be called for every StateEntity that is to be managed by this
	 * StateManager
	 * @param in StateEntity to register
	 */
	public void registerStateEntity(StateEntity in) {
		Class<? extends StateEntity> inClass = (Class<? extends StateEntity>) in.getClass();
		mKnownStateEntities.put(inClass, in);
	}

	/**
	 * Makes the StateManager aware of dependencies that any given State may have
	 * @param event State (could be a Transition) that requires a certain State
	 * @param requirement State that must be active for the event to be active
	 */
	public void registerDependency(State event, State requirement) {
		ArrayList<State> eventDependencies = mDependencies.get(event.getName());
		if (eventDependencies == null) {
			eventDependencies = new ArrayList<State>();
			mDependencies.put(event.getName(), eventDependencies);
		}

		eventDependencies.add(requirement);
	}

	@Override
	public void run() {
		while (mIsRunning) {
			queueProcess: if (!mQueue.isEmpty()) {
				// Get all relevant data about the new requested state
				StateRequest request = mQueue.get(0);
				State goal = request.getState();

				// If the state is already active, we can move on to the next item
				// in the queue
				if (isStateActive(goal)) {
					mQueue.remove(0);
					break queueProcess;
				}

				if (goal instanceof Transition) {
					// Lets see if we can begin the transition by ensuring that all
					// dependencies are satisfied
					ArrayList<State> dependencies = getDependencies(goal);
					if (dependencies != null) {
						for (State a : dependencies) {
							if (!isStateActive(a)) {
								// Let's request that the state be active
								mQueue.add(0, new StateRequest(a));
								break queueProcess;
							}
						}
					}
					// If we've reached this point, all dependencies have been met
					// We are now in the transition state
					getStateParent(goal).forceState(goal);
				} else {
					// Let's request to transition to a given state, if we have
					// not already done so
					StateEntity goalParent = getStateParent(goal);
					State current = goalParent.getCurrentState();
					if (!(current instanceof Transition) || !((Transition) current).getSecondState().equals(goal)) {
						Transition transition = new Transition(current, goal);
						mQueue.add(0, new StateRequest(transition));
					} else {
						// We are now transitioning into the cursor State

						// Lets see if we can enter the state by ensuring that all
						// dependencies are satisfied
						ArrayList<State> dependencies = getDependencies(goal);
						if (dependencies != null) {
							for (State a : dependencies) {
								if (!isStateActive(a)) {
									// Let's request that the state be active
									mQueue.add(0, new StateRequest(a));
									break queueProcess;
								}
							}
						}
						// If we've reached this point, all dependencies have been met
						getStateParent(goal).enterState(goal);
						// We are now in the cursor state
						getStateParent(goal).forceState(goal);
						if (request.getListener() != null) {
							request.getListener().onStateEntered();
						}
					}
				}
			} else {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private StateEntity getStateParent(State in) {
		return mKnownStateEntities.get(in.getParent());
	}

	private ArrayList<State> getDependencies(State state) {
		return mDependencies.get(state.getName());
	}

	private boolean isStateActive(State state) {
		StateEntity parent = getStateParent(state);
		return parent.getCurrentState().equals(state);
	}

	void queue(StateRequest stateRequest) {
		mQueue.add(stateRequest);
	}

}
