package com.sistemas.facturador.controllers;

import com.sistemas.facturador.models.InvoiceRequest;
import com.sistemas.facturador.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/facturador")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/send")
    public ResponseEntity<?> sendInvoice(@RequestBody InvoiceRequest invoiceRequest) {
        try {
            String respuestaApi = invoiceService.sendToFactiliza(invoiceRequest);  // recibe String
            return ResponseEntity.ok(respuestaApi); // lo envuelve correctamente en ResponseEntity
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al procesar la factura: " + e.getMessage());
        }
    }
}
