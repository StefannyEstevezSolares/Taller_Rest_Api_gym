
package com.example.TallerRestApiGym.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clientes")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    
    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false, unique = true)
    private String documento;
    
    @Column(nullable = false)
    private boolean activo;
    
    
    @ManyToMany
    @JoinTable(
    
            name = "clientes_rutinas",
            joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "rutina_id")
    )
    
    private Set<Rutina> rutinas = new HashSet<>();
    
    public Cliente(){{
        }}
    
    
    public Cliente(String nombre, String documento, boolean activo){
    
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

   
    public Set<Rutina> getRutinas() {
    return rutinas;
}
    
    public void setRutinas(Set<Rutina> rutinas) {
    this.rutinas = rutinas;
}
    
    
    
}
