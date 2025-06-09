package com.arriendatufinca.arriendatufinca.Specifications;

import org.springframework.data.jpa.domain.Specification;

import com.arriendatufinca.arriendatufinca.Entities.Property;

public class PropertySpecifications {

    public static Specification<Property> withCategory(String category) {
        return (root, query, criteriaBuilder) -> {
            if (category == null || category.isEmpty()) {
                return criteriaBuilder.conjunction(); // No filtrar
            }
            
            // Filtra por diferentes campos según la categoría
            switch(category.toLowerCase()) {
                case "title":
                    return criteriaBuilder.isNotNull(root.get("title"));
                case "description":
                    return criteriaBuilder.isNotNull(root.get("description"));
                case "city":
                    return criteriaBuilder.isNotNull(root.get("city"));
                case "country":
                    return criteriaBuilder.isNotNull(root.get("country"));
                default:
                    return criteriaBuilder.conjunction(); // No filtrar para categorías desconocidas
            }
        };
    }

    public static Specification<Property> withSearchString(String searchString) {
        return (root, query, criteriaBuilder) -> {
            if (searchString == null || searchString.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            
            String pattern = "%" + searchString.toLowerCase() + "%";
            return criteriaBuilder.or(
                criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), pattern),
                criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), pattern),
                criteriaBuilder.like(criteriaBuilder.lower(root.get("city")), pattern),
                criteriaBuilder.like(criteriaBuilder.lower(root.get("country")), pattern)
            );
        };
    }
}
