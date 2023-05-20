package hoa.demo.cm.views.form;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;

import hoa.demo.cm.data.entity.ABContact;

public abstract class ContactCommonForm extends FormLayout {

	protected TextField email = new TextField("Email");

	protected TextField telephone = new TextField("Telephone");

	protected void addCommonField() {
		add(email, telephone);
	}

	public abstract BeanValidationBinder<? extends ABContact> getBinder();
}
