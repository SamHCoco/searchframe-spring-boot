package com.samhcoco.project.searchframe.service.impl;

import com.samhcoco.project.searchframe.model.Query;
import com.samhcoco.project.searchframe.service.SearchService;
import lombok.*;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class SqlSearchServiceImpl<T> implements SearchService<T> {

    private final JpaSpecificationExecutor<T> executor;

    @Override
    public Collection<T> query(@NonNull Query query) {
        val specificationBuilder = new SpecificationBuilder<T>(query);
        return executor.findAll(specificationBuilder);
    }
}
