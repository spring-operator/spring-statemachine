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
package org.springframework.statemachine.data.mongodb;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.keyvalue.core.KeyValueTemplate;
import org.springframework.statemachine.data.AbstractRepositoryTests;
import org.springframework.statemachine.transition.TransitionKind;

/**
 * MongoDb repository config tests.
 *
 * @author Janne Valkealahti
 */
public class MongoDbRepositoryTests extends AbstractRepositoryTests {

	@Rule
	public MongoDbRule MongoDbAvailableRule = new MongoDbRule();

	@Override
	protected void cleanInternal() {
		AnnotationConfigApplicationContext c = new AnnotationConfigApplicationContext();
		c.register(TestConfig.class);
		c.refresh();
		MongoTemplate template = c.getBean(MongoTemplate.class);
		template.dropCollection(MongoDbRepositoryAction.class);
		template.dropCollection(MongoDbRepositoryGuard.class);
		template.dropCollection(MongoDbRepositoryState.class);
		template.dropCollection(MongoDbRepositoryTransition.class);
		c.close();
	}

	@Test
	public void testPopulate1() {
		context.register(getRegisteredClasses());
		context.register(Config2.class);
		context.refresh();

		MongoDbStateRepository stateRepository = context.getBean(MongoDbStateRepository.class);
		MongoDbTransitionRepository transitionRepository = context.getBean(MongoDbTransitionRepository.class);
		assertThat(stateRepository.count(), is(3l));
		assertThat(transitionRepository.count(), is(3l));

		List<MongoDbRepositoryState> states = new ArrayList<>();
		stateRepository.findAll().iterator().forEachRemaining(states::add);
		List<MongoDbRepositoryTransition> transitions = new ArrayList<>();
		transitionRepository.findAll().iterator().forEachRemaining(transitions::add);
		assertThat(states.size(), is(3));
		assertThat(transitions.size(), is(3));
		MongoDbRepositoryTransition transition1 = transitions.get(0);
		assertThat(transition1.getSource(), notNullValue());
		assertThat(transition1.getTarget(), notNullValue());
	}

	@Test
	public void testRepository1() {
		context.register(getRegisteredClasses());
		context.refresh();

		MongoDbStateRepository statesRepository = context.getBean(MongoDbStateRepository.class);
		MongoDbRepositoryState stateS1 = new MongoDbRepositoryState("S1");
		MongoDbRepositoryState stateS2 = new MongoDbRepositoryState("S2");
		assertThat(statesRepository.count(), is(0l));

		statesRepository.save(stateS1);
		statesRepository.save(stateS2);
		assertThat(statesRepository.count(), is(2l));

		MongoDbTransitionRepository transitionsRepository = context.getBean(MongoDbTransitionRepository.class);
		MongoDbRepositoryTransition transition = new MongoDbRepositoryTransition(stateS1, stateS2, "E1");
		transition.setKind(TransitionKind.EXTERNAL);
		transitionsRepository.save(transition);

		assertThat(statesRepository.count(), is(2l));

		MongoDbRepositoryTransition transition2 = transitionsRepository.findAll().iterator().next();
		assertThat(transition2.getSource().getState(), is("S1"));
		assertThat(transition2.getTarget().getState(), is("S2"));
		assertThat(transition2.getEvent(), is("E1"));
		assertThat(transition2.getKind(), is(TransitionKind.EXTERNAL));

		List<MongoDbRepositoryState> findByMachineId = statesRepository.findByMachineId("");
		assertThat(findByMachineId.size(), is(2));

		context.close();
	}

	@Override
	protected Class<?>[] getRegisteredClasses() {
		return new Class<?>[] { TestConfig.class };
	}

	@Override
	protected AnnotationConfigApplicationContext buildContext() {
		return new AnnotationConfigApplicationContext();
	}

	@EnableAutoConfiguration
	static class TestConfig {
	}
}
