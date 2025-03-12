package com.emp.helper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.emp.entities.EmployeeInfo;

public class ExcelGenerator {

	 public static byte[] generateExcel(List<EmployeeInfo> employeeInfoList) throws IOException {
	        Workbook workbook = new XSSFWorkbook();
	        Sheet sheet = workbook.createSheet("Employee Data");

	        // Create header row
	        Row headerRow = sheet.createRow(0);
	        headerRow.createCell(0).setCellValue("Employee ID");
	        headerRow.createCell(1).setCellValue("Name");
	        headerRow.createCell(2).setCellValue("Date of Birth");
	        headerRow.createCell(3).setCellValue("Mobile Number");
	        headerRow.createCell(4).setCellValue("Availability");

	        // Fill data rows
	        int rowNum = 1;
	        for (EmployeeInfo employee : employeeInfoList) {
	            Row row = sheet.createRow(rowNum++);
	            row.createCell(0).setCellValue(employee.getEmpId());
	            row.createCell(1).setCellValue(employee.getName());
	            row.createCell(2).setCellValue(employee.getDob().toString());
	            row.createCell(3).setCellValue(employee.getMobNumber());
	            row.createCell(4).setCellValue(employee.getAvailable());
	        }

	        // Write to byte array
	        ByteArrayOutputStream bos = new ByteArrayOutputStream();
	        workbook.write(bos);
	        workbook.close();
	        return bos.toByteArray();
	}
}
