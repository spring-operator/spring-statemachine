/*
 * Copyright 2015-2018 the original author or authors.
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
package org.springframework.statemachine.config.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.statemachine.config.builders.StateMachineConfigurationBuilder;
import org.springframework.statemachine.config.model.verifier.DefaultStateMachineModelVerifier;
import org.springframework.statemachine.config.model.verifier.StateMachineModelVerifier;
import org.springframework.statemachine.ensemble.StateMachineEnsemble;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.monitor.StateMachineMonitor;
import org.springframework.statemachine.security.SecurityRule;
import org.springframework.statemachine.support.StateMachineInterceptor;
import org.springframework.statemachine.transition.TransitionConflictPolicy;

/**
 * Configuration object used to keep things together in {@link StateMachineConfigurationBuilder}.
 *
 * @author Janne Valkealahti
 *
 * @param <S> the type of state
 * @param <E> the type of event
 */
public class ConfigurationData<S, E> {

	private final String machineId;
	private final BeanFactory beanFactory;
	private final TaskExecutor taskExecutor;
	private final TaskScheduler taskScheduler;
	private final boolean autoStart;
	private final TransitionConflictPolicy transitionConflictPolicy;
	private final StateMachineEnsemble<S, E> ensemble;
	private final List<StateMachineListener<S, E>> listeners;
	private final boolean securityEnabled;
	private final boolean verifierEnabled;
	private final StateMachineModelVerifier<S, E> verifier;
	private final AccessDecisionManager transitionSecurityAccessDecisionManager;
	private final AccessDecisionManager eventSecurityAccessDecisionManager;
	private final SecurityRule eventSecurityRule;
	private final SecurityRule transitionSecurityRule;
	private final StateMachineMonitor<S, E> stateMachineMonitor;
	private final List<StateMachineInterceptor<S, E>> interceptors;

	/**
	 * Instantiates a new state machine configuration config data.
	 */
	public ConfigurationData() {
		this(null, new SyncTaskExecutor(), new ConcurrentTaskScheduler(), false, null, new ArrayList<StateMachineListener<S, E>>(), false,
				null, null, null, null, true, new DefaultStateMachineModelVerifier<S, E>(), null, null, null);
	}

	/**
	 * Instantiates a new state machine configuration config data.
	 *
	 * @param beanFactory the bean factory
	 * @param taskExecutor the task executor
	 * @param taskScheduler the task scheduler
	 * @param autoStart the autostart flag
	 * @param ensemble the state machine ensemble
	 * @param listeners the state machine listeners
	 * @param securityEnabled the security enabled flag
	 * @param transitionSecurityAccessDecisionManager the transition security access decision manager
	 * @param eventSecurityAccessDecisionManager the event security access decision manager
	 * @param eventSecurityRule the event security rule
	 * @param transitionSecurityRule the transition security rule
	 * @param verifierEnabled the verifier enabled flag
	 * @param verifier the state machine model verifier
	 * @param machineId the machine id
	 * @param stateMachineMonitor the state machine monitor
	 * @param interceptors the state machine interceptors.
	 */
	public ConfigurationData(BeanFactory beanFactory, TaskExecutor taskExecutor,
			TaskScheduler taskScheduler, boolean autoStart, StateMachineEnsemble<S, E> ensemble,
			List<StateMachineListener<S, E>> listeners, boolean securityEnabled,
			AccessDecisionManager transitionSecurityAccessDecisionManager, AccessDecisionManager eventSecurityAccessDecisionManager,
			SecurityRule eventSecurityRule, SecurityRule transitionSecurityRule, boolean verifierEnabled,
			StateMachineModelVerifier<S, E> verifier, String machineId, StateMachineMonitor<S, E> stateMachineMonitor,
			List<StateMachineInterceptor<S, E>> interceptors) {
		this(beanFactory, taskExecutor, taskScheduler, autoStart, ensemble, listeners, securityEnabled,
				transitionSecurityAccessDecisionManager, eventSecurityAccessDecisionManager, eventSecurityRule, transitionSecurityRule,
				verifierEnabled, verifier, machineId, stateMachineMonitor, interceptors, null);
	}

