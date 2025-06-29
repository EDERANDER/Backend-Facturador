package com.sistemas.facturador.services;
import com.sistemas.facturador.models.InvoiceRequest;
import com.sistemas.facturador.models.NumeracionHistorial;
import com.sistemas.facturador.models.NumeracionComprobante;
import com.sistemas.facturador.repositories.NumeracionHistorialRepository;
import com.sistemas.facturador.repositories.NumeracionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class NumeracionService {

    @Autowired
    private NumeracionRepository numeracionRepository;

    @Autowired
    private NumeracionHistorialRepository historialRepository;
    
    @Transactional
    public NumeracionComprobante obtenerNumeracion(String tipoDoc, InvoiceRequest invoiceRequest) {
        String prefijo = tipoDoc.equals("01") ? "F" : tipoDoc.equals("03") ? "B" : null;
        if (prefijo == null) {
            throw new IllegalArgumentException("Tipo de documento no soportado: " + tipoDoc);
        }

        String serieActual = prefijo + "202";

        Numeracion numeracion = numeracionRepository
                .findByTipoDocumentoAndSerie(tipoDoc, serieActual)
                .orElseGet(() -> {
                    Numeracion nueva = new Numeracion();
                    nueva.setTipoDocumento(tipoDoc);
                    nueva.setSerie(serieActual);
                    nueva.setCorrelativo(1);
                    return nueva;
                });

        String serieGenerada = numeracion.getSerie();
        String correlativoGenerado = String.format("%08d", numeracion.getCorrelativo());

        NumeracionComprobante result = new NumeracionComprobante();
        result.setSerie(serieGenerada);
        result.setCorrelativo(correlativoGenerado);

        NumeracionHistorial historial = new NumeracionHistorial();
        historial.setTipoDocumento(tipoDoc);
        historial.setSerie(serieGenerada);
        historial.setCorrelativo(correlativoGenerado);
        historial.setFechaRegistro(LocalDateTime.now());
        historial.setRucCliente(invoiceRequest.getCliente_Num_Doc());
        historial.setRazonSocial(invoiceRequest.getCliente_Razon_Social());
        historial.setDireccion(invoiceRequest.getCliente_Direccion());
        historial.setSubtotal(BigDecimal.valueOf(invoiceRequest.getSub_Total()));
        historial.setIgv(BigDecimal.valueOf(invoiceRequest.getMonto_Igv()));
        historial.setTotal(BigDecimal.valueOf(invoiceRequest.getMonto_Imp_Venta()));
        historial.setValorVenta(BigDecimal.valueOf(invoiceRequest.getValor_Venta()));
        historial.setTipoMoneda(invoiceRequest.getTipo_Moneda());
        historial.setFechaEmision(invoiceRequest.getFecha_Emision());

        historialRepository.save(historial);

        // Actualiza para el siguiente
        int nuevoCorrelativo = numeracion.getCorrelativo() + 1;
        if (nuevoCorrelativo > 99999999) {
            int numSerie = Integer.parseInt(numeracion.getSerie().substring(1)) + 1;
            String nuevaSerie = prefijo + String.format("%03d", numSerie);
            numeracion.setSerie(nuevaSerie);
            numeracion.setCorrelativo(1);
        } else {
            numeracion.setCorrelativo(nuevoCorrelativo);
        }

        numeracionRepository.save(numeracion);

        return result;
    }
}
