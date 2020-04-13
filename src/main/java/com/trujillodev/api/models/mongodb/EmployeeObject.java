package com.trujillodev.api.models.mongodb;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Document("employee")
public class EmployeeObject {

	@Id
	private ObjectId _id;
	private long id;
	private String name;
	private String birthDate;
	private Double salary;
	private Integer workingDay;
	
}
