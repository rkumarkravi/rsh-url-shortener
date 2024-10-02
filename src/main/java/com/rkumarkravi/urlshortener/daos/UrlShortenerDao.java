package com.rkumarkravi.urlshortener.daos;

import com.rkumarkravi.urlshortener.models.UrlDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface UrlShortenerDao extends JpaRepository<UrlDataEntity,Long> {
    @Query("select u from UrlDataEntity u where u.shortCode = ?1")
    Optional<UrlDataEntity> findByShortCode(@NonNull String shortCode);

}
