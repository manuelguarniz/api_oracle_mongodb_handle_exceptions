package com.trujillodev.api.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
	private String key;
	private long id;
	private String nombre;
	private String fechaNacimiento;
	private Double salario;
	private Integer diasTrabajados;
}
