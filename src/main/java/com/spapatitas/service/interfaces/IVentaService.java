package com.spapatitas.service.interfaces;

import com.spapatitas.persistence.model.Venta;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

public interface IVentaService {

    public List<Venta> findAllVenta();

    public Optional<Venta> findById(Long id);

    public void save(Venta venta);

    byte[] exportPdf() throws JRException, FileNotFoundException;

    byte[] exportFacturaPdf(Long id) throws JRException, FileNotFoundException;

}
