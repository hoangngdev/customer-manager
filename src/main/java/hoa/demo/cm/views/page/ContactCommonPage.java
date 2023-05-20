package hoa.demo.cm.views.page;

import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BindingValidationStatus;

import hoa.demo.cm.data.entity.ABContact;
import hoa.demo.cm.data.entity.Address;
import hoa.demo.cm.data.entity.Company;
import hoa.demo.cm.data.entity.Person;
import hoa.demo.cm.data.repository.ContactRepository;
import hoa.demo.cm.views.form.AddressForm;
import hoa.demo.cm.views.form.CompanyForm;
import hoa.demo.cm.views.form.ContactCommonForm;
import hoa.demo.cm.views.form.PersonForm;

public abstract class ContactCommonPage extends VerticalLayout {

	protected ContactRepository contactRepository;

	private Binder<? extends ABContact> contactBinder;

	private Binder<Address> addressBinder;

	private ABContact contact;

	public ContactCommonPage(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}

	public void renderView(ABContact contact) {
		this.contact = contact;
		createButtons();
		createMainData();
		createAddressForm();
	}

	private void createButtons() {
		Button saveButton = new Button("Save");
		saveButton.addClickListener(event -> validateAndSave());
		Button cancelButton = new Button("Cancel");
		cancelButton.addClickListener(event -> discardChangeAndExist());
		HorizontalLayout buttonLayout = new HorizontalLayout(saveButton, cancelButton);
		add(buttonLayout);
	}

	private void createMainData() {
		ContactCommonForm newContactForm = null;
		if (contact instanceof Person person) {
			newContactForm = new PersonForm(person);
		} else if (contact instanceof Company company) {
			newContactForm = new CompanyForm(company);
		}
		this.add(newContactForm);
		contactBinder = newContactForm.getBinder();

	}

	private void createAddressForm() {
		this.add(new Hr());
		this.add(new Label("Primary Address"));
		AddressForm addresForm = new AddressForm(contact.getOrCreatePrimaryAddress());
		this.add(addresForm);
		this.addressBinder = addresForm.getBinder();
	}

	private void validateAndSave() {
		var isContactValid = contactBinder.validate().getFieldValidationErrors().isEmpty();
		var isAddressValid = addressBinder.validate().getFieldValidationErrors().isEmpty();
		if (isContactValid && isAddressValid) {
			contactRepository.save(contactBinder.getBean());
			this.getUI().ifPresent(ui -> ui.navigate(""));
		}
	}

	private void discardChangeAndExist() {
		this.getUI().ifPresent(ui -> ui.navigate(""));
	}
}
