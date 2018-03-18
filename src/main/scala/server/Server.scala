package server

import java.nio.FloatBuffer

import gen.TensorServiceGrpc.TensorServiceImplBase
import io.grpc.ServerBuilder
import io.grpc.stub.StreamObserver
import org.tensorflow.Tensor
import org.tensorflow.framework.TensorProto
import server.Convert.{toTensor, toTensorProto}

/** See https://github.com/grpc/grpc-java/blob/master/examples/src/main/java/io/grpc/examples/helloworld/HelloWorldServer.java
  *
  * @param host
  * @param port
  */
case class Server(host: String = "localhost", port: Int = 50051) extends TensorServiceImplBase {
  private val server = ServerBuilder.forPort(port).addService(this).build()

  def transform(tensor: Tensor[java.lang.Float]): Tensor[java.lang.Float] = {
    val buf = FloatBuffer.allocate(tensor.numElements())
    val out = FloatBuffer.allocate(tensor.numElements())
    tensor.writeTo(buf)
    buf.flip()

    while (buf.hasRemaining) out.put(buf.get() * 2.0f)
    out.flip()

    Tensor.create(tensor.shape(), out)
  }

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
    try {
      val tensor = toTensor(request)
      require(tensor.shape().toVector == Vector(2, 3), "shape must be (2, 3)")
      val response: TensorProto = toTensorProto(transform(tensor))
      responseObserver.onNext(response)
      responseObserver.onCompleted()
    } catch {
      case t: Throwable => responseObserver.onError(t)
    }
  }
}

