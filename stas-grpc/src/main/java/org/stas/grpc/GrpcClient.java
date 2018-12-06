package org.stas.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        HelloServiceGrpc.HelloServiceBlockingStub stub
                = HelloServiceGrpc.newBlockingStub(channel);

        for (int i = 0; i < 2_000_000; i++) {
            HelloResponse helloResponse = stub.hello(HelloRequest.newBuilder()
                    .setFirstName("Stanislav")
                    .setLastName("" + i)
                    .build());
            if (i % 1000 == 0) System.out.println(helloResponse);
        }

        channel.shutdown();
    }
}

