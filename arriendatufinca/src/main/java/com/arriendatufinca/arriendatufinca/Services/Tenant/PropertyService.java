package com.arriendatufinca.arriendatufinca.Services.Tenant;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.arriendatufinca.arriendatufinca.DTO.PropertyDTO;
import com.arriendatufinca.arriendatufinca.DTO.PropertySearchCriteriaDTO;
import com.arriendatufinca.arriendatufinca.Entities.Property;
import com.arriendatufinca.arriendatufinca.Repositories.PropertyRepository;
import com.arriendatufinca.arriendatufinca.Specifications.PropertySpecifications;


@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final ModelMapper modelMapper;

    public PropertyService(PropertyRepository propertyRepository, ModelMapper modelMapper) {
        this.propertyRepository = propertyRepository;
        this.modelMapper = modelMapper;
    }

    public List<PropertyDTO> getAllProperties() {
        return propertyRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<PropertyDTO> searchProperties(PropertySearchCriteriaDTO criteria) {
        Specification<Property> spec = Specification.where(
            PropertySpecifications.withCategory(criteria.getCategory()))
            .and(PropertySpecifications.withSearchString(criteria.getSearchString()));
        
        return propertyRepository.findAll(spec).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private PropertyDTO convertToDto(Property property) {
        return modelMapper.map(property, PropertyDTO.class);
    }

    // private Property convertToEntity(PropertyDTO propertyDTO) {
    //     return modelMapper.map(propertyDTO, Property.class);
    // }
}