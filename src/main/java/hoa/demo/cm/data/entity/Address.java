package hoa.demo.cm.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgenerator_address")
	@SequenceGenerator(name = "idgenerator", initialValue = 1)
	private Long id;

	@Version
	private int version;

	@NotBlank
	private String street;

	@NotBlank
	private String houseNumber;

	@NotBlank
	private String city;

	@NotBlank
	private String potalcode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPotalcode() {
		return potalcode;
	}

	public void setPotalcode(String potalcode) {
		this.potalcode = potalcode;
	}

	@Override
	public int hashCode() {
		if (getId() != null) {
			return getId().hashCode();
		}
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ABContact)) {
			return false; // null or other class
		}
		ABContact other = (ABContact) obj;

		if (getId() != null) {
			return getId().equals(other.getId());
		}
		return super.equals(other);
	}

}
