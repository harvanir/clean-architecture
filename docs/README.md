# JDBC vs R2DBC Performance Comparison

## Test setup
- Non docker PostgresSQL 11, with configuration:
  - max_connections=1000
  - shared_buffers=2GB
  - effective_io_concurrency=200
  - etc ...
- 1 Computer as test client (Intel Core i5, 8GB)
- 1 Computer as backend services & database server (Intel Core i5, 12GB)
- Using LAN Network
- Using docker compose file <i>docker-compose-using-host-db.yaml</i> with compatibility mode
- Warm up
- Duration: 3 mins
- Test operation (DB Write): invoke API resource [POST]<code>/v1/items</code>
- Test method: perform test individually.
- Each JVM heap memory setup is done by changing the limit.memory in the docker-compose file.
- For non reactive driver using fix 300 thread pool connections.
- For reactive driver using 10-50 pool connections.

![](topology.png)

## Using 158MB Heap size, 8 concurrent

### Througput & Latency
![](158mb-8c/1-158mb-8c.png)

### Java Heap Space
#### JDBC
![](158mb-8c/1-a-vm-158m-8c.png)

#### R2DBC with 10 pool
![](158mb-8c/1-b-vm-158m-8c.png)

#### R2DBC w/o pool
![](158mb-8c/1-c-vm-158m-8c.png)

## Using 158MB Heap size, 100 concurrent

### Througput & Latency
![](158mb-100c/2-158mb-100c.png)

### Java Heap Space
#### JDBC
![](158mb-100c/2-a-vm-158m-100c.png)

#### R2DBC with 10 pool
![](158mb-100c/2-b-vm-158m-100c.png)

#### R2DBC w/o pool
![](158mb-100c/2-c-vm-158m-100c.png)

## Using 256MB Heap size, 100 concurrent

### Througput & Latency
![](256mb-100c/3-256mb-100c.png)

### Java Heap Space
#### JDBC
![](256mb-100c/3-a-vm-256m-100c.png)

#### R2DBC with 10 pool
![](256mb-100c/3-b-vm-256m-100c.png)

#### R2DBC w/o pool
![](256mb-100c/3-c-vm-256m-100c.png)

## Using 256MB Heap size, 300 concurrent

### Througput & Latency
![](256mb-300c/4-256mb-300c.png)

### Java Heap Space
#### JDBC
![](256mb-300c/4-a-vm-256m-300c.png)

#### R2DBC with 10 pool
![](256mb-300c/4-b-vm-256m-300c.png)

## Using 256MB Heap size, 600 concurrent

### Througput & Latency
![](256mb-600c/5-256mb-600c.png)

### Java Heap Space
#### JDBC
![](256mb-600c/5-a-vm-256m-600c.png)

#### R2DBC with 50 pool
![](256mb-600c/5-b-vm-256m-600c.png)

## Using 256MB Heap size, 900 concurrent

### Througput & Latency
![](256mb-900c/6-256mb-900c.png)

### Java Heap Space
#### JDBC
![](256mb-900c/6-a-vm-256m-900c.png)

#### R2DBC with 50 pool
![](256mb-900c/6-b-vm-256m-900c.png)

## Using 1GB Heap size, 600 concurrent

### Througput & Latency
![](1gb-600c/7-1gb-600c.png)

### Java Heap Space
#### JDBC
![](1gb-600c/7-a-vm-1g-600c.png)

#### R2DBC with 50 pool
![](1gb-600c/7-b-vm-1g-600c.png)

## Using 1GB Heap size, 900 concurrent

### Througput & Latency
![](1gb-900c/8-1gb-900c.png)

### Java Heap Space
#### JDBC
![](1gb-900c/8-a-vm-1g-900c.png)

#### R2DBC with 50 pool
![](1gb-900c/8-b-vm-1g-900c.png)