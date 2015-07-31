package org.springframework.integration.samples.cafe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.BridgeFrom;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;

@Configuration
public class ChannelConfig {

    private static final int MAX_CAPACITY = 10;

    @Bean
    public DirectChannel orders() {
        return new DirectChannel();
    }

    @Bean
    public DirectChannel drinks() {
        return new DirectChannel();
    }

    @Bean
    public QueueChannel coldDrinks() {
        return new QueueChannel(MAX_CAPACITY);
    }

    @Bean
    public QueueChannel hotDrinks() {
        return new QueueChannel(MAX_CAPACITY);
    }

    @Bean
    @BridgeFrom("coldDrinks")
    public DirectChannel coldDrinkBarista() {
        return new DirectChannel();
    }

    @Bean
    @BridgeFrom("hotDrinks")
    public DirectChannel hotDrinkBarista() {
        return new DirectChannel();
    }

    @Bean
    public DirectChannel preparedDrinks() {
        return new DirectChannel();
    }

}