	/**
	 * Instantiates a new state machine configuration config data.
	 *
	 * @param beanFactory the bean factory
	 * @param taskExecutor the task executor
	 * @param taskScheduler the task scheduler
	 * @param autoStart the autostart flag
	 * @param ensemble the state machine ensemble
	 * @param listeners the state machine listeners
	 * @param securityEnabled the security enabled flag
	 * @param transitionSecurityAccessDecisionManager the transition security access decision manager
	 * @param eventSecurityAccessDecisionManager the event security access decision manager
	 * @param eventSecurityRule the event security rule
	 * @param transitionSecurityRule the transition security rule
	 * @param verifierEnabled the verifier enabled flag
	 * @param verifier the state machine model verifier
	 * @param machineId the machine id
	 * @param stateMachineMonitor the state machine monitor
	 * @param interceptors the state machine interceptors.
	 * @param transitionConflightPolicy the transition conflict policy
	 */
	public ConfigurationData(BeanFactory beanFactory, TaskExecutor taskExecutor,
			TaskScheduler taskScheduler, boolean autoStart, StateMachineEnsemble<S, E> ensemble,
			List<StateMachineListener<S, E>> listeners, boolean securityEnabled,
			AccessDecisionManager transitionSecurityAccessDecisionManager, AccessDecisionManager eventSecurityAccessDecisionManager,
			SecurityRule eventSecurityRule, SecurityRule transitionSecurityRule, boolean verifierEnabled,
			StateMachineModelVerifier<S, E> verifier, String machineId, StateMachineMonitor<S, E> stateMachineMonitor,
			List<StateMachineInterceptor<S, E>> interceptors, TransitionConflictPolicy transitionConflightPolicy) {
		this.beanFactory = beanFactory;
		this.taskExecutor = taskExecutor;
		this.taskScheduler = taskScheduler;
		this.autoStart = autoStart;
		this.ensemble = ensemble;
		this.listeners = listeners;
		this.securityEnabled = securityEnabled;
		this.transitionSecurityAccessDecisionManager = transitionSecurityAccessDecisionManager;
		this.eventSecurityAccessDecisionManager = eventSecurityAccessDecisionManager;
		this.eventSecurityRule = eventSecurityRule;
		this.transitionSecurityRule = transitionSecurityRule;
		this.verifierEnabled = verifierEnabled;
		this.verifier = verifier;
		this.machineId = machineId;
		this.stateMachineMonitor = stateMachineMonitor;
		this.interceptors = interceptors;
		this.transitionConflictPolicy = transitionConflightPolicy;
	}

	public String getMachineId() {
		return machineId;
	}

	/**
	 * Gets the bean factory.
	 *
	 * @return the bean factory
	 */
	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	/**
	 * Gets the task executor.
	 *
	 * @return the task executor
	 */
	public TaskExecutor getTaskExecutor() {
		return taskExecutor;
	}

	/**
	 * Gets the task scheduler.
	 *
	 * @return the task scheduler
	 */
	public TaskScheduler getTaskScheduler() {
		return taskScheduler;
	}

	/**
	 * Gets the state machine ensemble.
	 *
	 * @return the state machine ensemble
	 */
	public StateMachineEnsemble<S, E> getStateMachineEnsemble() {
		return ensemble;
	}

	/**
	 * Returns autostart flag.
	 *
	 * @return true, if is autostart is enabled.
	 */
	public boolean isAutoStart() {
		return autoStart;
	}

	/**
	 * Gets the state machine listeners.
	 *
	 * @return the state machine listeners
	 */
	public List<StateMachineListener<S, E>> getStateMachineListeners() {
		return listeners;
	}

	/**
	 * Checks if security is enabled.
	 *
	 * @return true, if security is enabled
	 */
	public boolean isSecurityEnabled() {
		return securityEnabled;
	}

	/**
	 * Checks if verifier is enabled.
	 *
	 * @return true, if verifier is enabled
	 */
	public boolean isVerifierEnabled() {
		return verifierEnabled;
	}

	/**
	 * Gets the state machine model verifier.
	 *
	 * @return the state machine model verifier
	 */
	public StateMachineModelVerifier<S, E> getVerifier() {
		return verifier;
	}

	/**
	 * Gets the state machine monitor.
	 *
	 * @return the state machine monitor
	 */
	public StateMachineMonitor<S, E> getStateMachineMonitor() {
		return stateMachineMonitor;
	}

	/**
	 * Gets the transition security access decision manager.
	 *
	 * @return the security access decision manager
	 */
	public AccessDecisionManager getTransitionSecurityAccessDecisionManager() {
		return transitionSecurityAccessDecisionManager;
	}

	/**
	 * Gets the event security access decision manager.
	 *
	 * @return the event security access decision manager
	 */
	public AccessDecisionManager getEventSecurityAccessDecisionManager() {
		return eventSecurityAccessDecisionManager;
	}

	/**
	 * Gets the event security rule.
	 *
	 * @return the event security rule
	 */
	public SecurityRule getEventSecurityRule() {
		return eventSecurityRule;
	}

	/**
	 * Gets the transition security rule.
	 *
	 * @return the transition security rule
	 */
	public SecurityRule getTransitionSecurityRule() {
		return transitionSecurityRule;
	}

	/**
	 * Gets the state machine interceptors.
	 *
	 * @return the state machine interceptors
	 */
	public List<StateMachineInterceptor<S, E>> getStateMachineInterceptors() {
		return interceptors;
	}

	/**
	 * Gets the transition conflict policy.
	 *
	 * @return the transition conflict policy
	 */
	public TransitionConflictPolicy getTransitionConflictPolicy() {
		return transitionConflictPolicy;
	}
}
