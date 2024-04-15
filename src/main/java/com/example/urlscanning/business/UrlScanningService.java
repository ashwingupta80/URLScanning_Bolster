package com.example.urlscanning.business;

import com.example.urlscanning.core.dto.ScanResultDTO;
import com.example.urlscanning.artifactextractor.ArtifactExtractor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UrlScanningService {
    private final ArtifactExtractor playwrightArtifactExtractor;
    private final ScanResultService scanResultService;

    @Autowired
    public UrlScanningService(ArtifactExtractor playwrightArtifactExtractor, ScanResultService scanResultService){
        this.playwrightArtifactExtractor = playwrightArtifactExtractor;
        this.scanResultService = scanResultService;
    }

    public ScanResultDTO scanUrl(String url) {
        byte[] screenshot = playwrightArtifactExtractor.takeScreenshot(url);
        String ipAddress = playwrightArtifactExtractor.extractIP(url);
        String sslDetails = playwrightArtifactExtractor.getSslCertificateDetails(url);
        String asn = playwrightArtifactExtractor.getAsnInformation(ipAddress);
        String pageSource = playwrightArtifactExtractor.extractPageSource(url);
        String naturalLanguage = playwrightArtifactExtractor.extractNaturalLanguageContent(url);
        List<String> sources = playwrightArtifactExtractor.extractSources(url);
        List<String> destinations = playwrightArtifactExtractor.extractDestinations(url);

        ScanResultDTO scanResultDTO = new ScanResultDTO(screenshot, ipAddress, sources, destinations, sslDetails, asn, pageSource, naturalLanguage);
        save(scanResultDTO);

        return scanResultDTO;
    }

    private void save(ScanResultDTO scanResultDTO) {
        scanResultService.saveScanResult(scanResultDTO);
    }
}
