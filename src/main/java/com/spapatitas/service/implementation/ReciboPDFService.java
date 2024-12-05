package com.spapatitas.service.implementation;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.spapatitas.persistence.model.DetalleVenta;
import com.spapatitas.persistence.model.Producto;
import com.spapatitas.persistence.model.Venta;
import com.spapatitas.persistence.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;


@Service
public class ReciboPDFService {

    @Autowired
    private VentaRepository ventaRepository;

    public void generarReciboPDF(Long idVenta, OutputStream outputStream) throws IOException {
        Venta venta = ventaRepository.findById(idVenta).orElseThrow(() -> new IllegalArgumentException("Venta no encontrada"));

        // Crear documento PDF con OutputStream
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(outputStream));
        Document document = new Document(pdfDoc);

        // Configurar márgenes para evitar que el texto se sobreponga con la imagen
        document.setMargins(150, 36, 36, 36); // margen superior más grande (150) para dejar espacio suficiente para la imagen

        // Cargar y agregar logo de la empresa
        String logoPath = "src/main/resources/static/img/logoGris.png"; // Ruta de la imagen
        Image logo = new Image(ImageDataFactory.create(logoPath));

        // Escalar la imagen para ajustarla al tamaño adecuado (reduciendo su tamaño)
        logo.scaleToFit(150f, 150f); // Tamaño más pequeño de la imagen (150x150)

        // Calcular el centro de la página y colocar la imagen allí
        float pageWidth = pdfDoc.getDefaultPageSize().getWidth();
        float logoWidth = logo.getImageScaledWidth();
        logo.setFixedPosition((pageWidth - logoWidth) / 2, pdfDoc.getDefaultPageSize().getTop() - 180); // Ajusta la posición vertical

        // Añadir la imagen centrada
        document.add(logo);

        // Dibuja una línea horizontal en la parte superior (2px de grosor)
        PdfCanvas canvas = new PdfCanvas(pdfDoc.getFirstPage());
        float yPosition = pdfDoc.getDefaultPageSize().getTop() - 210; // Ajustar la posición vertical para la línea
        canvas.setLineWidth(2); // Grosor de la línea
        canvas.moveTo(36, yPosition); // Posición inicial de la línea (margen izquierdo, y de la línea)
        canvas.lineTo(pdfDoc.getDefaultPageSize().getWidth() - 36, yPosition); // Posición final de la línea (margen derecho, misma altura)
        canvas.stroke(); // Dibuja la línea

        // Añadir un espacio explícito después de la imagen para asegurar que el texto no se solape
        document.add(new Paragraph("\n").setFontSize(12));  // Añadimos salto de línea para crear espacio antes del texto

