package com.Digitalcodes.Util;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Load_Excle {
	private static XSSFSheet sheet;
	private static XSSFWorkbook workbook = null;
	public Load_Excle(String path, String sheet_name) {
		
		try {
			FileInputStream excle_file = new FileInputStream(new File(System.getProperty("user.dir") + path));
			workbook = new XSSFWorkbook(excle_file);
			sheet = workbook.getSheet(sheet_name);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public Object[][] Read_Excle(int row) {

		int last_row = sheet.getPhysicalNumberOfRows();
		int last_clum = sheet.getRow(0).getLastCellNum();
		System.out.println(last_row);
		System.out.println(last_clum);
		Object[][] data = new Object[last_row][last_clum];
		System.out.println(data.length);

		int ri = 0;
		for (int i = 1; i <= row; i++, ri++) {
			int cj = 0;
			for (int j = 0; j < last_clum; j++, cj++) {

				CellType cell = sheet.getRow(i).getCell(j).getCellType();
				switch (cell) {
				case STRING:
					data[ri][cj] = Read_String(i, j);
					break;
				case NUMERIC:
					data[ri][cj] = Read_Numric(i, j);
					break;
				default:
					break;

				}
				System.out.println(data[ri][cj]);
			}
		}

		return data;

	}

	public static String Read_String(int i, int j) {
		return sheet.getRow(i).getCell(1).getStringCellValue();

	}

	public static double Read_Numric(int i, int j) {
		return sheet.getRow(i).getCell(j).getNumericCellValue();
	}

	// read all row and verify data of particular cell

	public String Row(int row, String value) {
		String data = null;
		int index = 0;
		int cellnum = sheet.getRow(0).getLastCellNum();

		//System.out.println(cellnum);

		for (int j = 0; j < cellnum; j++) {

			XSSFCell cell = sheet.getRow(0).getCell(j);
			String cellval = cell.getStringCellValue();
					
		    // System.out.println(cell.getStringCellValue());

			if (value.equalsIgnoreCase(cellval)) {

				index = cell.getColumnIndex();

				// System.out.println(index);
			}

			// int lastcell=sheet.getRow(row).getLastCellNum();
		}

		data = sheet.getRow(row).getCell(index).getStringCellValue();

		//System.out.println(data);

		return data;

	}

	
	
	
}
