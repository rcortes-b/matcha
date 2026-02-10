package me.rcortesb.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.63.0)",
    comments = "Source: search-filter.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class SearchFilterServiceGrpc {

  private SearchFilterServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "SearchFilterService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<me.rcortesb.grpc.SearchFilterRequest,
      me.rcortesb.grpc.SearchFilterResponse> getGetSearchFilterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getSearchFilter",
      requestType = me.rcortesb.grpc.SearchFilterRequest.class,
      responseType = me.rcortesb.grpc.SearchFilterResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<me.rcortesb.grpc.SearchFilterRequest,
      me.rcortesb.grpc.SearchFilterResponse> getGetSearchFilterMethod() {
    io.grpc.MethodDescriptor<me.rcortesb.grpc.SearchFilterRequest, me.rcortesb.grpc.SearchFilterResponse> getGetSearchFilterMethod;
    if ((getGetSearchFilterMethod = SearchFilterServiceGrpc.getGetSearchFilterMethod) == null) {
      synchronized (SearchFilterServiceGrpc.class) {
        if ((getGetSearchFilterMethod = SearchFilterServiceGrpc.getGetSearchFilterMethod) == null) {
          SearchFilterServiceGrpc.getGetSearchFilterMethod = getGetSearchFilterMethod =
              io.grpc.MethodDescriptor.<me.rcortesb.grpc.SearchFilterRequest, me.rcortesb.grpc.SearchFilterResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getSearchFilter"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  me.rcortesb.grpc.SearchFilterRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  me.rcortesb.grpc.SearchFilterResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SearchFilterServiceMethodDescriptorSupplier("getSearchFilter"))
              .build();
        }
      }
    }
    return getGetSearchFilterMethod;
  }

  private static volatile io.grpc.MethodDescriptor<me.rcortesb.grpc.UserHitRequest,
      me.rcortesb.grpc.UserHitResponse> getRetrieveUsersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "retrieveUsers",
      requestType = me.rcortesb.grpc.UserHitRequest.class,
      responseType = me.rcortesb.grpc.UserHitResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<me.rcortesb.grpc.UserHitRequest,
      me.rcortesb.grpc.UserHitResponse> getRetrieveUsersMethod() {
    io.grpc.MethodDescriptor<me.rcortesb.grpc.UserHitRequest, me.rcortesb.grpc.UserHitResponse> getRetrieveUsersMethod;
    if ((getRetrieveUsersMethod = SearchFilterServiceGrpc.getRetrieveUsersMethod) == null) {
      synchronized (SearchFilterServiceGrpc.class) {
        if ((getRetrieveUsersMethod = SearchFilterServiceGrpc.getRetrieveUsersMethod) == null) {
          SearchFilterServiceGrpc.getRetrieveUsersMethod = getRetrieveUsersMethod =
              io.grpc.MethodDescriptor.<me.rcortesb.grpc.UserHitRequest, me.rcortesb.grpc.UserHitResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "retrieveUsers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  me.rcortesb.grpc.UserHitRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  me.rcortesb.grpc.UserHitResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SearchFilterServiceMethodDescriptorSupplier("retrieveUsers"))
              .build();
        }
      }
    }
    return getRetrieveUsersMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SearchFilterServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SearchFilterServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SearchFilterServiceStub>() {
        @java.lang.Override
        public SearchFilterServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SearchFilterServiceStub(channel, callOptions);
        }
      };
    return SearchFilterServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SearchFilterServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SearchFilterServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SearchFilterServiceBlockingStub>() {
        @java.lang.Override
        public SearchFilterServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SearchFilterServiceBlockingStub(channel, callOptions);
        }
      };
    return SearchFilterServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SearchFilterServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SearchFilterServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SearchFilterServiceFutureStub>() {
        @java.lang.Override
        public SearchFilterServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SearchFilterServiceFutureStub(channel, callOptions);
        }
      };
    return SearchFilterServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getSearchFilter(me.rcortesb.grpc.SearchFilterRequest request,
        io.grpc.stub.StreamObserver<me.rcortesb.grpc.SearchFilterResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetSearchFilterMethod(), responseObserver);
    }

    /**
     */
    default void retrieveUsers(me.rcortesb.grpc.UserHitRequest request,
        io.grpc.stub.StreamObserver<me.rcortesb.grpc.UserHitResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRetrieveUsersMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service SearchFilterService.
   */
  public static abstract class SearchFilterServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return SearchFilterServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service SearchFilterService.
   */
  public static final class SearchFilterServiceStub
      extends io.grpc.stub.AbstractAsyncStub<SearchFilterServiceStub> {
    private SearchFilterServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SearchFilterServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SearchFilterServiceStub(channel, callOptions);
    }

    /**
     */
    public void getSearchFilter(me.rcortesb.grpc.SearchFilterRequest request,
        io.grpc.stub.StreamObserver<me.rcortesb.grpc.SearchFilterResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetSearchFilterMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void retrieveUsers(me.rcortesb.grpc.UserHitRequest request,
        io.grpc.stub.StreamObserver<me.rcortesb.grpc.UserHitResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRetrieveUsersMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service SearchFilterService.
   */
  public static final class SearchFilterServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<SearchFilterServiceBlockingStub> {
    private SearchFilterServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SearchFilterServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SearchFilterServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public me.rcortesb.grpc.SearchFilterResponse getSearchFilter(me.rcortesb.grpc.SearchFilterRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetSearchFilterMethod(), getCallOptions(), request);
    }

    /**
     */
    public me.rcortesb.grpc.UserHitResponse retrieveUsers(me.rcortesb.grpc.UserHitRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRetrieveUsersMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service SearchFilterService.
   */
  public static final class SearchFilterServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<SearchFilterServiceFutureStub> {
    private SearchFilterServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SearchFilterServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SearchFilterServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<me.rcortesb.grpc.SearchFilterResponse> getSearchFilter(
        me.rcortesb.grpc.SearchFilterRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetSearchFilterMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<me.rcortesb.grpc.UserHitResponse> retrieveUsers(
        me.rcortesb.grpc.UserHitRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRetrieveUsersMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_SEARCH_FILTER = 0;
  private static final int METHODID_RETRIEVE_USERS = 1;

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
        case METHODID_GET_SEARCH_FILTER:
          serviceImpl.getSearchFilter((me.rcortesb.grpc.SearchFilterRequest) request,
              (io.grpc.stub.StreamObserver<me.rcortesb.grpc.SearchFilterResponse>) responseObserver);
          break;
        case METHODID_RETRIEVE_USERS:
          serviceImpl.retrieveUsers((me.rcortesb.grpc.UserHitRequest) request,
              (io.grpc.stub.StreamObserver<me.rcortesb.grpc.UserHitResponse>) responseObserver);
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
          getGetSearchFilterMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              me.rcortesb.grpc.SearchFilterRequest,
              me.rcortesb.grpc.SearchFilterResponse>(
                service, METHODID_GET_SEARCH_FILTER)))
        .addMethod(
          getRetrieveUsersMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              me.rcortesb.grpc.UserHitRequest,
              me.rcortesb.grpc.UserHitResponse>(
                service, METHODID_RETRIEVE_USERS)))
        .build();
  }

  private static abstract class SearchFilterServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SearchFilterServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return me.rcortesb.grpc.SearchFilterProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SearchFilterService");
    }
  }

  private static final class SearchFilterServiceFileDescriptorSupplier
      extends SearchFilterServiceBaseDescriptorSupplier {
    SearchFilterServiceFileDescriptorSupplier() {}
  }

  private static final class SearchFilterServiceMethodDescriptorSupplier
      extends SearchFilterServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    SearchFilterServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (SearchFilterServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SearchFilterServiceFileDescriptorSupplier())
              .addMethod(getGetSearchFilterMethod())
              .addMethod(getRetrieveUsersMethod())
              .build();
        }
      }
    }
    return result;
  }
}
