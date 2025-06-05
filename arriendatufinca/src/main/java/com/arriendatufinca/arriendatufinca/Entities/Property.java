package com.arriendatufinca.arriendatufinca.Entities;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLRestriction;

import com.arriendatufinca.arriendatufinca.Enums.PropertyState;
import com.arriendatufinca.arriendatufinca.Enums.StatusEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"landlord", "rentalRequests", "photos"})
@SQLRestriction("status = 0")
@Table(name = "property")
public class Property {
    // Property ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Owner of the property
    @ManyToOne
    @JoinColumn(name = "landlord_id", nullable = false)
    private User landlord;

    //Property information
    private String title;
    private String description;
    private int bathrooms;
    private int bedrooms;
    private String address;
    private String country;
    private String city;
    private double price;
    private String photos;

    // Database state
    private StatusEnum status = StatusEnum.ACTIVE;

    // Property state
    @Enumerated(EnumType.STRING)
    private PropertyState propertyState = PropertyState.AVAILABLE;

    // Rating
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings = new ArrayList<>();

    //request list
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RentalRequest> rentalRequests = new ArrayList<>();
    
}