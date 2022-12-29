package com.samhcoco.project.searchframe.service.impl;

import com.samhcoco.project.searchframe.model.Query;
import com.samhcoco.project.searchframe.service.SearchService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

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
    public <T> Object query(@NonNull Query query, @NonNull Class<T> type) {
        val specification = new SpecificationBuilder<T>(query);
        try {
            val className = new StringBuilder(REPOSITORY_PACKAGE).append(type.getSimpleName())
                                                                 .append(REPOSITORY)
                                                                 .toString();

            val repository = (JpaSpecificationExecutor<T>) beanFactory.getBean(Class.forName(className));

            val pageRequest = nonNull(query.getPage()) ? query.getPage().request() : null;

            return nonNull(pageRequest) ? repository.findAll(specification, pageRequest) :
                                          repository.findAll(specification);
        } catch (Exception e) {
            log.error("DATABASE QUERY FAILED - reason: {}", e.getMessage());
        }
        return null;
    }
}
