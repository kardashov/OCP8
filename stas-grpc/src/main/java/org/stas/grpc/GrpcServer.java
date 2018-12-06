package org.stas.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.Executors;

public class GrpcServer {
    public static void main(String[] args) throws InterruptedException, IOException {
        Server server = ServerBuilder
                .forPort(8080)
                .executor(Executors.newFixedThreadPool(8))
                .addService(new HelloServiceImpl()).build();

        server.start();
        server.awaitTermination();
    }
}