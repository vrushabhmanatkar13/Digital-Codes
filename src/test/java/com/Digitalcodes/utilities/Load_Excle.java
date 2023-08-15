package com.Digitalcodes.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.netty.handler.codec.http.DefaultLastHttpContent;

public class Load_Excle {
	private static XSSFSheet sheet;
	private static XSSFWorkbook workbook = null;

	public Load_Excle() {

		try {
			FileInputStream excle_file = new FileInputStream(
					new File(System.getProperty("user.dir") + "\\TestData\\DigitalCodes data.xlsx"));
			workbook = new XSSFWorkbook(excle_file);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// It is user when @Dataprovider tag used in any test cases
	public Object[][] getDataFromExcle(String sheet_name) {
		sheet = workbook.getSheet(sheet_name);
		int last_row = sheet.getLastRowNum();
		int last_clum = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[last_row][last_clum];

		for (int i = 0; i < last_row; i++) {

			for (int j = 0; j < last_clum; j++) {

				CellType cell = sheet.getRow(i + 1).getCell(j).getCellType();
				switch (cell) {
				case STRING:
					data[i][j] = sheet.getRow(i + 1).getCell(j).getStringCellValue();
					break;
				case NUMERIC:
					data[i][j] = sheet.getRow(i + 1).getCell(j).getNumericCellValue();
					break;
				default:
					break;

				}
				if (sheet.getRow(i).getCell(j).getCellType() == CellType.BLANK) {
					break;
				}

			}

		}

		return data;

	}

	public static String getString(String sheetname, int i, int j) {
		return workbook.getSheet(sheetname).getRow(i).getCell(1).getStringCellValue();

	}

	public static double readNumric(int i, int j) {
		return sheet.getRow(i).getCell(j).getNumericCellValue();
	}

	// it use when need to get specific cell value depending on row and colume name
	// read all row and verify data of particular cell
	public String getCellValue(String sheetname, int row, String columeName) {
		sheet = workbook.getSheet(sheetname);
		String data = null;
		int index = 0;
		int cellnum = sheet.getRow(0).getLastCellNum();

		// System.out.println(cellnum);

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

	public Object[][] getDataFromExcle(String sheet_name, int row) {
		sheet = workbook.getSheet(sheet_name);
		int last_clum = sheet.getRow(0).getLastCellNum();
		Object[][] data = new Object[1][last_clum];

		for (int j = 0; j < last_clum; j++) {

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

	public Object[][] getDataFromExcle(String sheet_name, int startrow, int endrow) {
		sheet = workbook.getSheet(sheet_name);
		int last_clum = sheet.getRow(0).getLastCellNum();
		int numberofrows = endrow - startrow;

		Object[][] data = new Object[numberofrows + 1][last_clum];
		int size = 0;

		for (int i = startrow; i <= numberofrows + 1; i++, size++) {
			for (int j = 0; j < last_clum; j++) {

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

		Map<String, String> data = new HashMap<String, String>();

		for (int i = 0; i < last_row; i++) {

			for (int j = 0; j < last_clum; j++) {

				CellType cell = sheet.getRow(i + 1).getCell(j, MissingCellPolicy.RETURN_BLANK_AS_NULL).getCellType();
				switch (cell) {
				case STRING:
					data.put(sheet.getRow(i + 1).getCell(j).getStringCellValue(),
							sheet.getRow(i + 1).getCell(j).getStringCellValue());
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

	/*
	 * To Get Total Number of Row in Test cases
	 * 
	 * @param XSSFSheet Name
	 * 
	 * @param Test Cases Name
	 * 
	 * @return total Number of row for test case
	 * 
	 * 
	 */
	public static int getNumberRow(XSSFSheet sheet, String testcase) {
		int row = 0;
		int lastrow = sheet.getLastRowNum();

		for (int i = 0; i < lastrow; i++) {
			String testcasename = sheet.getRow(i).getCell(0).getStringCellValue();
			if (testcasename.equalsIgnoreCase(testcase)) {

				for (int j = i + 2; j < lastrow; j++) { // To Iterate Row from next from header +2
					if (sheet.getRow(j).getCell(0).getStringCellValue().equalsIgnoreCase("***")) {
						break;
					} else {
						row++;
					}
				}
			}
		}
		return row;
	}

	/*
	 * To Get Index of test cases name present in sheet
	 * 
	 * @param XSSFSheet Name
	 * 
	 * @param Test Cases Name
	 * 
	 * @return index of test case name
	 * 
	 * 
	 */

	public static int getIndexTestcase(XSSFSheet sheet, String testcase) {
		int lastrow = sheet.getLastRowNum();
		int row = 0;
		for (int i = 0; i < lastrow; i++) {
			String testcasename = sheet.getRow(i).getCell(0).getStringCellValue();
			if (testcasename.equalsIgnoreCase(testcase)) {
				row = i;
				break;
			}
		}
		return row;
	}

	/*
	 * To get All data form sheet using data provider
	 * 
	 * @param XSSFSheet Name
	 * 
	 * @param Test Cases Name
	 * 
	 * @return Object[][]
	 * 
	 * @exception Exception if Test cases name not display in sheet
	 * 
	 */

	public Object[][] getDataFromExcle(String sheet_name, String testcase) throws Exception {

		sheet = workbook.getSheet(sheet_name);

		int lastrow = getNumberRow(sheet, testcase); // To get Number of rows for test case
		int testcaseindex = getIndexTestcase(sheet, testcase); // To get Index of Testcases Name
		int lastcell = sheet.getRow(testcaseindex + 1).getLastCellNum(); // To get Total no of Cell of Testcase

		Object[][] data = new Object[lastrow][lastcell];

		String testcasename = sheet.getRow(testcaseindex).getCell(0).getStringCellValue();

		if (testcasename.equalsIgnoreCase(testcase)) {

			int a = 2;
			for (int i = 0; i < lastrow; i++, a++) {

				for (int j = 0; j < lastcell; j++) {

					if (sheet.getRow(i).getCell(0).getStringCellValue().equalsIgnoreCase("***")) {

						break;
					}

					else {

						XSSFCell cell = sheet.getRow(testcaseindex + a).getCell(j);

						switch (cell.getCellType()) {
						case STRING:
							data[i][j] = cell.getStringCellValue(); // In ArrayList add crate object[] and add value
							break;
						case NUMERIC:
							data[i][j] = cell.getNumericCellValue();
							break;
						case FORMULA:
							data[i][j]=cell.getCellFormula();
						default:
							break;
						}
						

					}
				}

			}

			return data;
		} else {
			throw new Exception("Test Case Name Not add in sheet");
		}
	}

	/*
	 * To get a data as per row form sheet using data provider.
	 * 
	 * @param XSSFSheet Name
	 * 
	 * @param Test Cases Name
	 * 
	 * @param int Row
	 * 
	 * @return Object[][]
	 * 
	 * @exception Exception if Test cases name not display in sheet
	 * 
	 */

	public Object[][] getDataFromExcle(String sheet_name, String testcase, int row) throws Exception {

		sheet = workbook.getSheet(sheet_name);

		int testcaseindex = getIndexTestcase(sheet, testcase); // To get Index of Testcases Name
		int lastcell = sheet.getRow(testcaseindex + 1).getLastCellNum(); // To get Total no of Cell of Testcase

		Object[][] data = new Object[1][lastcell];

		String testcasename = sheet.getRow(testcaseindex).getCell(0).getStringCellValue();

		if (testcasename.equalsIgnoreCase(testcase)) {

			int i = 0;
			for (int j = 0; j < lastcell; j++) {

				if (sheet.getRow(i).getCell(0).getStringCellValue().equalsIgnoreCase("***")) {

					break;
				}

				else {

					XSSFCell cell = sheet.getRow(testcaseindex + 2 + row - 1).getCell(j);
					switch (cell.getCellType()) {
					case STRING:
						data[i][j] = cell.getStringCellValue(); // In ArrayList add crate object[] and add value
						break;
					case NUMERIC:
						data[i][j] = cell.getNumericCellValue();
						break;
					default:
						break;
					}
            
				}
			}

			return data;
		} else {
			throw new Exception("Test Case Name Not add in sheet");
		}
	}
	
	
	
	

	

}
