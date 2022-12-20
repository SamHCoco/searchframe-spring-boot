package com.samhcoco.project.searchframe.service.impl;

import com.samhcoco.project.searchframe.model.Product;
import com.samhcoco.project.searchframe.model.Query;
import com.samhcoco.project.searchframe.service.ProductService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final SqlSearchServiceImpl<Product> productSearchService;

    @Override
    public Collection<Product> query(@NonNull Query query) {
        val result = productSearchService.query(query);
        log.info("Query {} result: {}", query, result); // todo - remove
        return result;
    }
}
