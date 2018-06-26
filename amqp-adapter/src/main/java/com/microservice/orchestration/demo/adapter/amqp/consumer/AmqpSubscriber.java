/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.microservice.orchestration.demo.adapter.amqp.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.microservice.orchestration.demo.entity.ServiceRequest;

/**
 * @author <a href="mailto:colinlucs@gmail.com">Colin Lu</a>
 */
public class AmqpSubscriber {
	private static final Logger LOG = LoggerFactory.getLogger(AmqpSubscriber.class);

	@Autowired
	EventHandler eventHandler;

	@RabbitListener(queues = "#{subscriberQueue.name}")
	public void process(ServiceRequest request) {
		LOG.trace("Subscribed event: {}", request);
		LOG.trace("Handled by: {}", eventHandler.getClass());
		eventHandler.processEvent(request);
	}
}
