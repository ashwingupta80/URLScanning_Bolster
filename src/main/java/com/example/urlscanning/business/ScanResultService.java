package com.example.urlscanning.business;

import com.example.urlscanning.core.dto.ScanResultDTO;
import com.example.urlscanning.repository.ScanResultEntity;
import com.example.urlscanning.repository.ScanResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScanResultService {
    private final ScanResultRepository scanResultRepository;

    @Autowired
    public ScanResultService(ScanResultRepository scanResultRepository) {
        this.scanResultRepository = scanResultRepository;
    }

    public void saveScanResult(ScanResultDTO scanResultDTO) {
        // Convert ScanResultDTO to ScanResult entity
        ScanResultEntity scanResult = convertToEntity(scanResultDTO);

        // Save the entity to the database
        scanResultRepository.save(scanResult);
    }

    private ScanResultEntity convertToEntity(ScanResultDTO scanResultDTO) {
        // Convert ScanResultDTO to ScanResult entity
        ScanResultEntity scanResult = new ScanResultEntity();

        // Stage 1
        scanResult.setScreenshot(scanResultDTO.getScreenshot());
        scanResult.setIpAddress(scanResultDTO.getIpAddress());

        scanResult.setSourceUrls(scanResultDTO.getSourceUrls());
        scanResult.setDestinationUrls(scanResultDTO.getDestinationUrls());

        // Stage 2
        scanResult.setAsnInfo(scanResultDTO.getAsnInformation());
        scanResult.setSslInfo(scanResultDTO.getSsl());

        // Stage 3
        scanResult.setPageSource(scanResultDTO.getPageSource());
        scanResult.setNaturalLanguage(scanResultDTO.getNaturalLanguage());

        return scanResult;
    }
}