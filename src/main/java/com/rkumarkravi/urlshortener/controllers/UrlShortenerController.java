package com.rkumarkravi.urlshortener.controllers;

import com.rkumarkravi.urlshortener.dtos.req.CreateShortUrlDto;
import com.rkumarkravi.urlshortener.services.UrlShortenerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UrlShortenerController {

    private final UrlShortenerService urlShortenerService;

    public UrlShortenerController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<?> createShortenUrl(@RequestBody CreateShortUrlDto shortUrlDto){
        return this.urlShortenerService.createShortUrl(shortUrlDto);
    }

    @GetMapping("/shorten/{shortCode}")
    public ResponseEntity<?> getShortenUrl(@PathVariable String shortCode){
        return this.urlShortenerService.getShortUrl(shortCode);
    }

    @GetMapping("/shorten/{shortCode}/stats")
    public ResponseEntity<?> getShortenUrlStats(@PathVariable String shortCode){
        return this.urlShortenerService.getShortUrlStats(shortCode);
    }

    @PutMapping("/shorten/{shortCode}")
    public ResponseEntity<?> updateShortenUrl(@PathVariable String shortCode,@RequestBody CreateShortUrlDto shortUrlDto){
        return this.urlShortenerService.updateShortUrl(shortCode,shortUrlDto);
    }

    @DeleteMapping("/shorten/{shortCode}")
    public ResponseEntity<?> updateShortenUrl(@PathVariable String shortCode){
        return this.urlShortenerService.deleteShortUrl(shortCode);
    }
}
