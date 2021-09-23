/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reportes;

import java.io.IOException;

/**
 *
 * @author kevin
 */
public class Main {

    public static final String DATA = "D:\\Documentos\\NetBeansProjects\\Reportes\\bd_simple.txt";
    public static final String DATA2 = "D:\\Documentos\\NetBeansProjects\\Reportes\\base_datos.txt";
    public static final String LOGO = "D:\\Documentos\\NetBeansProjects\\Reportes\\logo_chn.png";
    public static final String DEST = "D:\\Documentos\\NetBeansProjects\\Reportes\\HIPOTECARIO_MIXTO.PDF";
    
    

    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        Reporte reporte = new Reporte();
        reporte.crearPdf(DEST, LOGO, DATA, DATA2);
    }

}
