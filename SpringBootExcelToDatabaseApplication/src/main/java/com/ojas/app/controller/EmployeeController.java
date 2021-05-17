package com.ojas.app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ojas.app.model.Employee;
import com.ojas.app.service.EmployeeService;

@RestController

public class EmployeeController {

	@Autowired
	private EmployeeService service;
    @PostMapping("/excel")
	public ResponseEntity<List<Employee>> saveAll(@RequestParam("file") MultipartFile file) throws IOException {

		List<Employee> empList = new ArrayList<>();

		XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
		XSSFSheet worksheet = workbook.getSheetAt(0);

		for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
			if (index > 0) {
				Employee emp = new Employee();

				XSSFRow row = worksheet.getRow(index);

				emp.setEname(row.getCell(0).getStringCellValue());
				emp.setEaddr(row.getCell(1).getStringCellValue());

				empList.add(emp);

			}
		}
		return new ResponseEntity<>(service.saveAll(empList), HttpStatus.OK);

	}

}
