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

import lombok.Getter;
import lombok.Setter;

/**
 * @author Marius Bogoevici
 * @author Tom McCuch
 * @author Gunnar Hillert
 */
public class Drink implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private boolean iced;

    @Getter
    @Setter
    private int shots;

    @Getter
    @Setter
    private DrinkType drinkType;

    @Getter
    @Setter
    private int orderNumber;

    // Default constructor required by Jackson Java JSON-processor
    public Drink() {
    }

    public Drink(int orderNumber, DrinkType drinkType, boolean iced, int shots) {
        this.orderNumber = orderNumber;
        this.drinkType = drinkType;
        this.iced = iced;
        this.shots = shots;
    }

    @Override
    public String toString() {
        return (this.iced ? "Iced" : "Hot") + " " + this.drinkType.toString() + ", " + this.shots + " shots.";
    }

}
