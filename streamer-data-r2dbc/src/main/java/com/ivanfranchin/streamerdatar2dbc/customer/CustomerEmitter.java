package com.ivanfranchin.streamerdatar2dbc.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CustomerEmitter {

    private final StreamBridge streamBridge;

    public void send(Customer customer) {
        Message<Customer> message = MessageBuilder.withPayload(customer)
                .setHeader("partitionKey", customer.getId())
                .build();
        streamBridge.send("customers-out-0", message);
        log.info("Headers: {}; Payload: {}", message.getHeaders(), message.getPayload());
    }
}
