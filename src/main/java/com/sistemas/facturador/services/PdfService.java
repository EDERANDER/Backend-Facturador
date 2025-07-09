package com.sistemas.facturador.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistemas.facturador.models.InvoiceRequest;
import com.sistemas.facturador.models.NumeracionHistorial;
import com.sistemas.facturador.repositories.NumeracionHistorialRepository;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PdfService {

    @Autowired
    private NumeracionHistorialRepository historialRepository;

    @Value("${factiliza.token}")
    private String factilizaToken;

    public byte[] obtenerUltimoPdf() throws Exception {
        NumeracionHistorial ultimo = historialRepository.findTopByOrderByIdDesc();



        if (ultimo == null) {
            throw new RuntimeException("No hay registros en historial.");
        }

        Map<String, Object> body = new HashMap<>();
        body.put("empresa_Ruc", "20607086215"); // reemplaza si lo tienes en otra parte
        body.put("tipo_Doc", ultimo.getTipoDocumento());
        body.put("serie", ultimo.getSerie());
        body.put("correlativo", String.valueOf(Integer.parseInt(ultimo.getCorrelativo())));

        HttpResponse<byte[]> response = Unirest.post("https://apife-qa.factiliza.com/api/v1/invoice/pdf")
                .header("Authorization", "Bearer " + factilizaToken)
                .header("Content-Type", "application/json")
                .body(new ObjectMapper().writeValueAsString(body))
                .asBytes();

        if (response.getStatus() != 200) {
            throw new RuntimeException("Error al obtener PDF: " + response.getStatusText());
        }

        return response.getBody();
    }

    public byte[] obtenerPdfEspecifico(InvoiceRequest request) throws Exception {
        Map<String, Object> body = new HashMap<>();
        body.put("empresa_Ruc", "20607086215");
        body.put("tipo_Doc", request.getTipo_Doc());
        body.put("serie", request.getSerie());
        body.put("correlativo", request.getCorrelativo());

        HttpResponse<byte[]> response = Unirest.post("https://apife-qa.factiliza.com/api/v1/invoice/pdf")
                .header("Authorization", "Bearer " + factilizaToken)
                .header("Content-Type", "application/json")
                .body(new ObjectMapper().writeValueAsString(body))
                .asBytes();

        if (response.getStatus() != 200) {
            throw new RuntimeException("Error al obtener PDF: " + response.getStatusText());
        }

        return response.getBody();
    }

}
