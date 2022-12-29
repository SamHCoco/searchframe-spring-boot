package com.samhcoco.project.searchframe.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Query {
    private List<SearchCriteria> searchCriteria;
    private boolean strict;
    private Page page;
}
