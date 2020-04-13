package com.trujillodev.api.services;

import java.util.List;

import org.bson.types.ObjectId;

import com.trujillodev.api.models.Employee;

public interface IEmployeeRepository {
	public List<Employee> findAll() throws Exception;
	public Employee findById(long id) throws Exception;
	public Employee insert(Employee employee) throws Exception;
	public Employee update(Employee employee) throws Exception;
	public boolean delete(ObjectId employee) throws Exception;
}
