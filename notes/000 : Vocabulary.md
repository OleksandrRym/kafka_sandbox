# VOCABULARY📚

## Kafka topic - stream of data (identy by name)

IMMUTABLE ! ( if massage in topic - u dont can delelte , update etc.)

Topic saves a kafka kluster

Topic - like a tabel in DB

U cannot query topics ,instead , use kafka producer for send data or kafka consumer to read the data

---

## Kafka partition

Topic are split in partitions , massage in partitions gets incremental id , called **offset**

---

## Kafka producer

Producer already knows in which topic and paartition wont to write a massage

Producer can choose to send a key with the massage

If massage key == null data send in oll partitions ( round robin)

If key ≠ null → kafka get hash function and decide

All message who has same key → writen in partitions ( thanks to Hash functions )

---

## Kafka Consumer

Consumer read data from topic (by name) - pull model

---

## Kafka message serializable - Deserializable

Producer can send only ARRAY<BYTE> and consumer can consume only ARRAY<BYTE>

Consumer have a []byte and thanks to (for ex) “ IntegerSerializable” cane transform byte to value

---

## Commit offset

For ex. topic have 200 message , consumer read 100 , make commit and after go with 101. (if u setup polly u can delete first 100 sms after commit)

Commits save as __consumer_offset

---

## Kafka broker

Kafka cluster is composed of multiple brokers ( service)

Identify by id ( Integer)

---

## Bootstrap Server

Is the initial node (or set of nodes) in a Kafka cluster that consumers or producers use to establish a connection to the cluster.

If u connect to broker - u connect to kafka cluster and get all brokers

---

## **Replication Factor**

The replication factor in Apache Kafka defines the number of copies (replicas) of a partition that are stored across the Kafka cluster. In Kafka, the concept of partition leadership describes how Kafka manages data at the partition level within a topic in the cluster.

---

## **Acknowledgment**

In Apache Kafka, acknowledgment (acks) is a mechanism that determines how many replicas must confirm receiving a message before the producer considers it successfully sent. This parameter impacts the reliability of message delivery and system performance.

---

## **Idempotent**

Idempotency in Kafka ensures that a message is written to a topic only once, even if the producer retries sending it multiple times due to retries. This is critically important for preventing duplicate data in systems that require high accuracy and consistency.
