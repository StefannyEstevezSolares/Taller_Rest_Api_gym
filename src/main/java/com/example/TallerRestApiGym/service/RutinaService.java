
package com.example.TallerRestApiGym.service;

import com.example.TallerRestApiGym.dto.RutinaRequest;
import com.example.TallerRestApiGym.dto.RutinaResponse;
import com.example.TallerRestApiGym.entity.Cliente;
import com.example.TallerRestApiGym.entity.Rutina;
import com.example.TallerRestApiGym.repository.ClienteRepository;
import com.example.TallerRestApiGym.repository.RutinaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RutinaService {

    private final RutinaRepository repository;
    private final ClienteRepository clienteRepository;

    
    
    public RutinaService(
            RutinaRepository repository,
            ClienteRepository clienteRepository) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
    }


    @Transactional(readOnly = true)
    public List<RutinaResponse> listar() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }


    @Transactional(readOnly = true)
    public RutinaResponse buscarPorId(Long id) {
        Rutina rutina = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rutina no encontrada"));

        return toResponse(rutina);
    }


    public RutinaResponse crear(RutinaRequest request) {


        if (repository.existsByNombre(request.getNombre())) {
            throw new RuntimeException("Ya existe una rutina con ese nombre");
        }


        Rutina nuevaRutina = new Rutina(
                request.getNombre(),
                request.getNivel()
        );


        Rutina rutinaGuardada = repository.save(nuevaRutina);

        return toResponse(rutinaGuardada);
    }


    public RutinaResponse actualizar(Long id, RutinaRequest request) {


        Rutina rutina = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rutina no encontrada"));


        var rutinaConNombre = repository.findByNombre(request.getNombre());

        if (rutinaConNombre.isPresent()
                && !rutinaConNombre.get().getId().equals(id)) {
            throw new RuntimeException("Ya existe otra rutina con ese nombre");
        }


        rutina.setNombre(request.getNombre());
        rutina.setNivel(request.getNivel());


        Rutina rutinaActualizada = repository.save(rutina);

        return toResponse(rutinaActualizada);
    }


    public void eliminar(Long id) {

        Rutina rutina = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rutina no encontrada"));

      
        
        
      for (Cliente cliente : rutina.getClientes()) {
    cliente.getRutinas().remove(rutina);
    clienteRepository.save(cliente);
}


        repository.delete(rutina);
    }


    private RutinaResponse toResponse(Rutina rutina) {
        return new RutinaResponse(
                rutina.getId(),
                rutina.getNombre(),
                rutina.getNivel()
        );
    }
}
