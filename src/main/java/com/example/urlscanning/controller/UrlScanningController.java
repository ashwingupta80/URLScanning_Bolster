package com.example.urlscanning.controller;

import com.example.urlscanning.core.dto.ScanResultDTO;
import com.example.urlscanning.business.UrlScanningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlScanningController {
    private final UrlScanningService urlScanningService;

    @Autowired
    public UrlScanningController(UrlScanningService urlScanningService) {
        this.urlScanningService = urlScanningService;
    }

    @GetMapping("/")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/scan")
    public ScanResultDTO scan(@RequestParam("url") String url) {
        ScanResultDTO obj = this.urlScanningService.scanUrl(url);
        System.out.println(obj);
        return obj;
    }
}
