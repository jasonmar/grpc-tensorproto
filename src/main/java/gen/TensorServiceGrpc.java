package gen;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.10.0)",
    comments = "Source: tensor_service.proto")
public final class TensorServiceGrpc {

  private TensorServiceGrpc() {}

  public static final String SERVICE_NAME = "gen.TensorService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getApplyMethod()} instead. 
  public static final io.grpc.MethodDescriptor<org.tensorflow.framework.TensorProto,
      org.tensorflow.framework.TensorProto> METHOD_APPLY = getApplyMethodHelper();

  private static volatile io.grpc.MethodDescriptor<org.tensorflow.framework.TensorProto,
      org.tensorflow.framework.TensorProto> getApplyMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<org.tensorflow.framework.TensorProto,
      org.tensorflow.framework.TensorProto> getApplyMethod() {
    return getApplyMethodHelper();
  }

  private static io.grpc.MethodDescriptor<org.tensorflow.framework.TensorProto,
      org.tensorflow.framework.TensorProto> getApplyMethodHelper() {
    io.grpc.MethodDescriptor<org.tensorflow.framework.TensorProto, org.tensorflow.framework.TensorProto> getApplyMethod;
    if ((getApplyMethod = TensorServiceGrpc.getApplyMethod) == null) {
      synchronized (TensorServiceGrpc.class) {
        if ((getApplyMethod = TensorServiceGrpc.getApplyMethod) == null) {
          TensorServiceGrpc.getApplyMethod = getApplyMethod = 
              io.grpc.MethodDescriptor.<org.tensorflow.framework.TensorProto, org.tensorflow.framework.TensorProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "gen.TensorService", "Apply"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.tensorflow.framework.TensorProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.tensorflow.framework.TensorProto.getDefaultInstance()))
                  .setSchemaDescriptor(new TensorServiceMethodDescriptorSupplier("Apply"))
                  .build();
          }
        }
     }
     return getApplyMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TensorServiceStub newStub(io.grpc.Channel channel) {
    return new TensorServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TensorServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new TensorServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TensorServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new TensorServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class TensorServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void apply(org.tensorflow.framework.TensorProto request,
        io.grpc.stub.StreamObserver<org.tensorflow.framework.TensorProto> responseObserver) {
      asyncUnimplementedUnaryCall(getApplyMethodHelper(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getApplyMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                org.tensorflow.framework.TensorProto,
                org.tensorflow.framework.TensorProto>(
                  this, METHODID_APPLY)))
          .build();
    }
  }

  /**
   */
  public static final class TensorServiceStub extends io.grpc.stub.AbstractStub<TensorServiceStub> {
    private TensorServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TensorServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TensorServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TensorServiceStub(channel, callOptions);
    }

    /**
     */
    public void apply(org.tensorflow.framework.TensorProto request,
        io.grpc.stub.StreamObserver<org.tensorflow.framework.TensorProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getApplyMethodHelper(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class TensorServiceBlockingStub extends io.grpc.stub.AbstractStub<TensorServiceBlockingStub> {
    private TensorServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TensorServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TensorServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TensorServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public org.tensorflow.framework.TensorProto apply(org.tensorflow.framework.TensorProto request) {
      return blockingUnaryCall(
          getChannel(), getApplyMethodHelper(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TensorServiceFutureStub extends io.grpc.stub.AbstractStub<TensorServiceFutureStub> {
    private TensorServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TensorServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TensorServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TensorServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.tensorflow.framework.TensorProto> apply(
        org.tensorflow.framework.TensorProto request) {
      return futureUnaryCall(
          getChannel().newCall(getApplyMethodHelper(), getCallOptions()), request);
    }
  }

  private static final int METHODID_APPLY = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TensorServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TensorServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_APPLY:
          serviceImpl.apply((org.tensorflow.framework.TensorProto) request,
              (io.grpc.stub.StreamObserver<org.tensorflow.framework.TensorProto>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class TensorServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TensorServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return gen.TensorServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TensorService");
    }
  }

  private static final class TensorServiceFileDescriptorSupplier
      extends TensorServiceBaseDescriptorSupplier {
    TensorServiceFileDescriptorSupplier() {}
  }

  private static final class TensorServiceMethodDescriptorSupplier
      extends TensorServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    TensorServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (TensorServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TensorServiceFileDescriptorSupplier())
              .addMethod(getApplyMethodHelper())
              .build();
        }
      }
    }
    return result;
  }
}
