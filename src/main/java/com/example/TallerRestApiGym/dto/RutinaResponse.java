
package com.example.TallerRestApiGym.dto;



public class RutinaResponse {


private Long id;
private String nombre;
private String nivel;


public RutinaResponse(){


}


public RutinaResponse(Long id, String nombre, String nivel){

    this.id = id;
    this.nombre = nombre;
    this.nivel= nivel;
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

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
    


}
