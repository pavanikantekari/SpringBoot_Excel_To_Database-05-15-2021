package com.ojas.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ojas.app.dao.EmployeeDao;
import com.ojas.app.model.Employee;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDao repo;

	public List<Employee> saveAll(List<Employee> employees) {
		return repo.saveAll(employees);
	}
}