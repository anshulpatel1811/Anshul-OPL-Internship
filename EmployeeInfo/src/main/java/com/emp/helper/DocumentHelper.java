package com.emp.helper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.emp.entities.EmployeeInfo;

public class DocumentHelper {

	public static List<EmployeeInfo> getListOfObjectFromXL(InputStream is){
		
		List<EmployeeInfo> empList = new ArrayList<>();
		
		try {
			
			//Create an Excel workbook using InputStream
			Workbook workbook = new XSSFWorkbook(is);

			//Create/Get Sheet from workbook
			Sheet sheet = workbook.getSheet("emp_data");
			
			//Get Row Iterator
			Iterator<Row> rows = sheet.iterator();
			
			int rowCount=0;
			while (rows.hasNext()) {
				
				Row row = rows.next();
				if (rowCount==0) {
					rowCount++;
					continue;
				}
				//Get Cell Iterator
				EmployeeInfo emp = new EmployeeInfo();
				Iterator<Cell> cells = row.iterator();
				int cellCount=1;
				while (cells.hasNext()) {
					Cell cell = cells.next();
					switch (cellCount) {
					case 1:
						emp.setEmpId(NumberToTextConverter.toText(cell.getNumericCellValue()));
						break;
					case 2:
						emp.setName(cell.getStringCellValue());
						break;
					case 3:
						//emp.setDob(NumberToTextConverter.toText(cell.getNumericCellValue()));
                        if (cell.getCellType() == CellType.NUMERIC && org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                            Date dob = cell.getDateCellValue();
                            System.out.println(dob);
                            emp.setDob(dob);
                        } else {
                            emp.setDob(null); // Handle if the date is not valid or missing
                        }

						break;
					case 4:
						emp.setMobNumber(NumberToTextConverter.toText(cell.getNumericCellValue()));
						break;
					case 5:
						emp.setAvailable(cell.getBooleanCellValue());
						break;
						
					default:
						break;
					}
					cellCount++;
				}
				empList.add(emp);
			}
			
			workbook.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
		
		return empList;
	}
}
