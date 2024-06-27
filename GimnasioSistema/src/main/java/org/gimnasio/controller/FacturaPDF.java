package org.gimnasio.controller;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.gimnasio.model.Membresia;
import org.gimnasio.model.Socio;
import org.gimnasio.model.TipoMembresia;
import org.jsoup.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class FacturaPDF {

    public void crearFacturaPDF(Socio socio, Membresia membresia, TipoMembresia tipoMembresia) {
        try {
            PdfWriter writer = new PdfWriter("Factura.pdf");
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("Factura de Membresia"));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Socio: " + socio.getNombre() + " " + socio.getApellido()));
            document.add(new Paragraph("Cedula: " + socio.getCedula()));
            document.add(new Paragraph("Email: " + socio.getEmail()));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Tipo de Membresia: " + tipoMembresia.getNombre()));
            document.add(new Paragraph("Descripcion: " + tipoMembresia.getDescripcion()));
            document.add(new Paragraph("Precio: " + tipoMembresia.getPrecio()));
            document.add(new Paragraph("Fecha de inicio: " + membresia.getFechaInicio()));
            document.add(new Paragraph("Fecha de fin: " + membresia.getFechaFin()));

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void crearFacturaPDFPrueba() {
        try {
            PdfWriter writer = new PdfWriter("Factura.pdf");
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("Factura de Membresia"));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Socio: Juan Perez"));
            document.add(new Paragraph("Cedula: 1723456789"));
            document.add(new Paragraph("Email:"));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Tipo de Membresia: Membresia Basica"));
            document.add(new Paragraph("Descripcion: Membresia basica con acceso a todas las areas del gimnasio"));
            document.add(new Paragraph("Precio: 30"));
            document.add(new Paragraph("Fecha de inicio: 2021-10-01"));
            document.add(new Paragraph("Fecha de fin: 2021-11-01"));

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void crearFacturaPDFFromHTML() throws IOException {
        // Ruta al archivo HTML y el destino del PDF
        String htmlPath = "src/html/PlantillaFactura.html";
        String pdfPath = "factura1.pdf";

        // Leer el archivo HTML
        try (FileInputStream htmlFile = new FileInputStream(htmlPath);
             FileOutputStream pdfFile = new FileOutputStream(pdfPath)) {

            // Cargar el HTML y realizar reemplazos si es necesario
            org.jsoup.nodes.Document htmlDoc = Jsoup.parse(htmlFile, "UTF-8", "");
            htmlDoc.select("p:contains({nombre_cliente})").forEach(p -> p.html(p.html().replace("{nombre_cliente}", "John Doe")));
            htmlDoc.select("p:contains({direccion_cliente})").forEach(p -> p.html(p.html().replace("{direccion_cliente}", "1234 Calle Falsa")));
            htmlDoc.select("p:contains({telefono_cliente})").forEach(p -> p.html(p.html().replace("{telefono_cliente}", "123-456-7890")));
            htmlDoc.select("p:contains({email_cliente})").forEach(p -> p.html(p.html().replace("{email_cliente}", "john.doe@example.com")));
            htmlDoc.select("p:contains({numero_factura})").forEach(p -> p.html(p.html().replace("{numero_factura}", "001")));
            htmlDoc.select("p:contains({fecha_factura})").forEach(p -> p.html(p.html().replace("{fecha_factura}", "2024-06-20")));
            htmlDoc.select("td:contains({descripcion_1})").forEach(td -> td.html(td.html().replace("{descripcion_1}", "Membresía Mensual")));
            htmlDoc.select("td:contains({cantidad_1})").forEach(td -> td.html(td.html().replace("{cantidad_1}", "1")));
            htmlDoc.select("td:contains({precio_unitario_1})").forEach(td -> td.html(td.html().replace("{precio_unitario_1}", "50.00")));
            htmlDoc.select("td:contains({total_1})").forEach(td -> td.html(td.html().replace("{total_1}", "50.00")));
            htmlDoc.select("td:contains({descripcion_2})").forEach(td -> td.html(td.html().replace("{descripcion_2}", "Clase de Yoga")));
            htmlDoc.select("td:contains({cantidad_2})").forEach(td -> td.html(td.html().replace("{cantidad_2}", "1")));
            htmlDoc.select("td:contains({precio_unitario_2})").forEach(td -> td.html(td.html().replace("{precio_unitario_2}", "20.00")));
            htmlDoc.select("td:contains({total_2})").forEach(td -> td.html(td.html().replace("{total_2}", "20.00")));
            htmlDoc.select("span:contains({subtotal})").forEach(span -> span.html(span.html().replace("{subtotal}", "70.00")));
            htmlDoc.select("span:contains({iva})").forEach(span -> span.html(span.html().replace("{iva}", "21")));
            htmlDoc.select("span:contains({monto_iva})").forEach(span -> span.html(span.html().replace("{monto_iva}", "14.70")));
            htmlDoc.select("span:contains({total})").forEach(span -> span.html(span.html().replace("{total}", "84.70")));

            // Configurar el conversor con propiedades
            ConverterProperties converterProperties = new ConverterProperties();

            // Crear el escritor de PDF y el documento PDF con márgenes personalizados
            PdfWriter writer = new PdfWriter(pdfFile);
            PdfDocument pdfDocument = new PdfDocument(writer);
            pdfDocument.setDefaultPageSize(PageSize.A4);

            // Crear el documento de iText
            Document document = new Document(pdfDocument, PageSize.A4);
            document.setMargins(0, 0, 0, 0);
            // Convertir el HTML a PDF
            HtmlConverter.convertToPdf(htmlDoc.html(), pdfDocument, converterProperties);

            System.out.println("PDF creado exitosamente!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main (String[]args) throws IOException {
        FacturaPDF facturaPDF = new FacturaPDF();
        facturaPDF.crearFacturaPDFFromHTML();

    }
}


