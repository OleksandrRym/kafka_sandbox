# ASYNC #
```java
public String createProduct(CreateProductDTO createProductDTO) {
        //ToDo save in DB
        String productId = UUID.randomUUID().toString();

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(productId, createProductDTO.getTitle(),
                createProductDTO.getPrice(), createProductDTO.getQuantity());

        CompletableFuture<SendResult<String, ProductCreatedEvent>> future = kafkaTemplate
                .send("product-created-events-topic", productId, productCreatedEvent);
        future.whenComplete((result, exception) -> {
            if (exception != null) {
                log.error("Failed to send product created event {}", exception.getMessage());
            } else {
                log.info("Successfully sent product created {}", result.getRecordMetadata().offset());
            }
        });
        log.info("Successfully sent product created {}", productId);
        return productId;
    }
```
# SYNC #
```java
public String createProduct(CreateProductDTO createProductDTO) {
        //ToDo save in DB
        String productId = UUID.randomUUID().toString();

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(productId, createProductDTO.getTitle(),
                createProductDTO.getPrice(), createProductDTO.getQuantity());

        CompletableFuture<SendResult<String, ProductCreatedEvent>> future = kafkaTemplate
                .send("product-created-events-topic", productId, productCreatedEvent);
        future.whenComplete((result, exception) -> {
            if (exception != null) {
                log.error("Failed to send product created event {}", exception.getMessage());
            } else {
                log.info("Successfully sent product created {}", result.getRecordMetadata().offset());
            }
        });
    future.join();// until we get the result from the future we won't go any further
    log.info("Successfully sent product created {}", productId);
        return productId;
    }
```
OR
```java
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
```
but u need to use try-catch and handle error