# gRPC Project

## What is gRPC?

In gRPC, a client application can directly call a method on a server application on a different machine as if it were a local object, making it easier for you to create distributed applications and services. As in many RPC systems, gRPC is based around the idea of defining a service, specifying the methods that can be called remotely with their parameters and return types. On the server side, the server implements this interface and runs a gRPC server to handle client calls. On the client side, the client has a stub (referred to as just a client in some languages) that provides the same methods as the server.



![Concept Diagram](https://grpc.io/img/landing-2.svg)



gRPC clients and servers can run and talk to each other in a variety of environments - from servers inside Google to your own desktop - and can be written in any of gRPC’s supported languages. So, for example, you can easily create a gRPC server in Java with clients in Go, Python, or Ruby. In addition, the latest Google APIs will have gRPC versions of their interfaces, letting you easily build Google functionality into your applications.

By default, gRPC uses [Protocol Buffers](https://developers.google.com/protocol-buffers/docs/overview), Google’s mature open source mechanism for serializing structured data (although it can be used with other data formats such as JSON).

## What is this project?

The function of this project is RSA encryption. We established a server with encryption and decryption function, and created relevant clients to call.

This project consists of two parts: server (under `/RPC` directory) and client (under `/client` directory).

## How to Run?

The base IDE is **Eclipse**.

### 1. Start Server

The start function is located at `rpc/src/main/java/GRCPServer.java`. click `Run as Java Application`.

### 2. Start Client

The start function is located at `client/src/main/java/client/GrpcClient.java`. click `Run as Java Application`.

Then you will see the parameters and results in the console window.

## Demo

Please click [HERE](https://youtu.be/Yl7gV_KAfGk) to watch the demo video.

## Other

email guanchenzhao@gmail.com for more information.





