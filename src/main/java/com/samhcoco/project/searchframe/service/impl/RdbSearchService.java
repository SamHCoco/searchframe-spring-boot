package com.samhcoco.project.searchframe.service.impl;

import com.samhcoco.project.searchframe.model.Queries;
import com.samhcoco.project.searchframe.model.Query;
import com.samhcoco.project.searchframe.service.SearchService;
import lombok.*;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Generic service for performing search queries on relational databases.
 * @param <T> The database entity on which queries will be performed.
 */
@Service
@RequiredArgsConstructor
public class RdbSearchService<T> implements SearchService<T> {

    private final JpaSpecificationExecutor<T> executor;

    @Override
    public Collection<T> query(@NonNull Query query) {
        val specificationBuilder = new SpecificationBuilder<T>(query);
        return executor.findAll(specificationBuilder);
    }

    @Override
    public Collection<T> query(@NonNull Queries queries) {
        // todo - implement
        return null;
    }
}
