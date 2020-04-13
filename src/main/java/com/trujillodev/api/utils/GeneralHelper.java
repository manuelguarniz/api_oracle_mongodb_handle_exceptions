package com.trujillodev.api.utils;

import java.text.ParseException;

import com.trujillodev.api.models.Employee;
import com.trujillodev.api.models.mongodb.EmployeeObject;
import com.trujillodev.api.models.response.EmployeeResponse;

public class GeneralHelper {
	
	public static EmployeeResponse employeeToResponse(Employee employee) {
		return EmployeeResponse.builder()
				.key(String.valueOf(employee.get_id()))
				.id(employee.getId())
				.nombre(employee.getName())
				.fechaNacimiento(Utils.ConvertFullDateToString(employee.getBirthDate()))
				.diasTrabajados(employee.getWorkingDay())
				.salario(employee.getSalary())
				.build();
	}

	public static Employee employeeObjectToEmployee(EmployeeObject employee) throws ParseException {
		return Employee.builder()
				._id(employee.get_id())
				.id(employee.getId())
				.name(employee.getName())
				.birthDate(Utils.ConvertFullDateStringToDate(employee.getBirthDate()))
				.workingDay(employee.getWorkingDay())
				.salary(employee.getSalary())
				.build();
	}
}
