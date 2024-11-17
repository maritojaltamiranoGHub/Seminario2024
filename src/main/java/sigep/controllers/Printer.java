package main.java.sigep.controllers;

import main.java.sigep.model.entities.Etiquetas;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.SimpleDoc;

/**
 *
 * @author Marito
 */
public class Printer {

    private PrintService printService;
  

    public enum LabelType {

        STANDS, FRACTIONAL,BARCODE_PRICE,DEPOSIT
    };

    public Printer(PrintService printService) {
        this.printService = printService; 
    }

    public void print(Etiquetas etiqueta, int cantidad, LabelType type) {
        String command = null;
        switch (type) {
            case DEPOSIT:
                command = depositos(etiqueta, cantidad);
                break;
            case FRACTIONAL:
                command = fraccionada(etiqueta, cantidad);
                break;
            case STANDS:
                command = gondolas(etiqueta, cantidad);
                break;
            case BARCODE_PRICE:
                command = codigoBarraPrecio(etiqueta, cantidad);
                break;
        }

        if (command != null) {
            byte[] data;
            data = command.getBytes();
            Doc doc = new SimpleDoc(data, DocFlavor.BYTE_ARRAY.AUTOSENSE, null);

            try {
                printService.createPrintJob().print(doc, null);
            } catch (PrintException ex) {
                System.out.println("Error! " + ex.getMessage());
            }
        }
    }

    

    private String gondolas(Etiquetas etiqueta, int cantidad) {
     
            
            return "OD\n"
                    + "N\n"
                    + "D10\n"
                    + "Q256,8\n"
                    + "q512\n"
                    + "I8,A,003\n"
                    + "A10,180,3,1,2,1,N,\"" + etiqueta.getCodigoBarra() + "\"\n"
                    + "A480,55,1,1,2,2,N,\"" + etiqueta.getCodigoInterno() + "\"\n"
                    + "A30,1,0,c,2,2,N,\"Siglo 21 - Seminario\"\n"
                    + "A120,50,0,d,2,1,N,\"- 2024 - \"\n"
                    + "A80,70,0,c,3,3,N,\"" + etiqueta.getPrecio() + "\"\n"
                    + "A80,160,0,a,2,1,N,\"" + "" + "\"\n"
                    + "A30,180,0,c,1,2,N,\"" + etiqueta.getDescripcion() + "\"\n"
                    + "P" + cantidad + "\n";
         
    }


    private String fraccionada(Etiquetas etiqueta, int cantidad) {
       //TODO
       return "";
    }

    private String depositos(Etiquetas etiqueta, int cantidad) {
        //TODO
        return "";
    }

    private String codigoBarraPrecio(Etiquetas etiqueta, int cantidad) {
        //TODO
        return "";
    }
}
