package com.arriendatufinca.arriendatufinca.DTO;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class RatingDTO {

    private Long id;
    private String comment;
    private Integer rating;
    private long propertyId;

}
