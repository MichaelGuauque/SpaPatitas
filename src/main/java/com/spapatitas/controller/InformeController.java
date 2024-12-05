package com.spapatitas.controller;

import com.spapatitas.service.implementation.VentaService;
import com.spapatitas.service.interfaces.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileNotFoundException;

@Controller
@RequestMapping("/informes")
public class InformeController {

    @Autowired
    IVentaService ventaService;

    @GetMapping()
    public String show(){
        return "informes/vistaInformes";
    }

    @GetMapping("/informeMensual")
    public ResponseEntity<byte[]> exportPdf() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("reporteMensual", "reporteMensual.pdf");
        return ResponseEntity.ok().headers(headers).body(ventaService.exportPdf());
    }
    @GetMapping("/facturaVenta/{id}")
    public ResponseEntity<byte[]> exportFacturaPdf(@PathVariable Long id) throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("facturaVenta", "facturaVenta.pdf");
        return ResponseEntity.ok().headers(headers).body(ventaService.exportFacturaPdf(id));
    }
}
