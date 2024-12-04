package com.spapatitas.util;

import com.spapatitas.persistence.model.Venta;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VentasReportGenerator {

    public byte[] exportToPdf(List<Venta> list) throws JRException, FileNotFoundException {
        return JasperExportManager.exportReportToPdf(getReport(list));
    }

    private JasperPrint getReport(List<Venta> list) throws FileNotFoundException, JRException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("venta", new JRBeanCollectionDataSource(list));

        JasperPrint report = JasperFillManager.fillReport(JasperCompileManager.compileReport(
                ResourceUtils.getFile("classpath:reporteMensual.jrxml")
                        .getAbsolutePath()), params, new JREmptyDataSource());

        return report;
    }

    public byte[] exportFacturaToPdf(Collection<Venta> ventaF) throws JRException, FileNotFoundException {
        return JasperExportManager.exportReportToPdf(getFacturaReport(ventaF));
    }

    private JasperPrint getFacturaReport(Collection<Venta> ventaF) throws FileNotFoundException, JRException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("venta", new JRBeanCollectionDataSource((Collection<?>) ventaF));

        JasperPrint report = JasperFillManager.fillReport(JasperCompileManager.compileReport(
                ResourceUtils.getFile("classpath:facturaVenta.jrxml")
                        .getAbsolutePath()), params, new JREmptyDataSource());

        return report;
    }
}

