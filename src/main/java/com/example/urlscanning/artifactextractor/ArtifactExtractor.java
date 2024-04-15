package com.example.urlscanning.artifactextractor;

import java.util.List;

public interface ArtifactExtractor {
    byte[] takeScreenshot(String url);
    String extractIP(String url);
    String getSslCertificateDetails(String url);
    String getAsnInformation(String ipAddress);
    String extractPageSource(String url);
    String extractNaturalLanguageContent(String url);
    List<String> extractSources(String url);
    List<String> extractDestinations(String url);
}
