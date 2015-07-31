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

package org.springframework.integration.samples.cafe.business;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Marius Bogoevici
 * @author Tom McCuch
 * @author Gunnar Hillert
 */
public class Delivery implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String SEPARATOR = "-----------------------";

    @Getter
    @Setter
    private List<Drink> deliveredDrinks;

    @Getter
    @Setter
    private int orderNumber;

    // Default constructor required by Jackson Java JSON-processor
    public Delivery() {
    }

    public Delivery(List<Drink> deliveredDrinks) {
        assert deliveredDrinks.size() > 0;
        this.deliveredDrinks = deliveredDrinks;
        this.orderNumber = deliveredDrinks.get(0).getOrderNumber();
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder(SEPARATOR + "\n");
        buffer.append("Order #" + this.getOrderNumber() + "\n");
        for (Drink drink : this.getDeliveredDrinks()) {
            buffer.append(drink);
            buffer.append("\n");
        }
        buffer.append(SEPARATOR + "\n");
        return buffer.toString();
    }

}
