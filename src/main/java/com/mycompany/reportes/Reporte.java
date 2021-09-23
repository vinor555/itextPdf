/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reportes;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 *
 * @author kevin
 */
public class Reporte {

    public void crearPdf(String dest, String logo_chn, String data, String data2) throws IOException {
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        //Document document = new Document(pdf, PageSize.A4.rotate()); para hacerla horizontal
        Document document = new Document(pdf, PageSize.A4.rotate());
        document.setMargins(20, 20, 20, 20);//set margenes espacio

        Image logo = new Image(ImageDataFactory.create(logo_chn));
        //logo.setAbsolutePosition(0, 0);
        logo.scaleToFit(100, 100);

        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ITALIC);
        PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);

        Table table = new Table(new float[]{2, 4, 4});//dividimos las columnas con los ultimos numeros
        table.setWidth(UnitValue.createPercentValue(100));//le estamos diciendo que ocupe el 100% del ancho de la pagina respetando margenes

        Table table2 = new Table(new float[]{4, 1, 3, 4, 3, 3, 10, 10, 1});//dividimos las columnas con los ultimos numeros
        table2.setWidth(UnitValue.createPercentValue(100));//le estamos diciendo que ocupe el 100% del ancho de la pagina respetando margenes

        BufferedReader br = new BufferedReader(new FileReader(data));
        String line = br.readLine();
        process(table, line, bold, true);

        while ((line = br.readLine()) != null) {
            process(table, line, font, false);
        }

        br.close();
        document.add(table);
        
        br = new BufferedReader(new FileReader(data2));
        line = br.readLine();
        process2(table2, line, bold, true);

        while ((line = br.readLine()) != null) {
            process2(table2, line, font, false);
        }

        br.close();
        document.add(table2);

        document.add(new Paragraph("Lista BANCO CHN TESTING PDF").setFont(font));
        List list = new List().setSymbolIndent(12).setListSymbol("\u2022").setFont(font);
        list.add(new ListItem("Texto 1"))
                .add(new ListItem("Texto 2"))
                .add(new ListItem("Texto 3"))
                .add(new ListItem("Texto 4"));
        document.add(list);

        Paragraph p = new Paragraph("Logo CHN")
                .add(logo)
                .add("Es el logo del CHN")
                .add("Es otro espacio ejemplo")
                .add(logo);

        Paragraph p1 = new Paragraph("Logo CHN2")
                .add(logo)
                .add("Es el logo del CHN2")
                .add("Es otro espacio ejemplo2")
                .add(logo);
        document.add(p);
        document.add(p1);

        document.close();
    }

    public void process(Table table, String line, PdfFont font, boolean isHeader) {
        StringTokenizer tokenizer = new StringTokenizer(line, ",");
        while (tokenizer.hasMoreTokens()) {
            if (isHeader) {
                table.addHeaderCell(new Cell().add(new Paragraph(tokenizer.nextToken()).setFont(font)));
            } else {
                table.addCell(new Cell().add(new Paragraph(tokenizer.nextToken()).setFont(font)));
            }
        }
    }
    
    public void process2(Table table, String line, PdfFont font, boolean isHeader) {
        StringTokenizer tokenizer = new StringTokenizer(line, ";");
        while (tokenizer.hasMoreTokens()) {
            if (isHeader) {
                table.addHeaderCell(new Cell().add(new Paragraph(tokenizer.nextToken()).setFont(font)));
            } else {
                table.addCell(new Cell().add(new Paragraph(tokenizer.nextToken()).setFont(font)));
            }
        }
    }

}
