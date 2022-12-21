package com.samhcoco.project.searchframe.service.impl;

import com.samhcoco.project.searchframe.model.Queries;
import com.samhcoco.project.searchframe.model.VehicleProduct;
import com.samhcoco.project.searchframe.model.Query;
import com.samhcoco.project.searchframe.service.ProductService;
import com.samhcoco.project.searchframe.service.SearchService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class VehicleProductService implements ProductService, SearchService<VehicleProduct> {

    private final RdbSearchService<VehicleProduct> searchService;

    @Override
    public Collection<VehicleProduct> query(@NonNull Query query) {
        return searchService.query(query);
    }

    @Override
    public Collection<VehicleProduct> query(@NonNull Queries queries) {
        return searchService.query(queries);
    }
}
