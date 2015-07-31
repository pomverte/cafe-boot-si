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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.samples.cafe.business.DrinkType;
import org.springframework.integration.samples.cafe.business.Order;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.scheduling.support.PeriodicTrigger;

import lombok.extern.slf4j.Slf4j;

/**
 * Provides the 'main' method for running the Cafe Demo application. When an
 * order is placed, the Cafe will send that order to the "orders" channel.
 * The channels are defined within the configuration file ("cafeDemo.xml"),
 * and the relevant components are configured with annotations (such as the
 * OrderSplitter, DrinkRouter, and Barista classes).
 *
 * @author Mark Fisher
 * @author Marius Bogoevici
 */
@Slf4j
@SpringBootApplication
@IntegrationComponentScan
@ImportResource("classpath:spring-integration/cafeDemo.xml")
public class CafeDemoApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CafeDemoApp.class, args);

        Cafe cafe = context.getBean(Cafe.class);
        for (int i = 1; i <= 100; i++) {
            Order order = new Order(i);
            order.addItem(DrinkType.LATTE, 2, false);
            order.addItem(DrinkType.MOCHA, 3, true);
            cafe.placeOrder(order);
        }
        context.close();
    }

    /**
     * The entry point for the Cafe Demo. The demo's main() method invokes the
     * '<code>placeOrder</code>' method on a generated MessagingGateway proxy.
     * The gateway then passes the {@link Order} as the payload of a
     * {@link org.springframework.integration.Message} to the
     * configured <em>requestChannel</em>. The channel ('orders') is
     * defined in the 'cafeDemo.xml' file.
     *
     * @author Mark Fisher
     */
    @MessagingGateway
    public interface Cafe {
        @Gateway(requestChannel="orders")
        void placeOrder(Order order);
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata defaultPoller() {
        PollerMetadata pollerMetadata = new PollerMetadata();
        pollerMetadata.setTrigger(new PeriodicTrigger(10));
        return pollerMetadata;
    }
    
    @Bean
    public IntegrationFlow orderFlow() {
        return
        IntegrationFlows.from("inputChannel")
                .channel(MessageChannels.queue("outputChannel"))
                .get();
    }

}
