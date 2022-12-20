package com.samhcoco.project.searchframe.service.impl;

import com.samhcoco.project.searchframe.model.Query;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static com.samhcoco.project.searchframe.constants.SearchOperations.*;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class SpecificationBuilder<T> implements Specification<T> {

    private Query searchQuery;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        val searchCriteria = searchQuery.getSearchList();
        if (searchCriteria.isEmpty()) return null;

        val predicates = new Predicate[searchCriteria.size()];

        for (int i = 0; i < searchCriteria.size(); i++) {
            val criteria = searchCriteria.get(i);

            switch (criteria.getOperation()) {
                case EQUAL:
                    predicates[i] = criteriaBuilder.equal(root.get(criteria.getField()), criteria.getValue());
                    break;
                case GREATER_THAN:
                    predicates[i] = criteriaBuilder.greaterThan(root.get(criteria.getField()), criteria.getValue());
                    break;
                case GREATER_THAN_OR_EQUAL:
                    predicates[i] = criteriaBuilder.greaterThanOrEqualTo(root.get(criteria.getField()), criteria.getValue());
                    break;
                case LESS_THAN:
                    predicates[i] = criteriaBuilder.lessThan(root.get(criteria.getField()), criteria.getValue());
                    break;
                case LESS_THAN_OR_EQUAL:
                    predicates[i] = criteriaBuilder.lessThanOrEqualTo(root.get(criteria.getField()), criteria.getValue());
                    break;
                default:
                    log.debug("Required valid search operation missing for search query: {}", searchQuery);

            }
        }
        // Query can be disjunction (condition 1 OR condition 2) or conjunction (condition 1 AND condition 2)
        return searchQuery.isStrict() ? criteriaBuilder.and(predicates) : criteriaBuilder.or(predicates);
    }
}
