package com.trujillodev.api.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.trujillodev.api.exceptions.ValidationRequestException;
import com.trujillodev.api.models.Employee;
import com.trujillodev.api.models.request.EmployeeRequest;
import com.trujillodev.api.models.response.EmployeeResponse;
import com.trujillodev.api.models.response.ErrorResponse;
import com.trujillodev.api.services.IEmployeeService;
import com.trujillodev.api.utils.Utils;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	private IEmployeeService employeeService; 
	
	@GetMapping("/employee/all")
	public ResponseEntity<?> findAll() throws Exception {
		return new ResponseEntity<Object>(employeeService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/employee/get/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
		Map<String, Object> response = new HashMap<>();
		HttpStatus codeResponse = HttpStatus.OK;
		try {
			response.put("statusCode", codeResponse.value());
			response.put("result", employeeService.findById(id));	
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ErrorResponse>(
					ErrorResponse.builder()
					.errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
						.errorMessage(e.getMessage())
						.build(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response, codeResponse);
	}


	@PostMapping("/employee/create")
	public ResponseEntity<?> create(@Valid @RequestBody EmployeeRequest request) throws Exception {
		return new ResponseEntity<Object>(employeeService.insert(Employee.builder()
				.name(request.getNombre())
				.birthDate(request.getFechaNacimiento())
				.salary(request.getSalario())
				.workingDay(request.getDiasTrabajados())
				.build()), HttpStatus.OK);
	}
	
	@GetMapping("/employee/delete/{key}")
	public ResponseEntity<?> delete(@Valid @NotNull @PathVariable("key") String key) throws Exception {
		return new ResponseEntity<Object>(employeeService.delete(key), HttpStatus.OK);
	}

}
