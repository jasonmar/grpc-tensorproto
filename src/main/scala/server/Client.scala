package server

import java.util.concurrent.TimeUnit

import gen.TensorServiceGrpc
import io.grpc.ManagedChannelBuilder
import org.tensorflow.framework.TensorProto
import server.Convert.mkTensorProto

/**
  * See https://github.com/grpc/grpc-java/blob/master/examples/src/main/java/io/grpc/examples/helloworld/HelloWorldClient.java
  *
  * @param host
  * @param port
  */
case class Client(host: String = "localhost", port: Int = 50051){
  private val channel = ManagedChannelBuilder.forAddress(host, port)
    .usePlaintext(true) // by default SSL/TLS is used
    .build()

  private val client = TensorServiceGrpc.newBlockingStub(channel)

  def apply(data: Array[Float]): TensorProto = client.apply(mkTensorProto(Array[Long](2,3), data))
  def apply(data: TensorProto): TensorProto = client.apply(data)
  def stop(): Unit = channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
}

