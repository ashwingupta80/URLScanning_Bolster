package com.example.urlscanning.repository;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "scan_result")
public class ScanResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private byte[] screenshot;

    private String ipAddress;

    private String asnInfo;

    private String sslInfo;

    @Column(length = 1000000)
    private String pageSource;

    @Column(length = 1000000)
    private String naturalLanguage;

    @ElementCollection
    @CollectionTable(name = "source_urls", joinColumns = @JoinColumn(name = "scan_result_id"))
    @Column(name = "source_url", length =  10000000)
    private List<String> sourceUrls;

    @ElementCollection
    @CollectionTable(name = "destination_urls", joinColumns = @JoinColumn(name = "scan_result_id"))
    @Column(name = "destination_url", length = 10000000)
    private List<String> destinationUrls;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(byte[] screenshot) {
        this.screenshot = screenshot;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getAsnInfo() {
        return asnInfo;
    }

    public void setAsnInfo(String asnInfo) {
        this.asnInfo = asnInfo;
    }

    public String getSslInfo() {
        return sslInfo;
    }

    public void setSslInfo(String sslInfo) {
        this.sslInfo = sslInfo;
    }

    public List<String> getSourceUrls() {
        return sourceUrls;
    }

    public void setSourceUrls(List<String> sourceUrls) {
        this.sourceUrls = sourceUrls;
    }

    public List<String> getDestinationUrls() {
        return destinationUrls;
    }

    public void setDestinationUrls(List<String> destinationUrls) {
        this.destinationUrls = destinationUrls;
    }

    public String getPageSource() {
        return pageSource;
    }

    public void setPageSource(String pageSource) {
        this.pageSource = pageSource;
    }

    public String getNaturalLanguage() {
        return naturalLanguage;
    }

    public void setNaturalLanguage(String naturalLanguage) {
        this.naturalLanguage = naturalLanguage;
    }
}