package server

import java.nio.FloatBuffer

import org.tensorflow.Tensor
import org.tensorflow.framework.TensorShapeProto.Dim
import org.tensorflow.framework.{DataType, TensorProto, TensorShapeProto}

object Convert {

  def toDim(x: (Long, Int)): Dim = Dim.newBuilder().setName(x._2.toString).setSize(x._1).build()

  def mkShape(shape: Array[Long]): TensorShapeProto = {
    val builder = TensorShapeProto.newBuilder()
    shape.zipWithIndex.map(toDim).foreach(builder.addDim)
    builder.build()
  }

  def toTensor(tensorProto: TensorProto): Tensor[java.lang.Float] = {
    import scala.collection.JavaConverters.asScalaBufferConverter
    val shape = tensorProto.getTensorShape.getDimList.asScala.map(_.getSize).toArray

    val buf = FloatBuffer.allocate(tensorProto.getFloatValCount)
    tensorProto.getFloatValList.asScala.foreach(buf.put(_))
    buf.flip()

    Tensor.create(shape, buf)
  }

  def toTensorProto(tensor: Tensor[_]): TensorProto = {
    val buf = FloatBuffer.allocate(tensor.numElements())
    tensor.writeTo(buf)
    buf.flip()

    val builder = TensorProto.newBuilder()
    builder.setTensorShape(mkShape(tensor.shape()))
    builder.setDtype(DataType.DT_FLOAT)

    while (buf.hasRemaining) builder.addFloatVal(buf.get())

    builder.build()
  }

  def mkTensorProto(shape: Array[Long], data: Array[Float]): TensorProto = {
    val builder = TensorProto.newBuilder()
    builder.setTensorShape(mkShape(shape))
    builder.setDtype(DataType.DT_FLOAT)

    var i = 0
    while (i < data.length){
      builder.addFloatVal(data(i))
      i += 1
    }
    builder.build()
  }

  def mkTensorProto(data: Array[Array[Float]]): TensorProto = {
    val builder = TensorProto.newBuilder()
    val shape = TensorShapeProto.newBuilder()
    val dim0 = Dim.newBuilder().setName("x").setSize(data.length).build()
    val dim1 = Dim.newBuilder().setName("y").setSize(data.head.length).build()
    shape.addDim(dim0)
    shape.addDim(dim1)
    builder.setTensorShape(shape.build())
    builder.setDtype(DataType.DT_FLOAT)
    var i = 0
    var j = 0
    while (i < data.length){
      while (j < data(i).length){
        builder.addFloatVal(data(i)(j))
        j += 1
      }
      j = 0
      i += 1
    }
    builder.build()
  }
}
