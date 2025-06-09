package com.arriendatufinca.arriendatufinca.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arriendatufinca.arriendatufinca.DTO.RatingDTO;
import com.arriendatufinca.arriendatufinca.Services.Tenant.RatingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ratings")
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;

    @PostMapping("/comment")
    public ResponseEntity<RatingDTO> rateLandlord(@RequestBody RatingDTO ratingDTO) {
        RatingDTO savedRating = ratingService.rateProperty(ratingDTO);
        return ResponseEntity.ok(savedRating);
    }

    @GetMapping("/getrating/{propertyId}")
    public ResponseEntity<List<RatingDTO>> getRatingsForProperty(@PathVariable Long propertyId) {
        List<RatingDTO> ratings = ratingService.getRatingsByPropertyId(propertyId);
        return ResponseEntity.ok(ratings);
    }
}
