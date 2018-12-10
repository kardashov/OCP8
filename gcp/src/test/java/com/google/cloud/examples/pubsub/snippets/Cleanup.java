package com.google.cloud.examples.pubsub.snippets;

import com.google.cloud.pubsub.v1.SubscriptionAdminClient;
import com.google.cloud.pubsub.v1.TopicAdminClient;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.ProjectTopicName;

class Cleanup {

    protected static void deleteTestTopicsAndSubscriptions(
            String projectId, String[] topics, String[] subscriptions) throws Exception {
        deleteTestTopics(projectId, topics);
        deleteTestSubscriptions(projectId, subscriptions);
    }

    private static void deleteTestTopics(String projectId, String[] testTopics) throws Exception {
        try (TopicAdminClient topicAdminClient = TopicAdminClient.create()) {
            for (String topicId : testTopics) {
                try {
                    topicAdminClient.deleteTopic(ProjectTopicName.of(projectId, topicId));
                    System.out.println("Topic deleted : " + topicId);
                } catch (Exception e) {
                    // do nothing catch clause
                }
            }
        }
    }

    private static void deleteTestSubscriptions(String projectId, String[] subscriptions)
            throws Exception {
        try (SubscriptionAdminClient subscriptionAdminClient = SubscriptionAdminClient.create()) {
            for (String subscriptionId : subscriptions) {
                try {
                    subscriptionAdminClient.deleteSubscription(
                            ProjectSubscriptionName.of(projectId, subscriptionId));
                    System.out.println("Subscription deleted : " + subscriptionId);
                } catch (Exception e) {
                    // do nothing catch clause
                }
            }
        }
    }
}
