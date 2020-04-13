package com.trujillodev.api.models;

import java.util.Date;

import org.bson.types.ObjectId;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	private ObjectId _id;
	private long id;
	private String name;
	private Date birthDate;
	private Double salary;
	private Integer workingDay;
}
