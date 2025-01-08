package io.conduktor.kfk.service;

import io.conduktor.kfk.service.dto.CreateProductDTO;
import io.conduktor.kfk.service.event.ProductCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

    public String createProduct(CreateProductDTO createProductDTO) throws ExecutionException, InterruptedException {
        //ToDo save in DB
        String productId = UUID.randomUUID().toString();

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(productId, createProductDTO.getTitle(),
                createProductDTO.getPrice(), createProductDTO.getQuantity());

        SendResult<String, ProductCreatedEvent> result = kafkaTemplate
                .send("product-created-events-topic", productId, productCreatedEvent).get();

        log.info("topic {}", result.getRecordMetadata().topic());
        log.info("partition {}", result.getRecordMetadata().partition());
        log.info("offset {}", result.getRecordMetadata().offset());
        log.info("Successfully sent product created {}", productId);
        return productId;
    }

}
