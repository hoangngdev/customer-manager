package hoa.demo.cm.views.form;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;

import hoa.demo.cm.data.entity.ABContact;
import hoa.demo.cm.data.entity.Company;
import hoa.demo.cm.data.entity.Person;

public class PersonForm extends ContactCommonForm {

	private TextField firstName = new TextField("FirstName");

	private BeanValidationBinder<Person> binder = new BeanValidationBinder<>(Person.class);

	public PersonForm() {
		binder.bindInstanceFields(this);
		binder.setBean(new Person());
		
		add(firstName);
		addCommonField();
		add(new Button("Save", event -> save()));
	}

	private void save() {
		if (binder.validate().isOk()) {
			Person user = binder.getBean();

		}
	}

	void addNameTextFields() {
		var firstName = new TextField("FirstName");
		add(firstName);
	}

	@Override
	protected BeanValidationBinder<? extends ABContact> getBinder() {
		return binder;
	}

}
