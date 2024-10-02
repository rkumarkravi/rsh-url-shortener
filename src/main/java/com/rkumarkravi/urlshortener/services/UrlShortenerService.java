package com.rkumarkravi.urlshortener.services;

import com.rkumarkravi.urlshortener.daos.UrlShortenerDao;
import com.rkumarkravi.urlshortener.dtos.req.CreateShortUrlDto;
import com.rkumarkravi.urlshortener.dtos.res.UrlDataDto;
import com.rkumarkravi.urlshortener.dtos.res.UrlDataStatDto;
import com.rkumarkravi.urlshortener.models.UrlDataEntity;
import com.rkumarkravi.urlshortener.utils.Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UrlShortenerService {
    private final UrlShortenerDao urlShortenerDao;

    public UrlShortenerService(UrlShortenerDao urlShortenerDao) {
        this.urlShortenerDao = urlShortenerDao;
    }

    public ResponseEntity<?> createShortUrl(CreateShortUrlDto createShortUrlDto) {
        UrlDataEntity urlDataEntity = new UrlDataEntity();
        urlDataEntity.setUrlId(0L);
        urlDataEntity.setUrl(createShortUrlDto.getUrl());
        urlDataEntity.setShortCode(Util.generateUniqueValue((int)System.currentTimeMillis()));
        urlDataEntity.setCreatedAt(LocalDateTime.now());
//        urlDataEntity.setUpdatedAt(LocalDateTime.now());
        urlDataEntity.setAccessCount(0L);
        return new ResponseEntity<>(new UrlDataDto(urlShortenerDao.save(urlDataEntity)),HttpStatus.OK);
    }

    public ResponseEntity<?> getShortUrl(String shortCode) {
        var optionalUrlDataPojo = urlShortenerDao.findByShortCode(shortCode);
        if (optionalUrlDataPojo.isPresent()) {
            var updatedUrlPojo = optionalUrlDataPojo.get();
            updatedUrlPojo.setAccessCount(updatedUrlPojo.getAccessCount() + 1);
            optionalUrlDataPojo = Optional.of(urlShortenerDao.save(updatedUrlPojo));
        }
        return optionalUrlDataPojo
                .map(urlDataEntity -> new ResponseEntity<>(new UrlDataDto(urlDataEntity), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<?> getShortUrlStats(String shortCode) {
        var optionalUrlDataPojo = urlShortenerDao.findByShortCode(shortCode);
        return optionalUrlDataPojo
                .map(urlDataEntity -> new ResponseEntity<>(new UrlDataStatDto(urlDataEntity), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<?> updateShortUrl(String shortCode, CreateShortUrlDto createShortUrlDto) {
        var optionalUrlDataPojo = urlShortenerDao.findByShortCode(shortCode);

        if (optionalUrlDataPojo.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            var updatedUrlPojo = optionalUrlDataPojo.get();
            updatedUrlPojo.setUrl(createShortUrlDto.getUrl());
            updatedUrlPojo.setUpdatedAt(LocalDateTime.now());

            updatedUrlPojo = urlShortenerDao.save(updatedUrlPojo);

            return new ResponseEntity<>(new UrlDataDto(updatedUrlPojo), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> deleteShortUrl(String shortCode) {
        var optionalUrlDataPojo = urlShortenerDao.findByShortCode(shortCode);
        if (optionalUrlDataPojo.isPresent()) {
            urlShortenerDao.deleteById(optionalUrlDataPojo.get().getUrlId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
