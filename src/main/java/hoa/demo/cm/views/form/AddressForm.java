package hoa.demo.cm.views.form;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;

import hoa.demo.cm.data.entity.Address;

public class AddressForm extends FormLayout {

	private TextField street = new TextField("Street");
	private TextField houseNumber = new TextField("House Number");
	private TextField city = new TextField("City");
	private TextField potalcode = new TextField("Potal code");

	private BeanValidationBinder<Address> binder = new BeanValidationBinder<>(Address.class);

	public AddressForm(Address address) {
		binder.bindInstanceFields(this);
		binder.setBean(address);
		add(street, houseNumber, city, potalcode);
	}

	public BeanValidationBinder<Address> getBinder() {
		return binder;
	}

}
