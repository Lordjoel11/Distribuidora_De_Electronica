package com.Districto_Tech.distribuidora.features.clients;
import com.Districto_Tech.distribuidora.common.IService;
import com.Districto_Tech.distribuidora.common.exceptions.ResourceNotFoundException;
import com.Districto_Tech.distribuidora.features.clients.dto.ClientRequestDTO;
import com.Districto_Tech.distribuidora.features.clients.dto.ClientResponseDTO;
import com.Districto_Tech.distribuidora.features.orders.OrderModelMapper;
import com.Districto_Tech.distribuidora.features.orders.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService implements IService<ClientRequestDTO, ClientResponseDTO, Long> {

    private final ClientRepository clientRepository;
    private final ClientModelMapper clientMapper;

    @Override
    public ClientResponseDTO save(ClientRequestDTO dto) {
        ClientEntity clientEntity = clientMapper.toEntity(dto);
        ClientEntity saved = clientRepository.save(clientEntity);
        return clientMapper.toDto(saved);
    }

    @Override
    public List<ClientResponseDTO> getAll() {
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClientResponseDTO getById(Long id) {
        ClientEntity userEntity = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id));

        return clientMapper.toDto(userEntity);
    }

    @Override
    public ClientResponseDTO update(Long id, ClientRequestDTO dto) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede actualizar. El CLiente no existe.");
        }
        ClientEntity updatedUser = clientRepository.save(clientMapper.toEntity(dto));
        updatedUser.setId(id);
        return clientMapper.toDto(updatedUser);
    }

    @Override
    public void deleteById(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar. El Cliente no existe.");
        }
        clientRepository.deleteById(id);
    }




}
