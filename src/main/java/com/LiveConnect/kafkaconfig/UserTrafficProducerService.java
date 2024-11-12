package com.LiveConnect.kafkaconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserTrafficProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "user-traffic";

    public void sendUserTraffic(String eventMessage) {
        kafkaTemplate.send(TOPIC, eventMessage);
    }
}