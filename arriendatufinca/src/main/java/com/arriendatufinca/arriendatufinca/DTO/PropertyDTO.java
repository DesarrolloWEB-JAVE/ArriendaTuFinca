package com.arriendatufinca.arriendatufinca.DTO;

import com.arriendatufinca.arriendatufinca.Enums.PropertyState;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDTO {

    private Long id;
    private Long landlordId;
    private String title;
    private String description;
    private String address;
    private int bathrooms;
    private int bedrooms; 
    private String city;
    private double price;
    private PropertyState state;
    private String photos;
    private String country;
}
