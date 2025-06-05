package com.arriendatufinca.arriendatufinca.Services.Tenant;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.arriendatufinca.arriendatufinca.DTO.RatingDTO;
import com.arriendatufinca.arriendatufinca.Entities.Property;
import com.arriendatufinca.arriendatufinca.Entities.Rating;
import com.arriendatufinca.arriendatufinca.Repositories.PropertyRepository;
import com.arriendatufinca.arriendatufinca.Repositories.RatingRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    private final PropertyRepository propertyRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public RatingDTO rateProperty(RatingDTO dto) {

        Property property = propertyRepository.findById(dto.getPropertyId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Property not found: " + dto.getPropertyId()));

        if (dto.getRating() == null || dto.getRating() < 1 || dto.getRating() > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }

        Rating rating = new Rating();
        rating.setComment(dto.getComment());
        rating.setRating(dto.getRating());
        rating.setProperty(property);

        Rating saved = ratingRepository.save(rating);
        return modelMapper.map(saved, RatingDTO.class);
    }

    @Transactional
    public List<RatingDTO> getRatingsByPropertyId(Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new EntityNotFoundException("Property not found: " + propertyId));

        List<Rating> ratings = ratingRepository.findByProperty(property);

        return ratings.stream()
                .map(rating -> modelMapper.map(rating, RatingDTO.class))
                .collect(Collectors.toList());
}
}



