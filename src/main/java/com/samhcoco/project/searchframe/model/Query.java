package com.samhcoco.project.searchframe.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Query {
    private List<SearchCriteria> searchCriteria;
    private boolean strict;
}
