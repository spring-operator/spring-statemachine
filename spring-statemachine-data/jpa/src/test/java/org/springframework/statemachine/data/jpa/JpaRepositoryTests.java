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
package org.springframework.statemachine.data.jpa;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.data.RepositoryState;
import org.springframework.statemachine.data.StateRepository;
import org.springframework.statemachine.data.RepositoryTransition;
import org.springframework.statemachine.data.TransitionRepository;

public class JpaRepositoryTests {

	@Test
	public void testRepository1() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(Config.class);
		context.refresh();

		JpaStateRepository statesRepository = context.getBean(JpaStateRepository.class);
		JpaRepositoryState state = new JpaRepositoryState("S1");
		statesRepository.save(state);
		Iterable<JpaRepositoryState> findAll = statesRepository.findAll();
		assertThat(findAll.iterator().next().getState(), is("S1"));

		JpaTransitionRepository transitionsRepository = context.getBean(JpaTransitionRepository.class);
		JpaRepositoryTransition transition = new JpaRepositoryTransition("S1", "S2", "E1");
		transitionsRepository.save(transition);
		JpaRepositoryTransition transition2 = transitionsRepository.findAll().iterator().next();
		assertThat(transition2.getSource(), is("S1"));
		assertThat(transition2.getTarget(), is("S2"));
		assertThat(transition2.getEvent(), is("E1"));

		context.close();
	}

	@Test
	public void testRepository2() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(Config.class);
		context.refresh();

		@SuppressWarnings("unchecked")
		StateRepository<JpaRepositoryState> statesRepository1 = context.getBean(StateRepository.class);
		JpaRepositoryState state = new JpaRepositoryState("S1");
		statesRepository1.save(state);
		@SuppressWarnings("unchecked")
		StateRepository<? extends RepositoryState> statesRepository2 = context.getBean(StateRepository.class);
		Iterable<? extends RepositoryState> findAll = statesRepository2.findAll();
		assertThat(findAll.iterator().next().getState(), is("S1"));

		@SuppressWarnings("unchecked")
		TransitionRepository<RepositoryTransition> transitionsRepository = context.getBean(TransitionRepository.class);
		RepositoryTransition transition = new JpaRepositoryTransition("S1", "S2", "E1");
		transitionsRepository.save(transition);
		RepositoryTransition transition2 = transitionsRepository.findAll().iterator().next();
		assertThat(transition2.getSource(), is("S1"));
		assertThat(transition2.getTarget(), is("S2"));
		assertThat(transition2.getEvent(), is("E1"));

		context.close();
	}

	@Test
	public void testRepository3() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(Config.class);
		context.refresh();

		JpaStateRepository statesRepository = context.getBean(JpaStateRepository.class);
		JpaRepositoryState state1 = new JpaRepositoryState("machine1", "S1", true);
		statesRepository.save(state1);
		JpaRepositoryState state2 = new JpaRepositoryState("machine2", "S2", false);
		statesRepository.save(state2);

		List<JpaRepositoryState> findByMachineId1 = statesRepository.findByMachineId("machine1");
		List<JpaRepositoryState> findByMachineId2 = statesRepository.findByMachineId("machine2");
		assertThat(findByMachineId1.size(), is(1));
		assertThat(findByMachineId2.size(), is(1));
		assertThat(findByMachineId1.get(0).getMachineId(), is("machine1"));
		assertThat(findByMachineId2.get(0).getMachineId(), is("machine2"));


		JpaTransitionRepository transitionsRepository = context.getBean(JpaTransitionRepository.class);
		JpaRepositoryTransition transition1 = new JpaRepositoryTransition("machine1", "S1", "S2", "E1");
		JpaRepositoryTransition transition2 = new JpaRepositoryTransition("machine2", "S3", "S4", "E2");
		transitionsRepository.save(transition1);
		transitionsRepository.save(transition2);
		List<JpaRepositoryTransition> findByMachineId3 = transitionsRepository.findByMachineId("machine1");
		List<JpaRepositoryTransition> findByMachineId4 = transitionsRepository.findByMachineId("machine2");

		assertThat(findByMachineId3.size(), is(1));
		assertThat(findByMachineId4.size(), is(1));
		assertThat(findByMachineId3.get(0).getMachineId(), is("machine1"));
		assertThat(findByMachineId4.get(0).getMachineId(), is("machine2"));

		context.close();
	}

	@Test
	public void testAutowire() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(Config.class, WireConfig.class);
		context.refresh();
		context.close();
	}

	@EnableAutoConfiguration
	static class Config {
	}

	@Configuration
	static class WireConfig {

		@Autowired
		StateRepository<JpaRepositoryState> statesRepository1;

		@Autowired
		TransitionRepository<JpaRepositoryTransition> statesRepository11;

		@SuppressWarnings("rawtypes")
		@Autowired
		StateRepository statesRepository2;

		@Autowired
		JpaStateRepository statesRepository3;

		@Autowired
		StateRepository<? extends RepositoryState> statesRepository4;
	}
}
