package net.secudev.daprojkt.utils;

import java.io.FileNotFoundException;
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
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import net.secudev.daprojkt.model.role.Role;
import net.secudev.daprojkt.model.user.User;

public class ExportUtils {
	// itetxt pour pdf

	//Export PDF d'une annonce
	public void advertisementToExcel() throws DocumentException, URISyntaxException, MalformedURLException, IOException
	{
		Path path = Paths.get("files/sd.png");
		
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("iTextHelloWorld.pdf"));
		 
		document.open();
		Font font = FontFactory.getFont(FontFactory.COURIER, 32, BaseColor.BLACK);
		Chunk chunk = new Chunk("Hello Shadow Dancer", font);		 
		document.add(chunk);
		
		Image img = Image.getInstance(path.toAbsolutePath().toString());
		document.add(img);
		
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
