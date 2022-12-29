package com.samhcoco.project.searchframe.service;

import com.samhcoco.project.searchframe.model.Query;

public interface SearchService {

    /**
     * Queries a datasource and returns the results.
     * @param query {@link Query}.
     * @return Query result.
     */
    <T> Object query(Query query, Class<T> type);


}
