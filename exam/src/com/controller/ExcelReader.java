package com.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public List<LoginBean> fetchExcelData(InputStream fileInputStream) {
		List<LoginBean> loginBeans = new ArrayList<LoginBean>();
		
	try {
		
	     
	    FileInputStream file = (FileInputStream) fileInputStream;
	     
	    //Get the workbook instance for XLS file 
	    XSSFWorkbook workbook = new XSSFWorkbook(file);
	 
	    //Get first sheet from the workbook
	    XSSFSheet sheet = workbook.getSheetAt(0);
	     
	    //Iterate through each rows from first sheet
	    Iterator<Row> rowIterator = sheet.iterator();
	    LoginBean loginBean;
	    while(rowIterator.hasNext()) {
	    	loginBean = new LoginBean();
	        Row row = rowIterator.next();
	         
	        //For each row, iterate through each columns
	        Iterator<Cell> cellIterator = row.cellIterator();
	        while(cellIterator.hasNext()) {
	             
	            Cell cell = cellIterator.next();
	            String cellVal = getValue(cell);
	             //System.out.println("---->>>>"+cellVal);
	             
	             switch(cell.getColumnIndex()) {
	             case 0:
	            	 loginBean.setName(cellVal);
	                 //System.out.print(cell.getBooleanCellValue()+":"+cell.getColumnIndex() + "\t\t");
	                 break;
	             case 1:
	            	 loginBean.setPassword(cellVal);
	            	 //System.out.print(cell.getNumericCellValue()+":"+cell.getColumnIndex() + "\t\t");
	                 break;
	         }
	           
	        }
	        //System.out.println("");
	        loginBeans.add(loginBean);
	    }
	    /*file.close();
	    FileOutputStream out = 
	        new FileOutputStream(new File("C:\\test.xls"));
	    workbook.write(out);
	    out.close();*/
	     
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException ex) {
	    ex.printStackTrace();
	}
	return loginBeans;
	}
	
	private String getValue(Cell cell) {
		String value = null;
		 switch(cell.getCellType()) {
         case Cell.CELL_TYPE_BOOLEAN:
        	 value = String.valueOf(cell.getBooleanCellValue());
             //System.out.print(cell.getBooleanCellValue()+":"+cell.getColumnIndex() + "\t\t");
             break;
         case Cell.CELL_TYPE_NUMERIC:
        	 value = String.valueOf(cell.getNumericCellValue());
        	 //System.out.print(cell.getNumericCellValue()+":"+cell.getColumnIndex() + "\t\t");
             break;
         case Cell.CELL_TYPE_STRING:
        	 value = cell.getStringCellValue();
             //System.out.print(cell.getStringCellValue()+":"+cell.getColumnIndex() + "\t\t");
             break;
     }
		 return value;
		
	}
}

