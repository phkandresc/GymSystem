package controller;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import model.Membresia;
import model.Socio;
import model.TipoMembresia;


import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;



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
        // IO
        File htmlSource = new File("src/html/PlantillaFactura.html");
        File pdfDest = new File("output.pdf");
        // pdfHTML specific code
        ConverterProperties converterProperties = new ConverterProperties();
        HtmlConverter.convertToPdf(new FileInputStream(htmlSource),
                new FileOutputStream(pdfDest), converterProperties);
    }

    public static void main (String[]args) throws IOException {
        FacturaPDF facturaPDF = new FacturaPDF();
        facturaPDF.crearFacturaPDFFromHTML();

    }
}

