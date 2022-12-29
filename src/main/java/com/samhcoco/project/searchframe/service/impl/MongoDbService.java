package com.samhcoco.project.searchframe.service.impl;

import com.samhcoco.project.searchframe.model.Query;
import com.samhcoco.project.searchframe.service.SearchService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

import static com.samhcoco.project.searchframe.constant.SearchOperations.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class MongoDbService implements SearchService {

    private final MongoTemplate mongoTemplate;

    @Override
    public <T> Collection<T> query(@NonNull Query query, @NonNull Class<T> type) {
        val searchCriteria = query.getSearchCriteria();
        val mongoQuery = new org.springframework.data.mongodb.core.query.Query();

        Criteria criteria = null;

        for (int i = 0; i < searchCriteria.size(); i++) {
            var search = searchCriteria.get(i);

            if (i == 0) {
                criteria = Criteria.where(search.getField());
            } else {
                criteria = criteria.and(search.getField());
            }

            switch (search.getOperation()) {
                case LIKE:
                    // will return match if value found as substring in field: i - option to make matching case-insensitive
                    criteria.regex(".*" + search.getValue() + ".*", "i");
                    break;
                case EQUAL:
                    criteria.is(search.getValue());
                    break;
                case GREATER_THAN:
                    criteria.gt(search.getValue());
                    break;
                case GREATER_THAN_OR_EQUAL:
                    criteria.gte(search.getValue());
                    break;
                case LESS_THAN:
                    criteria.lt(search.getValue());
                    break;
                case LESS_THAN_OR_EQUAL:
                    criteria.lte(search.getValue());
                    break;
                default:
                    log.error("No valid search operation provided for MongoDB query.");
            }
        }
        mongoQuery.addCriteria(criteria);
        return mongoTemplate.find(mongoQuery, type);
    }

    /**
     * Validates a given {@link Query} object.
     * @param query {@link Query}.
     * @return Error reason if validation failed.
     */
    private Map<String, String> validateQuery(@NonNull Query query) {
        return null;
    }

}
