package com.arriendatufinca.arriendatufinca.DTO;



import com.arriendatufinca.arriendatufinca.Entities.Property;
import com.arriendatufinca.arriendatufinca.Entities.User;

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
    private Property property;
    private User user;
}
