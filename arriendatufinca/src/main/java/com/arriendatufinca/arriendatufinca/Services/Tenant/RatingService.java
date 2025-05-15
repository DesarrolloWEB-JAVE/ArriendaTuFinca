package com.arriendatufinca.arriendatufinca.Services.Tenant;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arriendatufinca.arriendatufinca.DTO.RatingDTO;
import com.arriendatufinca.arriendatufinca.Entities.Rating;
import com.arriendatufinca.arriendatufinca.Repositories.RatingRepository;
import com.arriendatufinca.arriendatufinca.Repositories.RentalRequestRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    RentalRequestRepository rentalRequestRepository;
    @Autowired
    ModelMapper modelMapper;


    public RatingDTO rateProperty(RatingDTO ratingDTO) {
        Rating rating = modelMapper.map(ratingDTO, Rating.class);
        rating.setComment(ratingDTO.getComment());
        rating.setRatingValue(ratingDTO.getRating());
        rating.setProperty(ratingDTO.getProperty());
        rating.setUser(ratingDTO.getUser());
        return modelMapper.map(ratingRepository.save(rating), RatingDTO.class);
    }
}
