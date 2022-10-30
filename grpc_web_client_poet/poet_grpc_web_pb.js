/**
 * @fileoverview gRPC-Web generated client stub for poet
 * @enhanceable
 * @public
 */

// GENERATED CODE -- DO NOT EDIT!


/* eslint-disable */
// @ts-nocheck



const grpc = {};
grpc.web = require('grpc-web');

const proto = {};
proto.poet = require('./poet_pb.js');

/**
 * @param {string} hostname
 * @param {?Object} credentials
 * @param {?grpc.web.ClientOptions} options
 * @constructor
 * @struct
 * @final
 */
proto.poet.HelloWorldServiceClient =
    function(hostname, credentials, options) {
  if (!options) options = {};
  options.format = 'text';

  /**
   * @private @const {!grpc.web.GrpcWebClientBase} The client
   */
  this.client_ = new grpc.web.GrpcWebClientBase(options);

  /**
   * @private @const {string} The hostname
   */
  this.hostname_ = hostname;

};


/**
 * @param {string} hostname
 * @param {?Object} credentials
 * @param {?grpc.web.ClientOptions} options
 * @constructor
 * @struct
 * @final
 */
proto.poet.HelloWorldServicePromiseClient =
    function(hostname, credentials, options) {
  if (!options) options = {};
  options.format = 'text';

  /**
   * @private @const {!grpc.web.GrpcWebClientBase} The client
   */
  this.client_ = new grpc.web.GrpcWebClientBase(options);

  /**
   * @private @const {string} The hostname
   */
  this.hostname_ = hostname;

};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.poet.HelloWorldRequest,
 *   !proto.poet.HelloWorldResponse>}
 */
const methodDescriptor_HelloWorldService_SayHello = new grpc.web.MethodDescriptor(
  '/poet.HelloWorldService/SayHello',
  grpc.web.MethodType.UNARY,
  proto.poet.HelloWorldRequest,
  proto.poet.HelloWorldResponse,
  /**
   * @param {!proto.poet.HelloWorldRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.poet.HelloWorldResponse.deserializeBinary
);


/**
 * @param {!proto.poet.HelloWorldRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.poet.HelloWorldResponse)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.poet.HelloWorldResponse>|undefined}
 *     The XHR Node Readable Stream
 */
proto.poet.HelloWorldServiceClient.prototype.sayHello =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/poet.HelloWorldService/SayHello',
      request,
      metadata || {},
      methodDescriptor_HelloWorldService_SayHello,
      callback);
};


/**
 * @param {!proto.poet.HelloWorldRequest} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.poet.HelloWorldResponse>}
 *     Promise that resolves to the response
 */
proto.poet.HelloWorldServicePromiseClient.prototype.sayHello =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/poet.HelloWorldService/SayHello',
      request,
      metadata || {},
      methodDescriptor_HelloWorldService_SayHello);
};


module.exports = proto.poet;

