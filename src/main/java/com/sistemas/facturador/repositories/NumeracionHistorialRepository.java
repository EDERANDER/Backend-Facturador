package com.sistemas.facturador.repositories;


import com.sistemas.facturador.services.NumeracionHistorial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NumeracionHistorialRepository extends JpaRepository<NumeracionHistorial, Long> {

    NumeracionHistorial findTopByOrderByIdDesc();
}
