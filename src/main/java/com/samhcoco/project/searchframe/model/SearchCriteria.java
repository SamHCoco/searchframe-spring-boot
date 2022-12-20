package com.samhcoco.project.searchframe.model;

import lombok.*;

/**
 * Model that represents a search operation for a single value
 * of a single field.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SearchCriteria {
    private String field;
    private String value;
    private String operation;
}
