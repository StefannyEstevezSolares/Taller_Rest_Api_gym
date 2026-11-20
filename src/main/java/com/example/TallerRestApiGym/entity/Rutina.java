
package com.example.TallerRestApiGym.entity;


import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "rutinas")

public class Rutina {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true, length = 100)
    private String nombre;
    
    @Column(nullable = false, length = 20)
    private String nivel;
    
    @ManyToMany(mappedBy = "rutinas")
    private Set<Cliente> clientes = new HashSet<>();
    
    public Rutina(){
    }
    
    public Rutina(String nombre, String nivel){
        
        this.nombre = nombre;
        this.nivel = nivel;
        
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

    public Set<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(Set<Cliente> clientes) {
        this.clientes = clientes;
    }
    
    
}
