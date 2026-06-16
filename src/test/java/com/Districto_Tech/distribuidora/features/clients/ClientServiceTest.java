package com.Districto_Tech.distribuidora.features.clients;

import com.Districto_Tech.distribuidora.common.exceptions.ResourceNotFoundException;
import com.Districto_Tech.distribuidora.features.clients.dto.ClientRequestDTO;
import com.Districto_Tech.distribuidora.features.clients.dto.ClientResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientModelMapper clientMapper;

    @InjectMocks
    private ClientService clientService;

    private ClientEntity clientEntity;
    private ClientRequestDTO requestDTO;
    private ClientResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        clientEntity = ClientEntity.builder()
                .id(1L)
                .nameAndSurname("Carlos Gómez")
                .DNI("12345678")
                .email("carlos@email.com")
                .phoneNumber("1122334455")
                .address("Av. Siempre Viva 123")
                .isVip(true)
                .build();

        requestDTO = ClientRequestDTO.builder()
                .nameAndSurname("Carlos Gómez")
                .DNI("12345678")
                .Email("carlos@email.com")
                .phoneNumber("1122334455")
                .address("Av. Siempre Viva 123")
                .isVip(true)
                .build();

        responseDTO = ClientResponseDTO.builder()
                .nameAndSurname("Carlos Gómez")
                .DNI("12345678")
                .email("carlos@email.com")
                .phoneNumber("1122334455")
                .address("Av. Siempre Viva 123")
                .isVip(true)
                .build();
    }

    @Test
    void save_ShouldCreateClientSuccessfully() {
        when(clientMapper.toEntity(any(ClientRequestDTO.class))).thenReturn(clientEntity);
        when(clientRepository.save(any(ClientEntity.class))).thenReturn(clientEntity);
        when(clientMapper.toDto(any(ClientEntity.class))).thenReturn(responseDTO);

        ClientResponseDTO result = clientService.save(requestDTO);

        assertNotNull(result);
        assertEquals("Carlos Gómez", result.getNameAndSurname());
        verify(clientRepository).save(any());
    }

    @Test
    void getAll_ShouldReturnAllClients() {
        when(clientRepository.findAll()).thenReturn(List.of(clientEntity));
        when(clientMapper.toDto(any(ClientEntity.class))).thenReturn(responseDTO);

        List<ClientResponseDTO> result = clientService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void getById_WhenClientExists_ShouldReturnClient() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(clientEntity));
        when(clientMapper.toDto(any(ClientEntity.class))).thenReturn(responseDTO);

        ClientResponseDTO result = clientService.getById(1L);

        assertNotNull(result);
        assertEquals("carlos@email.com", result.getEmail());
    }

    @Test
    void getById_WhenClientNotExists_ShouldThrowException() {
        when(clientRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> clientService.getById(99L));
    }

    @Test
    void update_ShouldUpdateClientSuccessfully() {
        when(clientRepository.existsById(1L)).thenReturn(true);
        when(clientMapper.toEntity(any(ClientRequestDTO.class))).thenReturn(clientEntity);
        when(clientRepository.save(any(ClientEntity.class))).thenReturn(clientEntity);
        when(clientMapper.toDto(any(ClientEntity.class))).thenReturn(responseDTO);

        ClientResponseDTO result = clientService.update(1L, requestDTO);

        assertNotNull(result);
        verify(clientRepository).save(any());
    }

    @Test
    void update_WhenClientNotExists_ShouldThrowException() {
        when(clientRepository.existsById(99L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> clientService.update(99L, requestDTO));
    }

    @Test
    void deleteById_WhenClientExists_ShouldDeleteSuccessfully() {
        when(clientRepository.existsById(1L)).thenReturn(true);
        doNothing().when(clientRepository).deleteById(1L);

        assertDoesNotThrow(() -> clientService.deleteById(1L));

        verify(clientRepository).deleteById(1L);
    }

    @Test
    void deleteById_WhenClientNotExists_ShouldThrowException() {
        when(clientRepository.existsById(99L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> clientService.deleteById(99L));
    }
}