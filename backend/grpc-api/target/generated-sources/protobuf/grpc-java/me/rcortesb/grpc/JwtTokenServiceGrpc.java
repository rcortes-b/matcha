package me.rcortesb.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.63.0)",
    comments = "Source: jwt-token.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class JwtTokenServiceGrpc {

  private JwtTokenServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "JwtTokenService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<me.rcortesb.grpc.JwtTokenRequest,
      me.rcortesb.grpc.JwtTokenResponse> getGetJwtTokenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getJwtToken",
      requestType = me.rcortesb.grpc.JwtTokenRequest.class,
      responseType = me.rcortesb.grpc.JwtTokenResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<me.rcortesb.grpc.JwtTokenRequest,
      me.rcortesb.grpc.JwtTokenResponse> getGetJwtTokenMethod() {
    io.grpc.MethodDescriptor<me.rcortesb.grpc.JwtTokenRequest, me.rcortesb.grpc.JwtTokenResponse> getGetJwtTokenMethod;
    if ((getGetJwtTokenMethod = JwtTokenServiceGrpc.getGetJwtTokenMethod) == null) {
      synchronized (JwtTokenServiceGrpc.class) {
        if ((getGetJwtTokenMethod = JwtTokenServiceGrpc.getGetJwtTokenMethod) == null) {
          JwtTokenServiceGrpc.getGetJwtTokenMethod = getGetJwtTokenMethod =
              io.grpc.MethodDescriptor.<me.rcortesb.grpc.JwtTokenRequest, me.rcortesb.grpc.JwtTokenResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getJwtToken"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  me.rcortesb.grpc.JwtTokenRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  me.rcortesb.grpc.JwtTokenResponse.getDefaultInstance()))
              .setSchemaDescriptor(new JwtTokenServiceMethodDescriptorSupplier("getJwtToken"))
              .build();
        }
      }
    }
    return getGetJwtTokenMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static JwtTokenServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<JwtTokenServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<JwtTokenServiceStub>() {
        @java.lang.Override
        public JwtTokenServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new JwtTokenServiceStub(channel, callOptions);
        }
      };
    return JwtTokenServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static JwtTokenServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<JwtTokenServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<JwtTokenServiceBlockingStub>() {
        @java.lang.Override
        public JwtTokenServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new JwtTokenServiceBlockingStub(channel, callOptions);
        }
      };
    return JwtTokenServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static JwtTokenServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<JwtTokenServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<JwtTokenServiceFutureStub>() {
        @java.lang.Override
        public JwtTokenServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new JwtTokenServiceFutureStub(channel, callOptions);
        }
      };
    return JwtTokenServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getJwtToken(me.rcortesb.grpc.JwtTokenRequest request,
        io.grpc.stub.StreamObserver<me.rcortesb.grpc.JwtTokenResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetJwtTokenMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service JwtTokenService.
   */
  public static abstract class JwtTokenServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return JwtTokenServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service JwtTokenService.
   */
  public static final class JwtTokenServiceStub
      extends io.grpc.stub.AbstractAsyncStub<JwtTokenServiceStub> {
    private JwtTokenServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected JwtTokenServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new JwtTokenServiceStub(channel, callOptions);
    }

    /**
     */
    public void getJwtToken(me.rcortesb.grpc.JwtTokenRequest request,
        io.grpc.stub.StreamObserver<me.rcortesb.grpc.JwtTokenResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetJwtTokenMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service JwtTokenService.
   */
  public static final class JwtTokenServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<JwtTokenServiceBlockingStub> {
    private JwtTokenServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected JwtTokenServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new JwtTokenServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public me.rcortesb.grpc.JwtTokenResponse getJwtToken(me.rcortesb.grpc.JwtTokenRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetJwtTokenMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service JwtTokenService.
   */
  public static final class JwtTokenServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<JwtTokenServiceFutureStub> {
    private JwtTokenServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected JwtTokenServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new JwtTokenServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<me.rcortesb.grpc.JwtTokenResponse> getJwtToken(
        me.rcortesb.grpc.JwtTokenRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetJwtTokenMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_JWT_TOKEN = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_JWT_TOKEN:
          serviceImpl.getJwtToken((me.rcortesb.grpc.JwtTokenRequest) request,
              (io.grpc.stub.StreamObserver<me.rcortesb.grpc.JwtTokenResponse>) responseObserver);
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

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getGetJwtTokenMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              me.rcortesb.grpc.JwtTokenRequest,
              me.rcortesb.grpc.JwtTokenResponse>(
                service, METHODID_GET_JWT_TOKEN)))
        .build();
  }

  private static abstract class JwtTokenServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    JwtTokenServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return me.rcortesb.grpc.JwtTokenServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("JwtTokenService");
    }
  }

  private static final class JwtTokenServiceFileDescriptorSupplier
      extends JwtTokenServiceBaseDescriptorSupplier {
    JwtTokenServiceFileDescriptorSupplier() {}
  }

  private static final class JwtTokenServiceMethodDescriptorSupplier
      extends JwtTokenServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    JwtTokenServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (JwtTokenServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new JwtTokenServiceFileDescriptorSupplier())
              .addMethod(getGetJwtTokenMethod())
              .build();
        }
      }
    }
    return result;
  }
}
