package hoa.demo.cm.data.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "ContactType")
@Table(name = "Contact")
public abstract class ABContact {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgenerator_contact")
	@SequenceGenerator(name = "idgenerator", initialValue = 1)
	private Long id;

	@Version
	private int version;

	@Email(message = "${validatedValue} is not a valid email")
	private String email;

	@NotEmpty
	private String telephone;


	@OneToOne(orphanRemoval = false, cascade = CascadeType.ALL)
	private Address primaryAddress;

	public String getDisplayName() {
		if (this instanceof Person) {
			Person person = (Person) this;
			return person.getFirstName() + " " + person.getLastName();
		} else if (this instanceof Company) {
			Company company = (Company) this;
			return company.getName();
		} else {
			return "";
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public Address getPrimaryAddress() {
		return primaryAddress;
	}

	public Address getOrCreatePrimaryAddress() {
		if (primaryAddress != null) {
			return primaryAddress;
		} else {
			Address newAddress = new Address();
			this.primaryAddress = newAddress;
			return newAddress;
		}
	}

	public String getPrimaryAddressDisplayName() {
		return primaryAddress != null ? primaryAddress.getDisplayName() : "";
	}

	public void setPrimaryAddress(Address primaryAddress) {
		this.primaryAddress = primaryAddress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

	@Override
	public String toString() {
		return "ABContact{id =" + id + "}";
	}
}
