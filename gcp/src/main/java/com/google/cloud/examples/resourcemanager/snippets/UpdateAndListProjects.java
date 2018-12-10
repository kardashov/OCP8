package com.google.cloud.examples.resourcemanager.snippets;

import com.google.cloud.resourcemanager.Project;
import com.google.cloud.resourcemanager.ResourceManager;
import com.google.cloud.resourcemanager.ResourceManagerOptions;

/**
 * A snippet for Google Cloud Resource Manager showing how to update a project and list all projects
 * the user has permission to view.
 */
public class UpdateAndListProjects {

    public static void main(String... args) {
        // Create Resource Manager service object
        // By default, credentials are inferred from the runtime environment.
        ResourceManager resourceManager = ResourceManagerOptions.getDefaultInstance().getService();

        // Get a project from the server
        Project project = resourceManager.get("some-project-id"); // Use an existing project's ID

        // Update a project
        if (project != null) {
            Project newProject =
                    project.toBuilder().addLabel("launch-status", "in-development").build().replace();
            System.out.println(
                    "Updated the labels of project "
                            + newProject.getProjectId()
                            + " to be "
                            + newProject.getLabels());
        }

        // List all the projects you have permission to view.
        System.out.println("Projects I can view:");
        for (Project currentProject : resourceManager.list().iterateAll()) {
            System.out.println(currentProject.getProjectId());
        }
    }
}
