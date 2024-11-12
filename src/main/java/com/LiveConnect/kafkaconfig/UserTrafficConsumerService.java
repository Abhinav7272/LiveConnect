package com.LiveConnect.kafkaconfig;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//bin/kafka-topics.sh --create --topic user-traffic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1

@Service
public class UserTrafficConsumerService {

    @KafkaListener(topics = "user-traffic", groupId = "user-traffic-group")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
        // Process or log the user activity
    }
}