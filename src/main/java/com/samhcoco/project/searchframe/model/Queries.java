package com.samhcoco.project.searchframe.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Queries {
    private List<Query> queries;
    private boolean strict;
}
