package com.samhcoco.project.searchframe.service.impl;

import com.samhcoco.project.searchframe.model.Queries;
import com.samhcoco.project.searchframe.model.Query;
import com.samhcoco.project.searchframe.service.SearchService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static java.lang.String.format;

/**
 * Generic service for performing search queries on relational databases.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RdbSearchService implements SearchService {

    private static final String REPOSITORY_PACKAGE = "com.samhcoco.project.searchframe.repository.";
    private static final String REPOSITORY = "Repository";

    private final BeanFactory beanFactory;

    @Override
    public <T> Collection<T> query(@NonNull Query query, @NonNull Class<T> type) {
        val specificationBuilder = new SpecificationBuilder<T>(query);
        try {
            val className = new StringBuilder(REPOSITORY_PACKAGE).append(type.getSimpleName())
                                                                 .append(REPOSITORY)
                                                                 .toString();

            val repository = (JpaSpecificationExecutor<T>) beanFactory.getBean(Class.forName(className));
            return repository.findAll(specificationBuilder);
        } catch (Exception e) {
            log.error("DATABASE QUERY FAILED - reason: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public <T> Collection<T> query(@NonNull Queries queries, @NonNull Class<T> type) {
        // todo - implement
        return null;
    }
}
