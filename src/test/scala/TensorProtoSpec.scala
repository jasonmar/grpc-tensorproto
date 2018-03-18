import java.nio.FloatBuffer

import org.scalatest.FlatSpec
import org.tensorflow.framework.TensorProto
import server.Convert._

class TensorProtoSpec extends FlatSpec {
  "Tensors" should "create TensorProto" in {
    val data = Array.fill[Float](2,3){0.1f}

    val tensorProto: TensorProto = mkTensorProto(data)

    import scala.collection.JavaConverters._
    val shape1 = tensorProto.getTensorShape.getDimList.asScala.map(_.getSize).toArray
    assert(shape1.length == 2)
    assert(shape1.head == 2)
    assert(shape1.last == 3)

    val tensor = toTensor(tensorProto)
    assert(tensor.shape().length == 2)
    assert(tensor.shape().head == 2)
    assert(tensor.shape().last == 3)
    assert(tensor.dataType() == org.tensorflow.DataType.FLOAT)

    val buf = FloatBuffer.allocate(6)
    tensor.writeTo(buf)
    buf.flip()
    for (_ <- 1 to 6) assert(buf.get() == 0.1f)
  }
}
