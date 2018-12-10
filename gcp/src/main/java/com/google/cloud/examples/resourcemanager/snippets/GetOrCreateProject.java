package com.google.cloud.examples.resourcemanager.snippets;

import com.google.cloud.resourcemanager.Project;
import com.google.cloud.resourcemanager.ProjectInfo;
import com.google.cloud.resourcemanager.ResourceManager;
import com.google.cloud.resourcemanager.ResourceManagerOptions;

/**
 * A snippet for Google Cloud Resource Manager showing how to create a project if it does not exist.
 */
public class GetOrCreateProject {

    public static void main(String... args) {
        // Create Resource Manager service object.
        // By default, credentials are inferred from the runtime environment.
        ResourceManager resourceManager = ResourceManagerOptions.getDefaultInstance().getService();

        String projectId = "my-globally-unique-project-id"; // Change to a unique project ID.
        // Get a project from the server.
        Project project = resourceManager.get(projectId);
        if (project == null) {
            // Create a project.
            project = resourceManager.create(ProjectInfo.newBuilder(projectId).build());
        }
        System.out.println("Got project " + project.getProjectId() + " from the server.");
    }
}
