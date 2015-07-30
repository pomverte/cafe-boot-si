/*
 * Copyright 2002-2012 the original author or authors.
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

package org.springframework.integration.samples.cafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import lombok.extern.slf4j.Slf4j;

/**
 * Provides the 'main' method for running the Cafe Demo application. When an order is placed, the Cafe will send that
 * order to the "orders" channel. The channels are defined within the configuration file ("cafeDemo.xml"), and the
 * relevant components are configured with annotations (such as the OrderSplitter, DrinkRouter, and Barista classes).
 *
 * @author Mark Fisher
 * @author Marius Bogoevici
 */
@Slf4j
@SpringBootApplication
public class CafeDemoApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CafeDemoApp.class, args);
    }

}