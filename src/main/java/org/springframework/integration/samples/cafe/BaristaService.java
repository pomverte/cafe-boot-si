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

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.samples.cafe.business.Drink;
import org.springframework.integration.samples.cafe.business.OrderItem;
import org.springframework.stereotype.Service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Mark Fisher
 * @author Marius Bogoevici
 * @author Tom McCuch
 */

@Slf4j
@Service
public class BaristaService {

    @Setter
    private long hotDrinkDelay = 5000;

    @Setter
    private long coldDrinkDelay = 1000;

    private AtomicInteger hotDrinkCounter = new AtomicInteger();

    private AtomicInteger coldDrinkCounter = new AtomicInteger();

    @ServiceActivator(inputChannel = "hotDrinkBarista", outputChannel = "preparedDrinks")
    public Drink prepareHotDrink(OrderItem orderItem) {
        try {
            Thread.sleep(this.hotDrinkDelay);
            log.info(Thread.currentThread().getName() + " prepared hot drink #" + this.hotDrinkCounter.incrementAndGet()
                    + " for order #" + orderItem.getOrderNumber() + ": " + orderItem);
            return new Drink(orderItem.getOrderNumber(), orderItem.getDrinkType(), orderItem.isIced(),
                    orderItem.getShots());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }

    @ServiceActivator(inputChannel = "coldDrinkBarista", outputChannel = "preparedDrinks")
    public Drink prepareColdDrink(OrderItem orderItem) {
        try {
            Thread.sleep(this.coldDrinkDelay);
            log.info(Thread.currentThread().getName() + " prepared cold drink #"
                    + this.coldDrinkCounter.incrementAndGet() + " for order #" + orderItem.getOrderNumber() + ": "
                    + orderItem);
            return new Drink(orderItem.getOrderNumber(), orderItem.getDrinkType(), orderItem.isIced(),
                    orderItem.getShots());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }

}
