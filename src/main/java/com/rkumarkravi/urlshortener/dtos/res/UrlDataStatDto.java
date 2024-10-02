package com.rkumarkravi.urlshortener.dtos.res;

import com.rkumarkravi.urlshortener.models.UrlDataEntity;
import lombok.Data;

@Data
public class UrlDataStatDto extends UrlDataDto{
    private long accessCount;
    public UrlDataStatDto(UrlDataEntity urlDataEntity){
        super(urlDataEntity);
        this.accessCount=urlDataEntity.getAccessCount();
    }
}
