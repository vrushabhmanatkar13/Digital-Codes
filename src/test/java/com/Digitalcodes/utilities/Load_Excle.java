package com.Digitalcodes.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

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
		int last_row = sheet.getPhysicalNumberOfRows();
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
				default:
					break;
                
				}
				 if (sheet.getRow(i).getCell(j).getCellType()== CellType.BLANK) {
					 break;
				 }
			
			}
			
			
		}
		
		return data;

	}

	public static String getString(String sheetname,int i, int j) {
		return workbook.getSheet(sheetname).getRow(i).getCell(1).getStringCellValue();

	}

	public static double readNumric(int i, int j) {
		return sheet.getRow(i).getCell(j).getNumericCellValue();
	}

	
	
	//it use when need to get specific cell value depending on row and colume name
	// read all row and verify data of particular cell
	public String getCellValue(String sheetname,int row, String columeName) {
		sheet=workbook.getSheet(sheetname);
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
		Object[][] data =new Object[1][last_clum];
		
		for (int j = 0; j <last_clum; j++) {

			CellType cell = sheet.getRow(row).getCell(j).getCellType();
			switch (cell) {
			case STRING:
				data[0][j] = sheet.getRow(row).getCell(j).getStringCellValue();
				break;
			case NUMERIC:
				data[0][j] = sheet.getRow(row).getCell(j).getNumericCellValue();
				break;
			default:
				break;
			}
			
		}
		return data;
	}
	 
	 public Object[][] getDataFromExcle(String sheet_name,int startrow, int endrow){
		 sheet = workbook.getSheet(sheet_name);
		int last_clum = sheet.getRow(0).getLastCellNum();
		int numberofrows=endrow-startrow;
		
		Object[][] data =new Object[numberofrows+1][last_clum];
		int size=0;
		
		for (int i=startrow ;i<=numberofrows+1 ;i++,size++) {
		for (int j = 0; j <last_clum; j++) {

			CellType cell = sheet.getRow(i).getCell(j).getCellType();
			switch (cell) {
			case STRING:
				data[size][j] = sheet.getRow(i).getCell(j).getStringCellValue();
				break;
			case NUMERIC:
				data[size][j] = sheet.getRow(i).getCell(j).getNumericCellValue();
				break;
			default:
				break;
			}
			
			
		}
	
			
		}
		
		return data;
	}
	
	 public static Map<String, String> getData(String sheet_name) throws Exception {
			sheet = workbook.getSheet(sheet_name);
			int last_row = sheet.getLastRowNum();
			int last_clum = sheet.getRow(0).getLastCellNum();
			
			Map<String, String> data=new HashMap<String, String>();
         
			for (int i = 0; i<last_row; i++) {
				
				for (int j = 0; j <last_clum; j++) {

					 CellType cell = sheet.getRow(i+1).getCell(j, MissingCellPolicy.RETURN_BLANK_AS_NULL).getCellType();
					switch (cell) {
					case STRING:
						 data.put(sheet.getRow(i+1).getCell(j).getStringCellValue(), sheet.getRow(i+1).getCell(j).getStringCellValue());
						break;
					case BLANK:
						
						break;
					default:
						throw new Exception("Data Not selected");
						
	                
					}
						
				
				}
				
				
			}
			
			return data;

		}
	 

	 
	
	
	
}
