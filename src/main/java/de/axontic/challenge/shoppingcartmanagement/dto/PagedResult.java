package de.axontic.challenge.shoppingcartmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class PagedResult<T> {
    private Integer currentPage;
    private Integer totalPages;
    private Long totalItems;
    private List<T> data;
}
