package com.samhcoco.project.searchframe.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Query {
    private List<SearchCriteria> searchList;
    /*
        If set true, only records that match all given SearchCriteria should be returned.
        If false, records that match at least one of the search criteria returned
     */
    private boolean strict;
}
