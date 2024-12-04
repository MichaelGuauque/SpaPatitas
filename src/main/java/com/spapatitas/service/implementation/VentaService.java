package com.spapatitas.service.implementation;


import com.spapatitas.persistence.model.Venta;
import com.spapatitas.persistence.repository.VentaRepository;
import com.spapatitas.service.interfaces.IVentaService;
import com.spapatitas.util.VentasReportGenerator;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class VentaService implements IVentaService {

    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private VentasReportGenerator ventasReportGenerator;

    @Override
    public List<Venta> findAllVenta() {
        return (List<Venta>) ventaRepository.findAll();
    }

    @Override
    public Optional<Venta> findById(Long id) {
        return ventaRepository.findById(id);
    }

    @Override
    public void save(Venta venta) {
        ventaRepository.save(venta);
    }

    @Override
    public byte[] exportPdf() throws JRException, FileNotFoundException {
        return ventasReportGenerator.exportToPdf((List<Venta>) ventaRepository.findAll());
    }
}