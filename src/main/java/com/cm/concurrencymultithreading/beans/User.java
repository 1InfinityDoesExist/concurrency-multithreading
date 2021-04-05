package com.cm.concurrencymultithreading.beans;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "user")
public class User {

	@Id
	private String id;
	private int userId;
	private String email;
	@NotEmpty
	private String name;
	private Date dob;

}
