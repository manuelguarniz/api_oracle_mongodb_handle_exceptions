package com.trujillodev.api.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.DeleteResult;
import com.trujillodev.api.models.Employee;
import com.trujillodev.api.models.mongodb.EmployeeObject;
import com.trujillodev.api.services.IEmployeeRepository;
import com.trujillodev.api.utils.GeneralHelper;
import com.trujillodev.api.utils.Utils;

@Repository("employeeMongo")
public class EmployeeMongo implements IEmployeeRepository {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Employee> findAll() throws Exception {
		List<Employee> result = new ArrayList<Employee>();
		try {
			List<EmployeeObject> listEmployee = mongoTemplate.findAll(EmployeeObject.class);
			
			result = listEmployee.stream()
					.collect(Collectors.mapping(
							obj -> {
								Date birthDate = null;
								try {
									birthDate = Utils.ConvertFullDateStringToDate(obj.getBirthDate());
								} catch (Exception e) {
									e.printStackTrace();
								}
								return Employee.builder()
										._id(obj.get_id())
										.id(obj.getId())
										.name(obj.getName())
										.birthDate(birthDate)
										.salary(obj.getSalary())
										.workingDay(obj.getWorkingDay())
										.build();
							}
							, Collectors.toList()));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}

	@Override
	public Employee findById(long id) throws Exception {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		EmployeeObject employee = mongoTemplate.findOne(query, EmployeeObject.class); 
		return GeneralHelper.employeeObjectToEmployee(employee);
	}

	@Override
	public Employee insert(Employee employee) throws Exception {
		EmployeeObject objResult = mongoTemplate.insert(EmployeeObject.builder()
				.name(employee.getName())
				.birthDate(Utils.ConvertFullDateToString(employee.getBirthDate()))
				.salary(employee.getSalary())
				.workingDay(employee.getWorkingDay())
				.build());
		return GeneralHelper.employeeObjectToEmployee(objResult);
	}

	@Override
	public Employee update(Employee employee) throws Exception {
		EmployeeObject getObject = mongoTemplate.findById(employee.get_id(), EmployeeObject.class);
		getObject.setName(employee.getName());
		getObject.setBirthDate(Utils.ConvertFullDateToString(employee.getBirthDate()));
		getObject.setSalary(employee.getSalary());
		getObject.setWorkingDay(employee.getWorkingDay());
		mongoTemplate.save(getObject);
		return GeneralHelper.employeeObjectToEmployee(getObject);
	}

	@Override
	public boolean delete(ObjectId key) throws Exception {
		EmployeeObject getObject = mongoTemplate.findById(key, EmployeeObject.class);
		DeleteResult result = mongoTemplate.remove(getObject);
		return result.getDeletedCount() > 0;
	}

}
