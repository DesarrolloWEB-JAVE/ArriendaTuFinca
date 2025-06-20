package com.arriendatufinca.arriendatufinca.Services.Tenant;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arriendatufinca.arriendatufinca.DTO.RentalRequestDTO;
import com.arriendatufinca.arriendatufinca.Entities.RentalRequest;
import com.arriendatufinca.arriendatufinca.Entities.User;
import com.arriendatufinca.arriendatufinca.Enums.RequestState;
import com.arriendatufinca.arriendatufinca.Enums.StatusEnum;
import com.arriendatufinca.arriendatufinca.Repositories.RentalRequestRepository;
import com.arriendatufinca.arriendatufinca.Repositories.UserRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class RentalRequestService {
    @Autowired
    RentalRequestRepository rentalRequestRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    
    public RentalRequestDTO createRentalRequest(RentalRequestDTO rentalRequestDTO) {
        RentalRequest rentalRequest = modelMapper.map(rentalRequestDTO, RentalRequest.class);
        rentalRequest.setCreatedAt(LocalDateTime.now());
        rentalRequest.setStatus(StatusEnum.ACTIVE);
        rentalRequest.setState(RequestState.PENDING);

        RentalRequest savedRequest = rentalRequestRepository.save(rentalRequest);
        return modelMapper.map(savedRequest, RentalRequestDTO.class);
    }

    public List<RentalRequestDTO> getRequestsForCurrentTenant(Long tenantId) {
        User tenant = userRepository.findById(tenantId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        List<RentalRequest> requests = rentalRequestRepository.findByTenantWithPropertyAndTenant(tenant);

        return requests.stream()
                .map(request -> {
                    RentalRequestDTO dto = modelMapper.map(request, RentalRequestDTO.class);
                    dto.setTenantId(request.getTenant() != null ? request.getTenant().getId() : null);
                    dto.setPropertyId(request.getProperty() != null ? request.getProperty().getId() : null);
                    dto.setStartDate(request.getStartDate());
                    dto.setEndDate(request.getEndDate());
                    return dto;
                })
                .collect(Collectors.toList());
    }


    public List<RentalRequestDTO> getAllRentalRequests() {
        List<RentalRequest> requests = rentalRequestRepository.findAllWithPropertyAndTenant();

        return requests.stream()
                .map(request -> {
                    RentalRequestDTO dto = modelMapper.map(request, RentalRequestDTO.class);
                    dto.setTenantId(request.getTenant() != null ? request.getTenant().getId() : null);
                    dto.setPropertyId(request.getProperty() != null ? request.getProperty().getId() : null);
                    dto.setStartDate(request.getStartDate());
                    dto.setEndDate(request.getEndDate());
                    return dto;
                })
                .collect(Collectors.toList());
    }


    public RentalRequestDTO approveRentalRequest(Long rentalRequestId) {
        RentalRequest rentalRequest = rentalRequestRepository.findById(rentalRequestId)
                .orElseThrow(() -> new RuntimeException("Solicitud de arriendo no encontrada"));
    
        rentalRequest.setState(RequestState.APPROVED);
        RentalRequest updatedRequest = rentalRequestRepository.save(rentalRequest);
    
        return modelMapper.map(updatedRequest, RentalRequestDTO.class);
    }
    
    public RentalRequestDTO rejectRentalRequest(Long rentalRequestId) {
        RentalRequest rentalRequest = rentalRequestRepository.findById(rentalRequestId)
                .orElseThrow(() -> new RuntimeException("Solicitud de arriendo no encontrada"));
    
        rentalRequest.setState(RequestState.REJECTED);
        RentalRequest updatedRequest = rentalRequestRepository.save(rentalRequest);
    
        return modelMapper.map(updatedRequest, RentalRequestDTO.class);
    }

        public RentalRequestDTO cancelRentalRequest(Long rentalRequestId) {
        RentalRequest rentalRequest = rentalRequestRepository.findById(rentalRequestId)
                .orElseThrow(() -> new RuntimeException("Solicitud de arriendo no encontrada"));
    
        rentalRequest.setStatus(StatusEnum.DELETED);
        RentalRequest updatedRequest = rentalRequestRepository.save(rentalRequest);
    
        return modelMapper.map(updatedRequest, RentalRequestDTO.class);
    }
}
