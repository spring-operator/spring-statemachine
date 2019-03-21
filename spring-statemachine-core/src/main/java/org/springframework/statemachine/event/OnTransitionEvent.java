/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.statemachine.event;

import org.springframework.statemachine.transition.Transition;

/**
 * Event representing that a {@link Transition} has happened.
 *
 * @author Janne Valkealahti
 *
 */
@SuppressWarnings("serial")
public class OnTransitionEvent extends TransitionEvent {

	/**
	 * Instantiates a new on transition event.
	 *
	 * @param source the source
	 * @param transition the transition
	 */
	public OnTransitionEvent(Object source, Transition<?, ?> transition) {
		super(source, transition);
	}

	@Override
	public String toString() {
		return "OnTransitionEvent [transition=" + getTransition() + "]";
	}

}
