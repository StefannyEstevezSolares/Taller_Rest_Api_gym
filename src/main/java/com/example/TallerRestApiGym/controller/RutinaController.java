
package com.example.TallerRestApiGym.controller;

import com.example.TallerRestApiGym.dto.RutinaRequest;
import com.example.TallerRestApiGym.dto.RutinaResponse;
import com.example.TallerRestApiGym.service.RutinaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@Tag(
    name = "Rutinas",
    description = "Administración de las rutinas de entrenamiento del gimnasio"
)

@RestController
@RequestMapping("/api/rutinas")
public class RutinaController {
    
    
    private final RutinaService service;
    
    public RutinaController(RutinaService service){
    
        this.service = service;
    }
    
    
@Operation(
    summary = "Listar rutinas",
    description = "Obtiene todas las rutinas de entrenamiento registradas."
)
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Rutinas encontradas."),
    @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
})
    
    
@GetMapping
public ResponseEntity<List<RutinaResponse>>listar(){
    return ResponseEntity.ok(service.listar());
    
}



@Operation(
    summary = "Buscar rutina por ID",
    description = "Busca una rutina de entrenamiento utilizando su identificador."
)
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Rutina encontrada."),
    @ApiResponse(responseCode = "404", description = "No se encontró la rutina indicada."),
    @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
})




@GetMapping("/{id}")
public ResponseEntity<RutinaResponse> buscarPorId(@PathVariable Long id){

    return ResponseEntity.ok(service.buscarPorId(id));

}



@Operation(
    summary = "Registrar rutina",
    description = "Registra una nueva rutina de entrenamiento. El nombre debe ser único y el nivel debe ser BASICO, INTERMEDIO o AVANZADO."
)
@ApiResponses(value = {
    @ApiResponse(
        responseCode = "201",
        description = "Rutina registrada correctamente.",
        content = @Content(
            schema = @Schema(implementation = RutinaResponse.class)
        )
    ),
    @ApiResponse(responseCode = "400", description = "Datos inválidos."),
    @ApiResponse(responseCode = "409", description = "Ya existe una rutina con ese nombre."),
    @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
})


@PostMapping
public ResponseEntity<RutinaResponse>crear(
        @Valid @RequestBody RutinaRequest request){

    RutinaResponse creada = service.crear(request);
    
    return ResponseEntity
            .created(URI.create("/api/rutinas/" + creada.getId()))
            .body(creada);

}




@Operation(
    summary = "Actualizar rutina",
    description = "Actualiza el nombre y nivel de una rutina existente mediante su ID."
)
@ApiResponses(value = {
    @ApiResponse(
        responseCode = "200",
        description = "Rutina actualizada correctamente.",
        content = @Content(
            schema = @Schema(implementation = RutinaResponse.class)
        )
    ),
    @ApiResponse(responseCode = "400", description = "Datos inválidos."),
    @ApiResponse(responseCode = "404", description = "No se encontró la rutina indicada."),
    @ApiResponse(responseCode = "409", description = "Ya existe otra rutina con ese nombre."),
    @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
})

@PutMapping("/{id}")
public ResponseEntity<RutinaResponse>actualizar(
        @PathVariable Long id,
        @Valid @RequestBody RutinaRequest request){

    return ResponseEntity.ok(service.actualizar(id, request));

}


@Operation(
    summary = "Eliminar rutina",
    description = "Elimina una rutina de entrenamiento y sus relaciones con los clientes asociados."
)
@ApiResponses(value = {
    @ApiResponse(responseCode = "204", description = "Rutina eliminada correctamente."),
    @ApiResponse(responseCode = "404", description = "No se encontró la rutina indicada."),
    @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
})


@DeleteMapping("/{id}")
public ResponseEntity<Void> eliminar(@PathVariable Long id){

    service.eliminar(id);
    
    return ResponseEntity.noContent().build();

}
    
}
