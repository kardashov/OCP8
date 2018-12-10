package com.google.cloud.examples.language.snippets;

import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;

/**
 * A snippet for Google Cloud Speech API showing how to analyze text message sentiment.
 */
public class AnalyzeSentiment {

    public static void main(String... args) throws Exception {
        // Instantiates a client
        LanguageServiceClient language = LanguageServiceClient.create();

        // The text to analyze
        String[] texts = {"I love this!", "I hate this!"};
        for (String text : texts) {
            Document doc = Document.newBuilder().setContent(text).setType(Type.PLAIN_TEXT).build();
            // Detects the sentiment of the text
            Sentiment sentiment = language.analyzeSentiment(doc).getDocumentSentiment();

            System.out.printf("Text: \"%s\"%n", text);
            System.out.printf(
                    "Sentiment: score = %s, magnitude = %s%n",
                    sentiment.getScore(), sentiment.getMagnitude());
        }
    }
}
