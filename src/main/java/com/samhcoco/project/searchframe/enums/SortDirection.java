package com.samhcoco.project.searchframe.enums;

import java.util.Set;

public enum SortDirection {
    ASC,
    DESC;

    public static Set<String> types() {
        return Set.of(ASC.name(), DESC.name());
    }
}
