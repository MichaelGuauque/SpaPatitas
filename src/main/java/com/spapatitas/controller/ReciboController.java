package com.spapatitas.controller;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.spapatitas.service.implementation.ReciboPDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class ReciboController {

    @Autowired
    private ReciboPDFService reciboPDFService;

    @GetMapping("/descargar-recibo")
    public ResponseEntity<InputStreamResource> descargarRecibo(@RequestParam Long idVenta) {
        try {
            // Generar el PDF en un ByteArrayOutputStream
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            reciboPDFService.generarReciboPDF(idVenta, outputStream);

            // Convertir el stream en un InputStreamResource para enviarlo
            ByteArrayInputStream pdfStream = new ByteArrayInputStream(outputStream.toByteArray());

            // Configurar los encabezados para que el archivo se descargue
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=recibo.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(pdfStream));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}