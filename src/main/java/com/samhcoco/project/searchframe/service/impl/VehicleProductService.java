package com.samhcoco.project.searchframe.service.impl;

import com.samhcoco.project.searchframe.model.Query;
import com.samhcoco.project.searchframe.model.VehicleProduct;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class VehicleProductService {

    private final RdbSearchService searchService;

    public Object query(@NonNull Query query) {
        return searchService.query(query, VehicleProduct.class);
    }

}
