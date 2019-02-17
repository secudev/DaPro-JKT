package net.secudev.daprojkt.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import net.secudev.daprojkt.model.role.Role;
import net.secudev.daprojkt.model.user.User;

public class ExportUtils {
	
	
	// itetxt pour pdf
	//https://itextpdf.com/en/resources/examples/itext-5
	public void testTpdf() throws DocumentException, URISyntaxException, MalformedURLException, IOException
	{
		Path path = Paths.get("files/sd.png");
		
		Document document = new Document();
		PdfWriter writer =PdfWriter.getInstance(document, new FileOutputStream("iTextHelloWorld.pdf"));
		
		
		
        
        
		document.open();
		Font font = FontFactory.getFont(FontFactory.COURIER, 32, BaseColor.BLACK);
		Chunk chunk = new Chunk("Hello Shadow Dancer", font);				
		document.add(chunk);	
		
		document.add(new Paragraph("Hello World"));
        document.add(new Paragraph("Hello People"));
		
        document.add( Chunk.NEWLINE );
        document.add( Chunk.NEWLINE );
        
		Image img = Image.getInstance(path.toAbsolutePath().toString());
		document.add(img);
		document.add( Chunk.NEWLINE );
		document.add( Chunk.NEWLINE );
		
		Font titleFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 11, BaseColor.BLACK);
        Paragraph docTitle = new Paragraph("NOTICE - Avertissement", titleFont);
        document.add(docTitle);
        Font subtitleFont = FontFactory.getFont("Times Roman", 9, BaseColor.BLACK);
        Paragraph subTitle = new Paragraph("(Ceci est un message important)", subtitleFont);
        document.add(subTitle);
        Font importantNoticeFont = FontFactory.getFont("Courier", 9, BaseColor.RED);
        Paragraph importantNotice = new Paragraph("Important: Vous devez contacter le service technique en cas de problemes", importantNoticeFont);
        document.add(importantNotice);
		PdfPTable table = new PdfPTable(10); // the arg is the number of columns
        PdfPCell cell = new PdfPCell(docTitle);
        cell.setColspan(3);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        PdfPCell cellCaveat = new PdfPCell(subTitle);
        cellCaveat.setColspan(2);
        cellCaveat.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cellCaveat);
        PdfPCell cellImportantNote = new PdfPCell(importantNotice);
        cellImportantNote.setColspan(5);
        cellImportantNote.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cellImportantNote);
        document.add(table);
		
		
		// add a couple of blank lines
        document.add( Chunk.NEWLINE );
        document.add( Chunk.NEWLINE );
		
        //Simple tableau
		 PdfPTable table2 = new PdfPTable(8);
	        for(int aw = 0; aw < 16; aw++){
	            table2.addCell("hi "+aw);
	        }
	        document.add(table2);
        
	        //Lignes
	        PdfContentByte canvas = writer.getDirectContent();
	        CMYKColor magentaColor = new CMYKColor(0.f, 1.f, 0.f, 0.f);
	        canvas.setColorStroke(magentaColor);
	        canvas.moveTo(36, 36);
	        canvas.lineTo(36, 806);
	        canvas.lineTo(559, 36);
	        canvas.lineTo(559, 806);
	        canvas.closePathStroke();
	        
        //Rectangle
		PdfContentByte canvas2 = writer.getDirectContent();
        Rectangle rect = new Rectangle(36, 50,60, 200);
        rect.setBackgroundColor(BaseColor.GRAY);
        rect.setBorder(Rectangle.BOX);
        rect.setBorderColor(BaseColor.RED);       
        rect.setBorderWidth(2);
        canvas2.rectangle(rect);
		
		document.close();
		
	}
	
	// Exemple d'export excel de la liste des utilisateurs
	public void usersToXcel(List<User> users, FileOutputStream outputStream) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Export Users");

		int rowNum = 0;

		for (User u : users) {
			Row row = sheet.createRow(rowNum++);
			Cell cell = row.createCell(0, CellType.STRING);
			cell.setCellValue(u.getName());
			String roles = "";
			for (Role role : u.getRoles()) {
				roles += role.getName() + " ";
			}
			Cell cell2 = row.createCell(1, CellType.STRING);
			cell2.setCellValue(roles);
		}
		
		workbook.write(outputStream);
		workbook.close();
	}

}
