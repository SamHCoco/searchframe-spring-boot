package com.samhcoco.project.searchframe.service;

import com.samhcoco.project.searchframe.model.Product;
import com.samhcoco.project.searchframe.model.Query;

import java.util.Collection;

public interface ProductService {

    /**
     * Queries products based on the provided {@link Query}.
     * @param query {@link Query}.
     * @return Query result as a collection.
     */
    Collection<Product> query(Query query);

}
