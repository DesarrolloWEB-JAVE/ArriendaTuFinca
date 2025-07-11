package com.arriendatufinca.arriendatufinca.Entities;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.arriendatufinca.arriendatufinca.Enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"ownedProperties", "rentedProperties", "rentalRequests"})
@SQLRestriction("status = 0")
@SQLDelete(sql = "UPDATE user SET status = 1 WHERE id=?")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String name;
    private String lastName;
    private String email;

    private StatusEnum status = StatusEnum.ACTIVE;

    // As landlord (owned properties)
    @OneToMany(mappedBy = "landlord", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Property> ownedProperties = new ArrayList<>();

    // As tenant (rented properties)
    @ManyToMany
    @JoinTable(
        name = "user_rented_properties",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "property_id")
    )
    private List<Property> rentedProperties = new ArrayList<>();

    // Rental requests created by the tenant
    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL)
    private List<RentalRequest> rentalRequests = new ArrayList<>();
}
