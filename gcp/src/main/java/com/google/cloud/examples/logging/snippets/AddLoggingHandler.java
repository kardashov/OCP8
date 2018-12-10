package com.google.cloud.examples.logging.snippets;

import com.google.cloud.logging.LoggingHandler;

import java.util.logging.Logger;

/**
 * A snippet showing how to use {@link java.util.logging.Logger} to log entries to Stackdriver
 * Logging. The snippet shows how to install a Stackdriver Logging handler using {@link
 * com.google.cloud.logging.LoggingHandler#addHandler(Logger, LoggingHandler)}. Notice that this
 * could also be done through the {@code logging.properties} file, adding the following line:
 *
 * <pre>
 * {@code com.google.cloud.examples.logging.snippets.AddLoggingHandler.handlers=com.google.cloud.logging.LoggingHandler}
 * </pre>
 */
public class AddLoggingHandler {

    private static final Logger LOGGER = Logger.getLogger(AddLoggingHandler.class.getName());

    public static void main(String... args) {
        // Add the Stackdriver Logging handler
        LoggingHandler.addHandler(LOGGER, new LoggingHandler());

        // log using the logger
        LOGGER.warning("test warning");
    }
}
