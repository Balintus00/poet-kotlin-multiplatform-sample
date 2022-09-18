# Kotlin Multiplatform Hello World sample application
A pure kotlin project with kotlinJS backend, Android and Desktop clients with gRPC communication.

A web client implemented with kotlin will also be added soon!

Implementation details:
- The common view logic is implemented with [MVIKotlin](https://arkivanov.github.io/MVIKotlin/) and [Decompose](https://arkivanov.github.io/Decompose/)
- The common UI of the Android and Desktop application is implemented with [Jetbrains compose](https://www.jetbrains.com/lp/compose-mpp/)
- The gRPC client of Android and Desktop application is implemented with [Wire](https://square.github.io/wire/)
- The backend is implemented with NodeJS using kotlinJS. The server side of the gRPC communication is implemented with the official [gRPC for NodeJS library](https://grpc.io/docs/languages/node/)

This project will be developed further into a more complex, but still simple Kotlin Multiplatfrom Application.

## Steps to run and try the Hello World application
1, Generate the gRPC clients for Android and Desktop

`gradlew protos:generateProtos`

2, Start the backend

`gradlew backend:nodeRun`

3, After this the Android and Desktop clients should work:
- Start the Desktop application

`gradlew desktop:run`

- Start the Android application on emulator
