
package com.example.TallerRestApiGym.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;




public class RutinaRequest {
    
    
    @NotBlank
    private String nombre;
    
    @NotBlank
    @Pattern(
            regexp = "BASICO|INTERMEDIO|AVANZADO",
            message = "El nivel debe ser BASICO, INTERMEDIO O AVANZADO"
    )
    
    private String nivel;
    
    public RutinaRequest(){
    }
    
    public RutinaRequest(String nombre, String nivel){
        
        this.nombre = nombre;
        this.nivel = nivel; 
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
    
    
    
}
