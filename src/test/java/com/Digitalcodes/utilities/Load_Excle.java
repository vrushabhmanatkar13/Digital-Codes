package com.Digitalcodes.utilities;

import java.io.File;
import java.io.FileInputStream;


import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Load_Excle {
	private static XSSFSheet sheet;
	private static XSSFWorkbook workbook = null;
	public Load_Excle() {
		
		try {
			FileInputStream excle_file = new FileInputStream(new File(System.getProperty("user.dir")+"\\TestData\\DigitalCodes data.xlsx"));
			workbook = new XSSFWorkbook(excle_file);
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	
	
	//It is user when @Dataprovider tag used in any test cases
	public Object[][] getDataFromExcle(String sheet_name) {
		sheet = workbook.getSheet(sheet_name);
		int last_row = sheet.getLastRowNum();
		int last_clum = sheet.getRow(0).getLastCellNum();
		
		
		Object[][] data = new Object[last_row][last_clum];
		

		
		for (int i = 0; i<last_row; i++) {
			
			for (int j = 0; j <last_clum; j++) {

				CellType cell = sheet.getRow(i+1).getCell(j).getCellType();
				switch (cell) {
				case STRING:
					data[i][j] = sheet.getRow(i+1).getCell(j).getStringCellValue();
					break;
				case NUMERIC:
					data[i][j] = sheet.getRow(i+1).getCell(j).getNumericCellValue();
					break;
                
				}
					
			System.out.println(data[i][j]);
			System.out.println(" ");
			}
			
			
		}
		
		return data;

	}

	public static String readString(int i, int j) {
		return sheet.getRow(i).getCell(1).getStringCellValue();

	}

	public static double readNumric(int i, int j) {
		return sheet.getRow(i).getCell(j).getNumericCellValue();
	}

	
	
	//it use when need to get specific cell value depending on row and colume name
	// read all row and verify data of particular cell
	public String getCellValue(int row, String columeName) {
		String data = null;
		int index = 0;
		int cellnum = sheet.getRow(0).getLastCellNum();

		//System.out.println(cellnum);

		for (int j = 0; j < cellnum; j++) {

			XSSFCell cell = sheet.getRow(0).getCell(j);
			String cellval = cell.getStringCellValue();
					
		    // System.out.println(cell.getStringCellValue());

			if (columeName.equalsIgnoreCase(cellval)) {

				index = cell.getColumnIndex();

				// System.out.println(index);
			}

			// int lastcell=sheet.getRow(row).getLastCellNum();
		}

		data = sheet.getRow(row).getCell(index).getStringCellValue();



		return data;

	}
	
	
	
	 public Object[][] getDataFromExcle(String sheet_name,int row){
		 sheet = workbook.getSheet(sheet_name);
		int last_clum = sheet.getRow(0).getLastCellNum();
		Object[][] data =new Object[row][last_clum];
		
		for (int j = 0; j <last_clum; j++) {

			CellType cell = sheet.getRow(row).getCell(j).getCellType();
			switch (cell) {
			case STRING:
				data[row-1][j] = sheet.getRow(row).getCell(j).getStringCellValue();
				break;
			case NUMERIC:
				data[row-1][j] = sheet.getRow(row).getCell(j).getNumericCellValue();
				break;
			}
			
		}
		return data;
	}
	 
	 


	 
	
	
	
}
