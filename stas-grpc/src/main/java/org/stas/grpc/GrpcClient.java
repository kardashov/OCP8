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

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100_000; i++) {
            HelloResponse helloResponse = stub.hello(HelloRequest.newBuilder()
                    .setFirstName("Stanislav")
                    .setLastName("" + i)
                    .build());
//            if (i % 100 == 0) System.out.println(helloResponse);
        }
        System.out.println("Completed in " + (System.currentTimeMillis() - start) / 1000);

        channel.shutdown();
    }
}

