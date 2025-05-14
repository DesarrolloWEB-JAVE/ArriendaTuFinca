package com.arriendatufinca.arriendatufinca.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arriendatufinca.arriendatufinca.DTO.PropertyDTO;
import com.arriendatufinca.arriendatufinca.DTO.PropertySearchCriteriaDTO;
import com.arriendatufinca.arriendatufinca.Services.admin.PropertyAdminService;
import com.arriendatufinca.arriendatufinca.Services.Tenant.PropertyService;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    private final PropertyService propertyService;
    private final PropertyAdminService adminService;

    public PropertyController(PropertyService propertyService, PropertyAdminService adminService) {
        this.propertyService = propertyService;
        this.adminService = adminService;
    }

    // Endpoint para búsqueda con filtros (nuevo o corregido)
    @GetMapping
    public ResponseEntity<List<PropertyDTO>> searchProperties(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String searchString) {
        
        List<PropertyDTO> properties;
        if (category == null && searchString == null) {
            properties = propertyService.getAllProperties(); // Obtener todas
        } else {
            PropertySearchCriteriaDTO criteria = new PropertySearchCriteriaDTO();
            criteria.setCategory(category);
            criteria.setSearchString(searchString);
            properties = propertyService.searchProperties(criteria);
        }
        return ResponseEntity.ok(properties);
    }

    // Endpoints existentes (se mantienen igual)
    @GetMapping("/{id}")
    public ResponseEntity<PropertyDTO> getPropertyById(@PathVariable Long id) {
        PropertyDTO property = adminService.getPropertyById(id);
        return ResponseEntity.ok(property);
    }

    @PostMapping("/create")
    public ResponseEntity<PropertyDTO> createProperty(@RequestBody PropertyDTO propertyDTO) {
        PropertyDTO createdProperty = adminService.createProperty(propertyDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProperty);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PropertyDTO> updateProperty(@PathVariable Long id, @RequestBody PropertyDTO propertyDTO) {
        PropertyDTO updatedProperty = adminService.updateProperty(id, propertyDTO);
        return ResponseEntity.ok(updatedProperty);
    }

    @GetMapping("/landlord/{landlordId}") 
    public ResponseEntity<List<String>> getPropertyNamesByUserId(@PathVariable Long landlordId) {
        List<String> propertyNames = adminService.getPropertyNamesByUserId(landlordId);
        return ResponseEntity.ok(propertyNames);
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<PropertyDTO> deactivateProperty(@PathVariable Long id) {
        PropertyDTO deactivatedProperty = adminService.deactivateProperty(id);
        return ResponseEntity.ok(deactivatedProperty);
    }
}