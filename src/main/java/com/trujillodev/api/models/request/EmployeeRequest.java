package com.trujillodev.api.models.request;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class EmployeeRequest {
	private String key;
	private long id;
	
	@NotNull(message = "El nombre es requerido")
	@NotEmpty(message = "El nombre no puede ser vácio")
	private String nombre;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date fechaNacimiento;

	@Positive(message = "Los días trabajados no puede ser menor o igual a 0")
	private Double salario;
	
	@Positive(message = "El salario no puede ser menor o igual a 0")
	private Integer diasTrabajados;
}
