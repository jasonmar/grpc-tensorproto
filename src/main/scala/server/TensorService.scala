package server


import java.util.concurrent.TimeUnit

import gen.TensorServiceGrpc
import gen.TensorServiceGrpc.TensorServiceImplBase
import io.grpc.{ManagedChannelBuilder, ServerBuilder, StatusRuntimeException}
import io.grpc.stub.StreamObserver
import org.tensorflow.framework.TensorProto

object TensorService {

  /** See https://github.com/grpc/grpc-java/blob/master/examples/src/main/java/io/grpc/examples/helloworld/HelloWorldServer.java
    *
    * @param host
    * @param port
    */
  case class Server(host: String = "localhost", port: Int = 50051) extends TensorServiceImplBase {
    private val server = ServerBuilder.forPort(port).addService(this).build()

    def start(): Unit = {
      server.start()
      Runtime.getRuntime.addShutdownHook(new Thread() {
        override def run(): Unit = {
          System.err.println("Shutting down gRPC server")
          server.shutdown()
          System.err.println("gRPC Server shut down")
        }
      })
    }

    def stop(): Unit = server.shutdown()

    override def apply(request: TensorProto, responseObserver: StreamObserver[TensorProto]): Unit = {
      val response: TensorProto = mkTensorProto(Array.fill[Float](8){1.1f})
      responseObserver.onNext(response)
      responseObserver.onCompleted()
    }
  }

  def mkTensorProto(data: Array[Float]): TensorProto = {
    val builder = TensorProto.newBuilder()
    var i = 0
    while (i < data.length){
      builder.addFloatVal(data(i))
      i += 1
    }
    builder.build()
  }

  /**
    * See https://github.com/grpc/grpc-java/blob/master/examples/src/main/java/io/grpc/examples/helloworld/HelloWorldClient.java
    * @param host
    * @param port
    */
  case class Client(host: String = "localhost", port: Int = 50051){
    private val channel = ManagedChannelBuilder.forAddress(host, port)
      .usePlaintext(true) // by default SSL/TLS is used
      .build()

    private val client = TensorServiceGrpc.newBlockingStub(channel)

    def apply(data: Array[Float]): TensorProto = client.apply(mkTensorProto(data))
    def apply(data: TensorProto): TensorProto = client.apply(data)
    def stop(): Unit = channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
  }

  def main(args: Array[String]): Unit = {
    val service = Server()
    service.start()

    val client = Client()

    try {
      for (x <- Seq(0.0f, 0.1f, 0.2f, 0.3f, 0.4f)){
        val request = Array.fill[Float](8){x}
        System.out.println("Request: ")
        System.out.println(request.mkString("[ ", ", ", " ]"))
        val response = client.apply(request)
        System.out.println("Response: ")
        System.out.println(response.toString)
      }
    } catch {
      case e: StatusRuntimeException =>
        System.err.println(s"RPC failed: ${e.getStatus}")
    }
  }
}
