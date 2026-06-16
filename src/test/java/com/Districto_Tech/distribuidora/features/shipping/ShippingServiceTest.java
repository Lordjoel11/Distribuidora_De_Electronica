package com.Districto_Tech.distribuidora.features.shipping;

import com.Districto_Tech.distribuidora.common.exceptions.ShippingAlreadyExistsException;
import com.Districto_Tech.distribuidora.common.exceptions.ShippingNotFoundException;
import com.Districto_Tech.distribuidora.features.shipping.dtos.ShippingRequestDTO;
import com.Districto_Tech.distribuidora.features.shipping.dtos.ShippingResponseDTO;
import com.Districto_Tech.distribuidora.features.shipping.misc.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShippingServiceTest {

    @Mock
    private ShippingRepository shippingRepository;

    @Mock
    private ShippingModelMapper shippingModelMapper;

    @InjectMocks
    private ShippingService shippingService;

    private ShippingEntity shippingEntity;
    private ShippingRequestDTO requestDTO;
    private ShippingResponseDTO responseDTO;
    private UUID shippingUuid;

    @BeforeEach
    void setUp() {
        shippingUuid = UUID.randomUUID();

        shippingEntity = ShippingEntity.builder()
                .shippingId(1)
                .uuid(shippingUuid)
                .shippingSentDate(LocalDate.now())
                .shippingDeliveredDate(LocalDate.now().plusDays(3))
                .state(State.PREPARING)
                .build();

        requestDTO = ShippingRequestDTO.builder()
                .uuid(shippingUuid)
                .shippingSentDate(LocalDate.now())
                .shippingDeliveredDate(LocalDate.now().plusDays(3))
                .build();

        responseDTO = ShippingResponseDTO.builder()
                .state(State.PREPARING)
                .shippingSentDate(LocalDate.now())
                .shippingDeliveredDate(LocalDate.now().plusDays(3))
                .build();
    }

    @Test
    void save_ShouldCreateShippingSuccessfully() {
        when(shippingModelMapper.toEntity(any(ShippingRequestDTO.class))).thenReturn(shippingEntity);
        when(shippingRepository.existsById(any(UUID.class))).thenReturn(false);
        when(shippingRepository.save(any(ShippingEntity.class))).thenReturn(shippingEntity);
        when(shippingModelMapper.toDto(any(ShippingEntity.class))).thenReturn(responseDTO);

        ShippingResponseDTO result = shippingService.save(requestDTO);

        assertNotNull(result);
        assertEquals(State.PREPARING, result.getState());
        verify(shippingRepository).save(any());
    }

    @Test
    void save_WhenShippingAlreadyExists_ShouldThrowException() {
        when(shippingModelMapper.toEntity(any())).thenReturn(shippingEntity);
        when(shippingRepository.existsById(any(UUID.class))).thenReturn(true);

        assertThrows(ShippingAlreadyExistsException.class, () -> shippingService.save(requestDTO));
    }

    @Test
    void getAll_ShouldReturnAllShipments() {
        when(shippingRepository.findAll()).thenReturn(List.of(shippingEntity));
        when(shippingModelMapper.toDto(any(ShippingEntity.class))).thenReturn(responseDTO);

        List<ShippingResponseDTO> result = shippingService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void getById_WhenShippingExists_ShouldReturnShipping() {
        when(shippingRepository.findById(shippingUuid)).thenReturn(Optional.of(shippingEntity));
        when(shippingModelMapper.toDto(any(ShippingEntity.class))).thenReturn(responseDTO);

        ShippingResponseDTO result = shippingService.getById(shippingUuid);

        assertNotNull(result);
    }

    @Test
    void getById_WhenShippingNotExists_ShouldThrowException() {
        when(shippingRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(ShippingNotFoundException.class, () -> shippingService.getById(shippingUuid));
    }

    @Test
    void deleteById_WhenShippingExists_ShouldDeleteSuccessfully() {
        when(shippingRepository.findById(shippingUuid)).thenReturn(Optional.of(shippingEntity));
        doNothing().when(shippingRepository).delete(any(ShippingEntity.class));

        assertDoesNotThrow(() -> shippingService.deleteById(shippingUuid));

        verify(shippingRepository).delete(any());
    }
}