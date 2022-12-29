package com.samhcoco.project.searchframe.model;

import com.samhcoco.project.searchframe.enums.SortDirection;
import lombok.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static java.util.Objects.nonNull;
import static org.springframework.util.StringUtils.hasText;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Page {
    Short page;
    Short size;
    private String sort;
    private String direction;

    public PageRequest request() {
        Sort sortBy = null;
        if (hasText(sort) && hasText(direction) && SortDirection.types().contains(direction)) {
            sortBy = direction.toUpperCase().equals(SortDirection.DESC.name()) ? Sort.by(sort).descending() :
                                                                                 Sort.by(sort).ascending();
        }

        if (nonNull(page) && nonNull(size)) {
            return nonNull(sortBy) ? PageRequest.of(page - 1, size, sortBy) :
                                     PageRequest.of(page - 1, size);
        }
        return null;
    }
}
