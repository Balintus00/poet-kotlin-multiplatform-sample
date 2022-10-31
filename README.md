# Kotlin Multiplatform Hello World sample application
A pure kotlin project with:
- KotlinJS backend
- Android, Desktop and Web Material 3 clients
- The clients and backend communicates with gRPC

## Implementation details:
- The common view logic is implemented with [MVIKotlin](https://arkivanov.github.io/MVIKotlin/) and [Decompose](https://arkivanov.github.io/Decompose/)
- The common UI of the Android and Desktop application is implemented with [Jetbrains compose](https://www.jetbrains.com/lp/compose-mpp/)
- The gRPC client of Android and Desktop application is generated with [Wire](https://square.github.io/wire/)
- The Web client uses [Material 3 Web Components](https://github.com/material-components/material-web) and the currently missing Material 3 components are replaced with Material components from [the Kotlin Material Design Components (KMDC) library](https://github.com/mpetuska/kmdc)
- Currently the [gRPC web client requires a proxy](https://grpc.io/blog/state-of-grpc-web/) to be able to communicate with gRPC backends. This proxy in this project is implemented with [Envoy](https://www.envoyproxy.io/)
- The backend and the proxy for web can be set up with [Docker compose](https://github.com/docker/compose)
- The gRPC client of the Web application is generated with [protoc](https://github.com/protocolbuffers/protobuf) and with [gRPC-Web protoc plugin](https://www.npmjs.com/package/protoc-gen-grpc-web)
- The backend is implemented with NodeJS. The server side of the gRPC communication is implemented with the official [gRPC for NodeJS library](https://grpc.io/docs/languages/node/)

## Further development plans
This project will be developed further into a more complex, but still simple Kotlin Multiplatfrom Application.

## Steps to run and try the Hello World application
1, Generate the gRPC clients for Android and Desktop: `gradlew protos:generateProtos`

2, Start the backend

- on localhost without proxy for web: `gradlew backend:nodeRun`
- on docker with proxy for web: `docker compose up --build`

3, After this the Android, Desktop and Web clients should work:
- Run the Android application on emulator
- Run the Desktop application: `gradlew desktop:run`
- Run the Web application: `gradlew web:browserRun`
