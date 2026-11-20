
package com.example.TallerRestApiGym.repository;

import com.example.TallerRestApiGym.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByDocumento(String documento);


    boolean existsByDocumento(String documento);

}


