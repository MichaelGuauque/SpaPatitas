package com.spapatitas.controller;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.spapatitas.persistence.model.Venta;
import com.spapatitas.service.implementation.ReciboPDFService;
import com.spapatitas.service.interfaces.IVentaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

@RestController
public class ReciboController {

//    @Autowired
//    private ReciboPDFService reciboPDFService;
//
//    // Endpoint para generar el recibo en formato PDF
//    @GetMapping("/generar-recibo")
//    public ResponseEntity<InputStreamResource> generarRecibo(@RequestParam Long idVenta) {
//        try {
//            // Ruta temporal donde se guardará el archivo PDF (puedes cambiarla según sea necesario)
//            String rutaArchivo = "C:\\Users\\Jorge Humberto Marin\\Downloads\\recibo_venta_" + idVenta + ".pdf";
//
//            // Llamar al servicio para generar el recibo
//            reciboPDFService.generarReciboPDFUrl(idVenta, rutaArchivo);
//
//            // Crear un archivo de entrada para enviar el PDF como respuesta
//            File file = new File(rutaArchivo);
//            FileInputStream fis = new FileInputStream(file);
//
//            // Configurar las cabeceras de la respuesta para indicar que es un archivo PDF
//            HttpHeaders headers = new HttpHeaders();
//            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());
//            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE);
//
//            // Retornar el archivo como una respuesta para descarga
//            return new ResponseEntity<>(new InputStreamResource(fis), headers, HttpStatus.OK);
//        } catch (IOException e) {
//            e.printStackTrace();
//
//            // En caso de error, retornar un ResponseEntity con un mensaje de error como String
//            return ResponseEntity
//                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new InputStreamResource(new ByteArrayInputStream(("Error al generar el recibo: " + e.getMessage()).getBytes())));
//        }
//    }

}
