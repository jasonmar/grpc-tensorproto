# gRPC Example

## Description

This project provides an example of a gRPC service for use with TensorFlow. The [protocol definition](src/main/resources/proto/tensor_service.proto) describes a service that accepts a `TensorProto` and returns a `TensorProto`.

The [generated code](src/main/java/gen/TensorServiceGrpc.java) provides an interface which is implemented by [Server.scala](src/main/scala/server/Server.scala#45). The gRPC library makes it easy to create a secured netty HTTP/2 server that accepts and returns the given types.


## Motivation

This service could be used to prepare Tensors for service by TF-Serving or as a lightweight alternative to TF-Serving. 


## Features

* Demonstrates a simple gRPC service that accepts and returns TensorFlow TensorProto ProtoBuf.
* Uses `.proto` files from Google TensorFlow project to define a gRPC service which consumes and produces ProtoBuf messages compatible with Tensorflow and TF-serving.
