
package com.example.TallerRestApiGym.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class ClienteRequest {
    
    @NotBlank
    private String nombre;
    
    @NotBlank
    private String documento;
    
    @NotNull
    private Boolean activo;
    
    public ClienteRequest(){
    
}
    
    public ClienteRequest(String nombre, String documento, Boolean activo){
        this.nombre = nombre;
        this.documento = documento;
        this.activo = activo;
        
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    
    
    
    
    
}
