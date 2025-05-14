package com.arriendatufinca.arriendatufinca.Controllers;

import org.springframework.http.ResponseEntity;
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
        return ResponseEntity.ok(ratingService.rateProperty(ratingDTO));
    }
}
