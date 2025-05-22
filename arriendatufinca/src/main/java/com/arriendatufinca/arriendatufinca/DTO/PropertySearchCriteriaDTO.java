package com.arriendatufinca.arriendatufinca.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertySearchCriteriaDTO {
    private String Category;
    private String searchString;
}
