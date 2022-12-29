package com.samhcoco.project.searchframe.service.impl;

import com.samhcoco.project.searchframe.model.Queries;
import com.samhcoco.project.searchframe.model.VehicleProduct;
import com.samhcoco.project.searchframe.model.Query;
import com.samhcoco.project.searchframe.service.ProductService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class VehicleProductService implements ProductService<VehicleProduct> {

    private final RdbSearchService searchService;

    @Override
    public Collection<VehicleProduct> query(@NonNull Query query) {
        return searchService.query(query, VehicleProduct.class);
    }

}
