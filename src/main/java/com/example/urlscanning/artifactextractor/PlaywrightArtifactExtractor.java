package com.example.urlscanning.artifactextractor;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.AsnResponse;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitUntilState;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.io.File;
import java.io.IOException;
import java.net.*;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PlaywrightArtifactExtractor implements ArtifactExtractor{
    private final Playwright playwright;
    private Browser browser;

    public PlaywrightArtifactExtractor() {
        this.playwright = Playwright.create();
        this.browser = playwright.chromium().launch();
    }

    @Override
    public byte[] takeScreenshot(String url) {
        try{
            Page page = this.browser.newPage();
            page.navigate(url);
            byte[] screenshot = page.screenshot();
            page.close();
            return screenshot;
        } catch (PlaywrightException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String extractIP(String url) {
        try{
            String hostname = new URL(url).getHost();
            InetAddress address = InetAddress.getByName(hostname);
            return address.getHostAddress();
        } catch(PlaywrightException e){
            e.printStackTrace();
            return null;
        } catch (MalformedURLException | UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> extractSources(String url) {
        try{
            Page page = this.browser.newPage();
            List<String> sources = new ArrayList<>();

            page.route("**/*", route ->{
                Request request = route.request();
                String source = request.url();
                sources.add(source);
                route.resume();
            });

            page.navigate(url, new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED));

            page.close();

            return sources;
        } catch(PlaywrightException e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    @Override
    public List<String> extractDestinations(String url) {
        try {
            Page page = browser.newPage();
            List<String> destinationUrls = new ArrayList<>();

            // Navigate to the URL
            page.navigate(url);

            // Evaluate JavaScript to capture redirection URLs
            String script = "(() => { return Array.from(document.querySelectorAll('a')).map(a => a.href); })()";
            destinationUrls = (List<String>) page.evaluate(script);

            page.close();

            return destinationUrls;
        } catch (PlaywrightException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public String getAsnInformation(String ipAddress) {
        try {
            // Load MaxMind ASN database
            File database = new File("src/main/resources/GeoLite2-ASN.mmdb");
            DatabaseReader reader = new DatabaseReader.Builder(database).build();

            // Get ASN information
            InetAddress inetAddress = InetAddress.getByName(ipAddress);
            AsnResponse response = reader.asn(inetAddress);

            // Extract ASN information
            return "Autonomous System Number (ASN): " + response.getAutonomousSystemNumber() +
                    "| Autonomous System Organization (ASO): " + response.getAutonomousSystemOrganization();
        } catch (IOException | GeoIp2Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String extractPageSource(String url) {
        try {
            Page page = browser.newPage();
            page.navigate(url);
            String pageSource = page.content();
            page.close();
            return pageSource;
        } catch (PlaywrightException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String extractNaturalLanguageContent(String url) {
        try {
            Page page = browser.newPage();
            page.navigate(url);
            String naturalLanguageContent = page.evaluate("() => document.body.innerText").toString();
            page.close();
            return naturalLanguageContent;
        } catch (PlaywrightException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getSslCertificateDetails(String url) {
        try {
            URL httpsUrl = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) httpsUrl.openConnection();
            connection.connect();
            Certificate[] certs = connection.getServerCertificates();
            X509Certificate cert = (X509Certificate) certs[0];
            return "Subject: " + cert.getSubjectX500Principal().getName() + "\nIssuer: " + cert.getIssuerX500Principal().getName();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PreDestroy
    protected void cleanup() {
        if(this.browser != null){
            browser.close();
        }
    }
}
