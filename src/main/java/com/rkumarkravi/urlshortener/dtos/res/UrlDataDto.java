package com.rkumarkravi.urlshortener.dtos.res;

import com.rkumarkravi.urlshortener.models.UrlDataEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UrlDataDto {
    private Long urlId;
    private String url;
    private String shortCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UrlDataDto(UrlDataEntity urlDataEntity){
        this.urlId=urlDataEntity.getUrlId();
        this.url=urlDataEntity.getUrl();
        this.shortCode=urlDataEntity.getShortCode();
        this.createdAt=urlDataEntity.getCreatedAt();
        this.updatedAt=urlDataEntity.getUpdatedAt();
    }
}
