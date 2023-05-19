package hoa.demo.cm.data.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Person extends ABContact {

	public Person() {

	}

	@NotEmpty
	private String firstName = "";

	@NotEmpty
	private String lastName = "";


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



}
