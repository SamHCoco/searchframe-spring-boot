package com.samhcoco.project.searchframe.service;

import com.samhcoco.project.searchframe.model.Query;

import java.util.Collection;

/**
 * For services which allow queries (database searches) on their entity may be performed.
 * @param <T> The entity class.
 */
public interface Searchable<T> {

    /**
     * Queries products based on the provided {@link Query}.
     * @param query {@link Query}.
     * @return Query result.
     */
    Collection<T> query(Query query);

}
