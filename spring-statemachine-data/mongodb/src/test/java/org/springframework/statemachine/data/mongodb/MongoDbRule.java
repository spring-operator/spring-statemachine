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

import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;

/**
 * Rule skipping tests if MongoDb is not available from localhost with default settings.
 *
 * @author Janne Valkealahti
 *
 */
public class MongoDbRule extends TestWatcher implements TestRule {

	@Override
	public Statement apply(Statement base, Description description) {
		MongoClient client = null;
		try {
			client = new MongoClient(new ServerAddress(), MongoClientOptions.builder().connectTimeout(50).build());
			client.getAddress();
		} catch (Exception e) {
			return super.apply(new Statement() {
				@Override
				public void evaluate() throws Throwable {
				}
			}, Description.EMPTY);
		} finally {
			if (client != null) {
				client.close();
			}
		}
		return super.apply(base, description);
	}
}
