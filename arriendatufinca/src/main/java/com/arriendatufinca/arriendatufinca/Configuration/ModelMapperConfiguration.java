package com.arriendatufinca.arriendatufinca.Configuration;

import com.arriendatufinca.arriendatufinca.DTO.PropertyDTO;
import com.arriendatufinca.arriendatufinca.DTO.RentalRequestDTO;
import com.arriendatufinca.arriendatufinca.Entities.Property;
import com.arriendatufinca.arriendatufinca.Entities.RentalRequest;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // ----- Mapeo para RentalRequest -----
        modelMapper.typeMap(RentalRequestDTO.class, RentalRequest.class)
                .addMappings(mapper -> {
                    mapper.<Long>map(RentalRequestDTO::getTenantId, (dest, v) -> dest.getTenant().setId(v));
                    mapper.<Long>map(RentalRequestDTO::getPropertyId, (dest, v) -> dest.getProperty().setId(v));
                });

        // ----- Mapeo para Property -----
        modelMapper.typeMap(PropertyDTO.class, Property.class)
                .addMappings(mapper -> {
                    mapper.<Long>map(PropertyDTO::getLandlordId, (dest, v) -> dest.getLandlord().setId(v));
                    mapper.skip(Property::setId);
                    mapper.skip(Property::setStatus);
                    mapper.skip(Property::setPhotos);
                    mapper.skip(Property::setRentalRequests);
                });

        // ----- Mapeo inverso (Property -> PropertyDTO) -----
        modelMapper.typeMap(Property.class, PropertyDTO.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getLandlord().getId(), PropertyDTO::setLandlordId);
                });

        return modelMapper;
    }
}