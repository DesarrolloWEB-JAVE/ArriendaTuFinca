package com.arriendatufinca.arriendatufinca.Repositories;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.arriendatufinca.arriendatufinca.Entities.Property;
import com.arriendatufinca.arriendatufinca.Entities.RentalRequest;
import com.arriendatufinca.arriendatufinca.Entities.User;

public interface RentalRequestRepository extends JpaRepository<RentalRequest, Long>, JpaSpecificationExecutor<RentalRequest> {
    
    List<RentalRequest> findByTenant(User tenant);

    @Query("SELECT rr FROM RentalRequest rr JOIN FETCH rr.property JOIN FETCH rr.tenant WHERE rr.tenant = :tenant AND rr.status = com.arriendatufinca.arriendatufinca.Enums.StatusEnum.ACTIVE")
    List<RentalRequest> findByTenantWithPropertyAndTenant(@Param("tenant") User tenant);


    static Specification<RentalRequest> belongsToTenant(User tenant) {
        return (root, query, cb) -> 
            cb.equal(root.get("tenant").get("id"), tenant.getId());
    }
    
    boolean existsByTenantAndProperty(User tenant, Property property);

    List<RentalRequest> findByPropertyLandlordId(Long landlordId);
}