        // Título del recibo
        Paragraph title = new Paragraph("Recibo de Compra")
                .setFontSize(16)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER);
        document.add(title);

        // Agregar un espacio antes de la información de la empresa y el cliente
        document.add(new Paragraph("\n")); // Espacio adicional antes de la siguiente sección

        // Crear una tabla para la información de la empresa y la información del cliente
        Table infoTable = new Table(2);
        infoTable.setWidth(UnitValue.createPercentValue(100));

        // Columna de la empresa
        Cell empresaCell = new Cell().add(new Paragraph("Información de la Empresa")
                .setBold().setFontSize(12));
        empresaCell.setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
        infoTable.addCell(empresaCell);

        // Columna del cliente
        Cell clienteCell = new Cell().add(new Paragraph("Información del Cliente")
                .setBold().setFontSize(12));
        clienteCell.setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
        infoTable.addCell(clienteCell);

        // Agregar la información a la tabla
        // Información de la empresa en la primera columna
        String infoEmpresaCompleta = "Nombre de la Empresa: Spa Patitas\n" +
                "Dirección: Calle Ficticia 123\n" +
                "Teléfono: +123456789\n" +
                "Correo: contacto@spapatitas.com\n" +
                "Horario: Lunes a Viernes, 9:00 AM - 6:00 PM";
        infoTable.addCell(new Cell().add(new Paragraph(infoEmpresaCompleta)).setBorder(Border.NO_BORDER));

        // Información del cliente en la segunda columna
        String infoClienteCompleta = "ID Cliente: " + venta.getCliente().getIdCliente() + "\n" +
                "Cédula: " + venta.getCliente().getCedula() + "\n" +
                "Nombre: " + venta.getCliente().getPrimerNombre() + " " + venta.getCliente().getSegundoNombre() + "\n" +
                "Apellidos: " + venta.getCliente().getPrimerApellido() + " " + venta.getCliente().getSegundoApellido() + "\n" +
                "Teléfono: " + venta.getCliente().getTelefono();
        infoTable.addCell(new Cell().add(new Paragraph(infoClienteCompleta)).setBorder(Border.NO_BORDER));

        document.add(infoTable);

        // Añadir un salto de línea para separar la sección de cliente y detalles de la venta
        document.add(new Paragraph("\n"));

        // Información de la venta
        Paragraph ventaTitle = new Paragraph("Detalles de la Venta")
                .setBold()
                .setFontSize(12);
        document.add(ventaTitle);

        Paragraph infoVenta = new Paragraph("ID Venta: " + venta.getIdVenta() +
                "\nFecha de venta: " + venta.getFechaVenta() +
                "\nMétodo de pago: " + venta.getMetodoPago() +
                "\nValor sin IVA: " + venta.getValorSinIva() +
                "\nValor con IVA: " + venta.getValorIva())
                .setTextAlignment(TextAlignment.LEFT);
        document.add(infoVenta);

        // Añadir un salto de línea para separar la información de la venta y la tabla
        document.add(new Paragraph("\n"));

        // Tabla de detalles de la venta
        Table table = new Table(5); // Cinco columnas: Producto, Cantidad, Valor sin IVA, Valor con IVA, Total

        // Establecer el ancho de la tabla en porcentaje
        table.setWidth(UnitValue.createPercentValue(100));  // El 100% de la página
        table.setMarginTop(10f); // Espacio antes de la tabla
        table.setMarginBottom(10f); // Espacio después de la tabla

        // Agregar encabezados de tabla (en negrita)
        table.addCell(new Cell().add(new Paragraph("Producto").setBold()));
        table.addCell(new Cell().add(new Paragraph("Cantidad").setBold()));
        table.addCell(new Cell().add(new Paragraph("Valor sin IVA").setBold()));
        table.addCell(new Cell().add(new Paragraph("Valor con IVA").setBold()));
        table.addCell(new Cell().add(new Paragraph("Total").setBold()));

        // Agregar filas de la tabla con detalles de la venta
        List<DetalleVenta> detallesVenta = venta.getDetallesVenta();
        for (DetalleVenta detalle : detallesVenta) {
            Producto producto = detalle.getProducto();
            double totalProducto = (detalle.getValorSinIva() *detalle.getCantidad() ) + (detalle.getValorIva() *detalle.getCantidad());

            table.addCell(new Cell().add(new Paragraph(producto.getNombre())));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(detalle.getCantidad()))));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(detalle.getValorSinIva()*detalle.getCantidad()))));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(detalle.getValorIva()*detalle.getCantidad()))));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(totalProducto))));
        }

        document.add(table);

        // Agregar total general
        Paragraph total = new Paragraph("Total de la compra (sin impuestos): " + venta.getValorSinIva())
                .setTextAlignment(TextAlignment.RIGHT);
        document.add(total);

        Paragraph iva = new Paragraph("Valor de los impuestos: " + venta.getValorIva())
                .setTextAlignment(TextAlignment.RIGHT);
        document.add(iva);

        Paragraph totalConIva = new Paragraph("Total con impuestos: " + (venta.getTotal()))
                .setTextAlignment(TextAlignment.RIGHT);
        document.add(totalConIva);

        // Añadir un espacio entre el contenido y el mensaje final
        document.add(new Paragraph("\n"));

        // Mensaje de agradecimiento
        Paragraph agradecimiento = new Paragraph("Muchas gracias por su compra")
                .setBold()
                .setFontSize(14)
                .setTextAlignment(TextAlignment.CENTER);
        document.add(agradecimiento);

        // Cerrar el documento
        document.close();
    }

}