package com.samhcoco.project.searchframe.service;

import com.samhcoco.project.searchframe.model.Queries;
import com.samhcoco.project.searchframe.model.Query;

import java.util.Collection;

public interface SearchService {

    /**
     * Queries a datasource and returns the results.
     * @param query {@link Query}.
     * @return Query result.
     */
    <T> Collection<T> query(Query query, Class<T> type);

}
