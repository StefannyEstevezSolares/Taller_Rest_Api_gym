
package com.example.TallerRestApiGym.repository;

import com.example.TallerRestApiGym.entity.Rutina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RutinaRepository extends JpaRepository<Rutina, Long> {
    
    
    Optional<Rutina> findByNombre(String nombre);
    
    boolean existsByNombre(String nombre);
    
}
