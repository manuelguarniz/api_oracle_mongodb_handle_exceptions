package com.trujillodev.api.core;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.trujillodev.api.models.Employee;
import com.trujillodev.api.models.response.EmployeeResponse;
import com.trujillodev.api.services.IEmployeeRepository;
import com.trujillodev.api.services.IEmployeeService;
import com.trujillodev.api.utils.GeneralHelper;

@Service
public class EmployeeService implements IEmployeeService {

//	@Qualifier("employeeRepository")
//	@Qualifier("employeeMongo")

	@Autowired
	@Qualifier("employeeMongo")
	private IEmployeeRepository employeeRepository;
	
	@Override
	public List<EmployeeResponse> findAll() throws Exception {
		String xd = null;
		xd.toString();
		List<EmployeeResponse> lst = employeeRepository.findAll()
				.stream()
				.map(GeneralHelper::employeeToResponse)
				.collect(Collectors.toList()); 
		return lst;
	}

	@Override
	public EmployeeResponse findById(Integer id) throws Exception {
		return GeneralHelper.employeeToResponse(employeeRepository.findById(id));
	}

	@Override
	public EmployeeResponse insert(Employee employee) throws Exception {
		return GeneralHelper.employeeToResponse(employeeRepository.insert(employee));
	}

	@Override
	public EmployeeResponse update(Employee employee) throws Exception {
		return GeneralHelper.employeeToResponse(employeeRepository.update(employee));
	}

	@Override
	public boolean delete(String key) throws Exception {
		return employeeRepository.delete(new ObjectId(key));
	}

}
