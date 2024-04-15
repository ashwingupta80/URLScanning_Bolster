package com.example.urlscanning.core.dto;

import java.util.List;

public class ScanResultDTO {
    private final byte[] screenshot;
    private final String ipAddress;
    private final List<String> sourceUrls;
    private final List<String> destinationUrls;
    private final String ssl;
    private final String asnInformation;
    private final String pageSource;
    private final String naturalLanguage;

    public ScanResultDTO(byte[] screenshot, String ipAddress, List<String> sourceUrls, List<String> destinationUrls, String ssl, String asnInformation, String pageSource, String naturalLanguage) {
        this.screenshot = screenshot;
        this.ipAddress = ipAddress;
        this.ssl = ssl;
        this.asnInformation = asnInformation;
        this.sourceUrls = sourceUrls;
        this.destinationUrls = destinationUrls;
        this.pageSource = pageSource;
        this.naturalLanguage = naturalLanguage;
    }

    public byte[] getScreenshot() {
        return screenshot;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public List<String> getSourceUrls() {
        return sourceUrls;
    }

    public List<String> getDestinationUrls() {
        return destinationUrls;
    }

    public String getSsl() {
        return ssl;
    }

    public String getAsnInformation() {
        return asnInformation;
    }

    public String getPageSource() {
        return pageSource;
    }

    public String getNaturalLanguage() {
        return naturalLanguage;
    }
}
