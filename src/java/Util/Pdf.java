/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
/**
 *
 * @author Nekena
 */
public class Pdf {
    public static  void creerpdf() throws FileNotFoundException, DocumentException, URISyntaxException, BadElementException, IOException{
    Document document = new Document();
    PdfWriter.getInstance(document, new FileOutputStream("iTextTable.pdf"));
    Path path = Paths.get(ClassLoader.getSystemResource("C:\\Users\\Nekena\\Desktop\\cvako2.png").toURI());
    Image img = Image.getInstance(path.toAbsolutePath().toString());
    document.add(img);
    document.open();

    PdfPTable table = new PdfPTable(3);
    String[] columnname={"essai","Age","Prenom"};
    addTableHeader(table,columnname);
    addRows(table,3);
    addRows(table,3);
    

    document.add(table);
    document.close();
    }
    public static  void addTableHeader(PdfPTable table,String[] tab) {
    Stream.of(tab).forEach(columnTitle -> {
        PdfPCell header = new PdfPCell();
        header.setBackgroundColor(BaseColor.ORANGE);
        header.setBorderWidth(2);
        header.setPhrase(Phrase.getInstance(columnTitle));
        table.addCell(header);
    });
    }
    public static  void addRows(PdfPTable table,int colonne) {
        
            for (int j = 0; j < colonne; j++) {
                table.addCell("row 1, col 3");
                
            }
            
            
        
    
    
    }   
    
}
