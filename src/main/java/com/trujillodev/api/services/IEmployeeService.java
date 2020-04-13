package com.trujillodev.api.services;

import java.util.List;

import org.bson.types.ObjectId;

import com.trujillodev.api.models.Employee;
import com.trujillodev.api.models.response.EmployeeResponse;

public interface IEmployeeService {
	public List<EmployeeResponse> findAll() throws Exception;
	public EmployeeResponse findById(Integer id) throws Exception;
	public EmployeeResponse insert(Employee employee) throws Exception;
	public EmployeeResponse update(Employee employee) throws Exception;
	public boolean delete(String key) throws Exception;
}
