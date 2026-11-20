
package com.example.TallerRestApiGym.dto;




public class ClienteResponse {


private Long id;
private String nombre;
private String documento;
private boolean activo;


public ClienteResponse(){
}

public ClienteResponse(Long id, String nombre, String documento, boolean activo){
    
    this.id = id;
    this.nombre = nombre;
    this.documento = documento;
    this.activo = activo;
}

    public Long getId() {
        return id;
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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
 



}
