package com.rkumarkravi.urlshortener.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class UrlDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "url_id", nullable = false)
    private Long urlId;
    private String url;
    @Column(unique = true)
    private String shortCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private long accessCount;

}
