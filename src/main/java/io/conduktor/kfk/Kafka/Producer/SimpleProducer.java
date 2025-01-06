package io.conduktor.kfk.Kafka.Producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.UUID;

public class SimpleProducer {
    public static void main(String[] args) {
        Properties props = createProducerProperties();
        String message = "dog -> 🐶";
        // try-with-resources
        try (KafkaProducer<String, String> producer = new KafkaProducer<>(props)) {
            sendMessage(producer, message);
        } catch (Exception e) {
            System.err.println("Error while sending message: " + e.getMessage());
        }
    }

    private static Properties createProducerProperties() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return props;
    }

    private static void sendMessage(KafkaProducer<String, String> producer, String message) {
        producer.send(new ProducerRecord<>("test-topic", UUID.randomUUID().toString(), message), (metadata, exception) -> {
            if (exception == null) {
                System.out.println("Message sent successfully: " + message);
            } else {
                System.err.println("Error while sending message: " + exception.getMessage());
            }
        });
    }
}
