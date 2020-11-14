# spring-boot-rabbitmq-integration

This project folder contains two maven project.

- ProduceMessageApp
- ConsumeMessageApp

Firstly you must create a RabbitMQ instance in your system.

Use follwoing Docker commands for creating RabbitMQ container

```
docker pull rabbitmq:3-management
```
after pulling image

```
docker run --rm -it --hostname my-rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management
```

Now you are ready for **Run** java project.Use your IDE Run or use 

```
mvn clean install
```

- Firstly Run **Produce App** and send message via **Postman** or **curl**

in Postman enter the following Url for sending message.

```
localhost:8282/send?msg=hello-rabbit

```



- Secondly Run **Consumer App** , you will see message in console that you sent.
