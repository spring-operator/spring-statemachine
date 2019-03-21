/*
 * Copyright 2016 the original author or authors.
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
package demo.deploy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateContext.Stage;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

public class StateMachineLogListener extends StateMachineListenerAdapter<String, String> {

	private final List<String> messages = new ArrayList<String>();

	public List<String> getMessages() {
		return messages;
	}

	public void resetMessages() {
		messages.clear();
	}

	@Override
	public void stateEntered(State<String, String> state) {
//		messages.add("Enter " + state.getId());
	}

	@Override
	public void stateExited(State<String, String> state) {
//		messages.add("Exit " + state.getId());
	}

	@Override
	public void stateContext(StateContext<String, String> stateContext) {
		if (stateContext.getStage() == Stage.STATE_ENTRY) {
			messages.add("Enter " + stateContext.getTarget().getId());
			if (stateContext.getTarget().getId().equals("ERROR")) {
				messages.add("ERROR got exception " + stateContext.getExtendedState().getVariables().get("error"));
			}
		} else if (stateContext.getStage() == Stage.STATE_EXIT) {
			messages.add("Exit " + stateContext.getSource().getId());
		}
	}

}
