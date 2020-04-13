package com.trujillodev.api.repositories;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.trujillodev.api.models.Employee;
import com.trujillodev.api.services.IEmployeeRepository;

@Repository("employeeRepository")
public class EmployeeRepository implements IEmployeeRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@PostConstruct
	public void init() {
		jdbcTemplate.setResultsMapCaseInsensitive(true);
	}

	@Override
	public List<Employee> findAll() throws Exception {
		String query = "SELECT ID ,\r\n" + 
				"NOMBRE ,\r\n" + 
				"FECHANACI ,\r\n" + 
				"SALARIO ,\r\n" + 
				"ACTUALIZADO ,\r\n" + 
				"DIASTRABAJADOS  FROM EMPLEADOS";
		
		List<Employee> lstEmployee = jdbcTemplate.query(query,
				(result, rowNum) -> Employee.builder()
					.id(result.getLong("ID"))
					.name(result.getString("NOMBRE"))
					.workingDay(result.getInt("DIASTRABAJADOS"))
					.birthDate(result.getDate("FECHANACI"))
					.salary(result.getDouble("SALARIO"))
					.build());
		return lstEmployee;
	}

	@Override
	public Employee findById(long id) throws Exception {
		Employee employee = null;
		
		SimpleJdbcCall procedureGetEmployeeById = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("FIND_EMPLOYEE_BY_ID")
				.returningResultSet("RESULTSET", BeanPropertyRowMapper.newInstance(Employee.class));
		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("ID_EMPLOYEE", id);
		
		Map<String, Object> result = procedureGetEmployeeById.execute(parameters);
				
		if (result != null) {
			employee = ((List<Employee>) result.get("RESULTSET")).get(0);
		}
		return employee;
	}

	@Override
	public Employee insert(Employee employee) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee update(Employee employee) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(ObjectId key) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
