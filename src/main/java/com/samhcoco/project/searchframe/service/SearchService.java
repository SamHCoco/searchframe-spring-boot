package com.samhcoco.project.searchframe.service;

import com.samhcoco.project.searchframe.model.Queries;
import com.samhcoco.project.searchframe.model.Query;

import java.util.Collection;

public interface SearchService<T> {

    /**
     * Queries a datasource and returns the results.
     * @param query {@link Query}.
     * @return Query result.
     */
    Collection<T> query(Query query);

    /**
     * A method for performing more complex queries by combining {@link Query} conditions.
     * @param queries {@link Query}.
     * @return Query result.
     */
    Collection<T> query(Queries queries);

}
