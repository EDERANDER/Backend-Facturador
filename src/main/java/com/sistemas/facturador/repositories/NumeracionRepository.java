package com.sistemas.facturador.repositories;

import com.sistemas.facturador.services.Numeracion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NumeracionRepository extends JpaRepository<Numeracion, Long> {
    Optional<Numeracion> findByTipoDocumentoAndSerie(String tipoDocumento, String serie);
}
