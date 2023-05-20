package hoa.demo.cm.views.form;

import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;

import hoa.demo.cm.data.entity.ABContact;
import hoa.demo.cm.data.entity.Person;

public class PersonForm extends ContactCommonForm {

	private TextField firstName = new TextField("First Name");
	private TextField lastName = new TextField("Last Name");

	private BeanValidationBinder<Person> binder = new BeanValidationBinder<>(Person.class);

	public PersonForm(Person person) {
		binder.bindInstanceFields(this);
		binder.setBean(person);
		add(firstName, lastName);
		addCommonField();
	}


	void addNameTextFields() {
		var firstName = new TextField("FirstName");
		add(firstName);
	}

	@Override
	public BeanValidationBinder<? extends ABContact> getBinder() {
		return binder;
	}

}
