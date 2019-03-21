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
package org.springframework.statemachine.config;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.statemachine.StateMachineSystemConstants;
import org.springframework.statemachine.config.common.annotation.EnableAnnotationConfiguration;
import org.springframework.statemachine.config.common.annotation.configuration.ObjectPostProcessorConfiguration;
import org.springframework.statemachine.config.configuration.StateMachineAnnotationPostProcessorConfiguration;
import org.springframework.statemachine.config.configuration.StateMachineCommonConfiguration;
import org.springframework.statemachine.config.configuration.StateMachineConfiguration;
import org.springframework.statemachine.config.configuration.StateMachineConfigurationImportSelector;

/**
 * Annotation which imports @{@link Configuration}s related to
 * building state machines.
 *
 * @author Janne Valkealahti
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@EnableAnnotationConfiguration
@Import({ StateMachineConfigurationImportSelector.class, StateMachineCommonConfiguration.class,
		StateMachineConfiguration.class, ObjectPostProcessorConfiguration.class,
		StateMachineAnnotationPostProcessorConfiguration.class })
public @interface EnableStateMachine {

	/**
	 * The name of bean, or if plural, aliases for bean created based on this
	 * annotation. If left unspecified bean name will be autogenerated.
	 *
	 * @see Bean#name()
	 * @return the array if names or empty as default
	 */
	String[] name() default {StateMachineSystemConstants.DEFAULT_ID_STATEMACHINE};

	/**
	 * Defines if application context events for a state machine are
	 * enable or not. On default events are enabled.
	 *
	 * @return true, if events are enabled.
	 */
	boolean contextEvents() default true;

}