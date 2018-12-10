package com.google.cloud.examples.pubsub.snippets;

import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.NoCredentialsProvider;
import com.google.api.gax.grpc.GrpcTransportChannel;
import com.google.api.gax.rpc.FixedTransportChannelProvider;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.cloud.pubsub.v1.TopicAdminClient;
import com.google.cloud.pubsub.v1.TopicAdminSettings;
import com.google.pubsub.v1.ProjectTopicName;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.io.IOException;

/**
 * Snippet that demonstrates creating Pub/Sub clients using the Google Cloud Pub/Sub emulator.
 *
 * <p>Note: clients cannot start/stop the emulator.
 */
public class UsePubSubEmulatorSnippet {

    public static void main(String... args) throws IOException {
        // [START use_pubsub_emulator]
        String hostport = System.getenv("PUBSUB_EMULATOR_HOST");
        ManagedChannel channel = ManagedChannelBuilder.forTarget(hostport).usePlaintext(true).build();
        try {
            TransportChannelProvider channelProvider =
                    FixedTransportChannelProvider.create(GrpcTransportChannel.create(channel));
            CredentialsProvider credentialsProvider = NoCredentialsProvider.create();

            // Set the channel and credentials provider when creating a `TopicAdminClient`.
            // Similarly for SubscriptionAdminClient
            TopicAdminClient topicClient =
                    TopicAdminClient.create(
                            TopicAdminSettings.newBuilder()
                                    .setTransportChannelProvider(channelProvider)
                                    .setCredentialsProvider(credentialsProvider)
                                    .build());

            ProjectTopicName topicName = ProjectTopicName.of("my-project-id", "my-topic-id");
            // Set the channel and credentials provider when creating a `Publisher`.
            // Similarly for Subscriber
            Publisher publisher =
                    Publisher.newBuilder(topicName)
                            .setChannelProvider(channelProvider)
                            .setCredentialsProvider(credentialsProvider)
                            .build();
        } finally {
            channel.shutdown();
        }
        // [END use_pubsub_emulator]
    }
}
