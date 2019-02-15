package net.secudev.daprojkt.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.secudev.daprojkt.model.role.Role;
import net.secudev.daprojkt.model.user.User;

public class ExportUtils {
	// itetxt pour pdf

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
