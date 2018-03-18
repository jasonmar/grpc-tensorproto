package server

import io.grpc.StatusRuntimeException

object TensorService {
  def main(args: Array[String]): Unit = {
    val service = Server()
    service.start()

    val client = Client()

    try {
      for (x <- Seq(0.05f, 0.1f, 0.2f, 0.3f, 0.4f)){
        val request = Array.fill[Float](6){x}
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
