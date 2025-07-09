package com.sistemas.facturador.controllers;

import com.sistemas.facturador.models.InvoiceRequest;
import com.sistemas.facturador.services.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/facturador")
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @GetMapping("/devolver")
    public ResponseEntity<byte[]> descargarUltimoPdf() {
        try {
            byte[] pdf = pdfService.obtenerUltimoPdf();
            return ResponseEntity.ok()
                    .header("Content-Type", "application/pdf")
                    .header("Content-Disposition", "inline; filename=\"comprobante.pdf\"")
                    .body(pdf);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/devolverEspecifico")
    public ResponseEntity<byte[]> descargarEspecifico(@RequestBody InvoiceRequest request) {
        try {
            byte[] pdf = pdfService.obtenerPdfEspecifico(request);
            return ResponseEntity.ok()
                    .header("Content-Type", "application/pdf")
                    .header("Content-Disposition", "inline; filename=\"comprobante.pdf\"")
                    .body(pdf);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
