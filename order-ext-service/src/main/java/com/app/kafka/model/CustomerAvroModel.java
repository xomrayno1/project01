package com.app.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAvroModel  {
	
	  private java.lang.String id;
	  private java.lang.String username;
	  private java.lang.String firstName;
	  private java.lang.String lastName;

	 
}
