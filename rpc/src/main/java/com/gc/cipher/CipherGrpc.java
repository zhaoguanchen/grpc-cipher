package com.gc.cipher;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 * <pre>
 * The Cipher service definition.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.0.1)",
    comments = "Source: cipher.proto")
public class CipherGrpc {

  private CipherGrpc() {}

  public static final String SERVICE_NAME = "Cipher";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.gc.cipher.CipherOuterClass.KeyRequest,
      com.gc.cipher.CipherOuterClass.KeyReply> METHOD_GET_KEYS =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "Cipher", "GetKeys"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.gc.cipher.CipherOuterClass.KeyRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.gc.cipher.CipherOuterClass.KeyReply.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.gc.cipher.CipherOuterClass.EncryptRequest,
      com.gc.cipher.CipherOuterClass.EncryptReply> METHOD_ENCRYPT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "Cipher", "Encrypt"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.gc.cipher.CipherOuterClass.EncryptRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.gc.cipher.CipherOuterClass.EncryptReply.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.gc.cipher.CipherOuterClass.DecryptRequest,
      com.gc.cipher.CipherOuterClass.DecryptReply> METHOD_DECRYPT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "Cipher", "Decrypt"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.gc.cipher.CipherOuterClass.DecryptRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.gc.cipher.CipherOuterClass.DecryptReply.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CipherStub newStub(io.grpc.Channel channel) {
    return new CipherStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CipherBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new CipherBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static CipherFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new CipherFutureStub(channel);
  }

  /**
   * <pre>
   * The Cipher service definition.
   * </pre>
   */
  public static abstract class CipherImplBase implements io.grpc.BindableService {

    /**
     */
    public void getKeys(com.gc.cipher.CipherOuterClass.KeyRequest request,
        io.grpc.stub.StreamObserver<com.gc.cipher.CipherOuterClass.KeyReply> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_KEYS, responseObserver);
    }

    /**
     */
    public void encrypt(com.gc.cipher.CipherOuterClass.EncryptRequest request,
        io.grpc.stub.StreamObserver<com.gc.cipher.CipherOuterClass.EncryptReply> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_ENCRYPT, responseObserver);
    }

    /**
     */
    public void decrypt(com.gc.cipher.CipherOuterClass.DecryptRequest request,
        io.grpc.stub.StreamObserver<com.gc.cipher.CipherOuterClass.DecryptReply> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_DECRYPT, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_KEYS,
            asyncUnaryCall(
              new MethodHandlers<
                com.gc.cipher.CipherOuterClass.KeyRequest,
                com.gc.cipher.CipherOuterClass.KeyReply>(
                  this, METHODID_GET_KEYS)))
          .addMethod(
            METHOD_ENCRYPT,
            asyncUnaryCall(
              new MethodHandlers<
                com.gc.cipher.CipherOuterClass.EncryptRequest,
                com.gc.cipher.CipherOuterClass.EncryptReply>(
                  this, METHODID_ENCRYPT)))
          .addMethod(
            METHOD_DECRYPT,
            asyncUnaryCall(
              new MethodHandlers<
                com.gc.cipher.CipherOuterClass.DecryptRequest,
                com.gc.cipher.CipherOuterClass.DecryptReply>(
                  this, METHODID_DECRYPT)))
          .build();
    }
  }

  /**
   * <pre>
   * The Cipher service definition.
   * </pre>
   */
  public static final class CipherStub extends io.grpc.stub.AbstractStub<CipherStub> {
    private CipherStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CipherStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CipherStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CipherStub(channel, callOptions);
    }

    /**
     */
    public void getKeys(com.gc.cipher.CipherOuterClass.KeyRequest request,
        io.grpc.stub.StreamObserver<com.gc.cipher.CipherOuterClass.KeyReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_KEYS, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void encrypt(com.gc.cipher.CipherOuterClass.EncryptRequest request,
        io.grpc.stub.StreamObserver<com.gc.cipher.CipherOuterClass.EncryptReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_ENCRYPT, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void decrypt(com.gc.cipher.CipherOuterClass.DecryptRequest request,
        io.grpc.stub.StreamObserver<com.gc.cipher.CipherOuterClass.DecryptReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DECRYPT, getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * The Cipher service definition.
   * </pre>
   */
  public static final class CipherBlockingStub extends io.grpc.stub.AbstractStub<CipherBlockingStub> {
    private CipherBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CipherBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CipherBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CipherBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.gc.cipher.CipherOuterClass.KeyReply getKeys(com.gc.cipher.CipherOuterClass.KeyRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_KEYS, getCallOptions(), request);
    }

    /**
     */
    public com.gc.cipher.CipherOuterClass.EncryptReply encrypt(com.gc.cipher.CipherOuterClass.EncryptRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_ENCRYPT, getCallOptions(), request);
    }

    /**
     */
    public com.gc.cipher.CipherOuterClass.DecryptReply decrypt(com.gc.cipher.CipherOuterClass.DecryptRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_DECRYPT, getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * The Cipher service definition.
   * </pre>
   */
  public static final class CipherFutureStub extends io.grpc.stub.AbstractStub<CipherFutureStub> {
    private CipherFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CipherFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CipherFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CipherFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gc.cipher.CipherOuterClass.KeyReply> getKeys(
        com.gc.cipher.CipherOuterClass.KeyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_KEYS, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gc.cipher.CipherOuterClass.EncryptReply> encrypt(
        com.gc.cipher.CipherOuterClass.EncryptRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_ENCRYPT, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gc.cipher.CipherOuterClass.DecryptReply> decrypt(
        com.gc.cipher.CipherOuterClass.DecryptRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DECRYPT, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_KEYS = 0;
  private static final int METHODID_ENCRYPT = 1;
  private static final int METHODID_DECRYPT = 2;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CipherImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(CipherImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_KEYS:
          serviceImpl.getKeys((com.gc.cipher.CipherOuterClass.KeyRequest) request,
              (io.grpc.stub.StreamObserver<com.gc.cipher.CipherOuterClass.KeyReply>) responseObserver);
          break;
        case METHODID_ENCRYPT:
          serviceImpl.encrypt((com.gc.cipher.CipherOuterClass.EncryptRequest) request,
              (io.grpc.stub.StreamObserver<com.gc.cipher.CipherOuterClass.EncryptReply>) responseObserver);
          break;
        case METHODID_DECRYPT:
          serviceImpl.decrypt((com.gc.cipher.CipherOuterClass.DecryptRequest) request,
              (io.grpc.stub.StreamObserver<com.gc.cipher.CipherOuterClass.DecryptReply>) responseObserver);
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

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    return new io.grpc.ServiceDescriptor(SERVICE_NAME,
        METHOD_GET_KEYS,
        METHOD_ENCRYPT,
        METHOD_DECRYPT);
  }

}
