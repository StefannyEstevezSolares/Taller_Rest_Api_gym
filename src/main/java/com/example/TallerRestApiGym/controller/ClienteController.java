
package com.example.TallerRestApiGym.controller;

import com.example.TallerRestApiGym.dto.ClienteRequest;
import com.example.TallerRestApiGym.dto.ClienteResponse;
import com.example.TallerRestApiGym.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;


import java.net.URI;
import java.util.List;

@Tag(
    name = "Clientes",
    description = "Administración de clientes del gimnasio"
)

@RestController
@RequestMapping("/api/clientes")

public class ClienteController {
    
    private final ClienteService service;
    
    public ClienteController(ClienteService service){
    
        this.service = service;
    
    }
    
    
@Operation(
    summary = "Listar clientes",
    description = "Obtiene la lista de todos los clientes registrados en el gimnasio."
)
@ApiResponses(value = {
    @ApiResponse(
        responseCode = "200",
        description = "Lista de clientes obtenida correctamente."
    ),
    @ApiResponse(
        responseCode = "500",
        description = "Error interno del servidor."
    )
})
    
    
    @GetMapping
    public ResponseEntity<List<ClienteResponse>>listar(){
        
        return ResponseEntity.ok(service.listar());
    }
    
   
@Operation(
    summary = "Buscar cliente por ID",
    description = "Busca un cliente registrado utilizando su identificador único."
) 

@ApiResponses(value = {
    @ApiResponse(
        responseCode = "200",
        description = "Cliente encontrado correctamente."
    ),
    @ApiResponse(
        responseCode = "404",
        description = "No se encontró un cliente con el ID proporcionado."
    ),
    @ApiResponse(
        responseCode = "500",
        description = "Error interno del servidor."
    )
})
    
    
    
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(service.buscarPorId(id));
    }
    
    
@Operation(
    summary = "Registrar cliente",
    description = "Registra un nuevo cliente en el gimnasio. El documento debe ser único."
)
@ApiResponses(value = {
    @ApiResponse(
        responseCode = "201",
        description = "Cliente registrado correctamente.",
        content = @Content(
            schema = @Schema(implementation = ClienteResponse.class)
        )
    ),
    @ApiResponse(
        responseCode = "400",
        description = "Los datos enviados no cumplen las validaciones."
    ),
    @ApiResponse(
        responseCode = "409",
        description = "Ya existe un cliente con ese documento."
    ),
    @ApiResponse(
        responseCode = "500",
        description = "Error interno del servidor."
    )})
    
    
    @PostMapping
    public ResponseEntity<ClienteResponse> crear(
            @Valid @RequestBody ClienteRequest request){
    
        ClienteResponse creado = service.crear(request);
        
        return ResponseEntity
                .created(URI.create("/api/clientes/" + creado.getId()))
                .body(creado);
    
    }
    
    
@Operation(
    summary = "Actualizar cliente",
    description = "Actualiza los datos de un cliente utilizando su ID."
)
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Cliente actualizado correctamente."),
    @ApiResponse(responseCode = "400", description = "Datos inválidos."),
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado."),
    @ApiResponse(responseCode = "409", description = "El documento ya pertenece a otro cliente.")
})


@PutMapping("/{id}")
public ResponseEntity<ClienteResponse> actualizar(
        @PathVariable Long id,
        @Valid @RequestBody ClienteRequest request) {

    return ResponseEntity.ok(service.actualizar(id, request));
}



@Operation(
    summary = "Desactivar cliente",
    description = "Desactiva un cliente mediante su ID. El registro permanece almacenado en la base de datos."
)
@ApiResponses(value = {
    @ApiResponse(
        responseCode = "204",
        description = "Cliente desactivado correctamente."
    ),
    @ApiResponse(
        responseCode = "404",
        description = "No se encontró el cliente indicado."
    ),
    @ApiResponse(
        responseCode = "500",
        description = "Error interno del servidor."
    )
})


    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        service.eliminar(id);
        
        return ResponseEntity.noContent().build();
        
    }
    
}
