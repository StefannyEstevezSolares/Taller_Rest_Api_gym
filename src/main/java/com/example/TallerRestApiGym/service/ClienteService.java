
package com.example.TallerRestApiGym.service;

import com.example.TallerRestApiGym.dto.ClienteRequest;
import com.example.TallerRestApiGym.dto.ClienteResponse;
import com.example.TallerRestApiGym.entity.Cliente;
import com.example.TallerRestApiGym.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service


@Transactional
public class ClienteService {


    private final ClienteRepository repository;


    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }


    @Transactional(readOnly = true)
    public List<ClienteResponse> listar() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }


    @Transactional(readOnly = true)
    public ClienteResponse buscarPorId(Long id) {


        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        return toResponse(cliente);
    }


    public ClienteResponse crear(ClienteRequest request) {


        if (repository.existsByDocumento(request.getDocumento())) {
            throw new RuntimeException("Ya existe un cliente con ese documento");
        }


        Cliente nuevoCliente = new Cliente(
                request.getNombre(),
                request.getDocumento(),
                request.getActivo()
        );


        Cliente clienteGuardado = repository.save(nuevoCliente);


        return toResponse(clienteGuardado);
    }


    public ClienteResponse actualizar(Long id, ClienteRequest request) {


        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));


        var clienteConDocumento = repository.findByDocumento(request.getDocumento());

        if (clienteConDocumento.isPresent()
                && !clienteConDocumento.get().getId().equals(id)) {
            throw new RuntimeException("El documento ya pertenece a otro cliente");
        }


        cliente.setNombre(request.getNombre());
        cliente.setDocumento(request.getDocumento());
        cliente.setActivo(request.getActivo());


        Cliente clienteActualizado = repository.save(cliente);

        return toResponse(clienteActualizado);
    }


    public void eliminar(Long id) {

        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));


        cliente.setActivo(false);

        repository.save(cliente);
    }

    
    private ClienteResponse toResponse(Cliente cliente) {
        return new ClienteResponse(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getDocumento(),
                cliente.isActivo()
        );
    }
}
