# 🛠️ **How to Set Up Kafka (KRaft Mode, 3 Servers)**

## 1️⃣ **Create and Set Up Servers**

For starting with 3 servers:
- Create copies of the `server.properties` file twice and rename them to `server2` and `server3`.
- Then, modify the properties in the `server2` and `server3` files.

### 📍 **Server 1**

```properties
controller.quorum.voters=1@localhost:9093,2@localhost:9095,3@localhost:9097
log.dirs=/tmp/server-1/kraft-combined-logs 
```

### 📍 **Server 2**
```properties
node.id=2 
controller.quorum.voters=1@localhost:9093,2@localhost:9095,3@localhost:9097
listeners=PLAINTEXT://:9094,CONTROLLER://:9095
advertised.listeners=PLAINTEXT://localhost:9094,CONTROLLER://localhost:9095
log.dirs=/tmp/server-2/kraft-combined-logs
```

### 📍 **Server 3**
```properties
node.id=3
controller.quorum.voters=1@localhost:9093,2@localhost:9095,3@localhost:9097
listeners=PLAINTEXT://:9096,CONTROLLER://:9097
advertised.listeners=PLAINTEXT://localhost:9096,CONTROLLER://localhost:9097
log.dirs=/tmp/server-3/kraft-combined-logs
```

## 2️⃣ **Generate UUID and Format Directory Files**

### Generate UUID:
```
kafka-storage.sh random-uuid 
```
### Format the `log.dirs` for each server:
``` 
kafka-storage.sh format -t <UUID> -c ~/kafka_2.13-3.9.0/config/kraft/server.properties
kafka-storage.sh format -t <UUID> -c ~/kafka_2.13-3.9.0/config/kraft/server2.properties
kafka-storage.sh format -t <UUID> -c ~/kafka_2.13-3.9.0/config/kraft/server3.properties
```
## 3️⃣ **Run the Servers**

Open 3 terminals and start the servers:

### 🖥️ **Terminal 1**
```bash
kafka-server-start.sh ~/kafka_2.13-3.9.0/config/kraft/server.properties
```
### 🖥️ **Terminal 1**
```bash
kafka-server-start.sh ~/kafka_2.13-3.9.0/config/kraft/server2.properties
```
### 🖥️ **Terminal 1**
```bash
kafka-server-start.sh ~/kafka_2.13-3.9.0/config/kraft/server.3properties
```

