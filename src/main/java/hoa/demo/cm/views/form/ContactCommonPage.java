package hoa.demo.cm.views.form;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import hoa.demo.cm.data.entity.ABContact;
import hoa.demo.cm.data.entity.Company;
import hoa.demo.cm.data.entity.Person;
import hoa.demo.cm.data.repository.ContactRepository;

public abstract class ContactCommonPage extends VerticalLayout {

	protected ContactRepository contactRepository;

	private Binder<? extends ABContact> binder;

	public ContactCommonPage(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}

	public void renderView(ABContact contact) {
		createButtons();
		createMainData(contact);
	}

	private void createButtons() {
		Button saveButton = new Button("Save");
		saveButton.addClickListener(event -> validateAndSave());
		Button cancelButton = new Button("Cancel");
		cancelButton.addClickListener(event -> discardChangeAndExist());
		HorizontalLayout buttonLayout = new HorizontalLayout(saveButton, cancelButton);
		add(buttonLayout);
	}

	private void createMainData(ABContact contact) {
		ContactCommonForm newContactForm = null;
		if (contact instanceof Person) {
			newContactForm = new PersonForm();
		} else if (contact instanceof Company) {
			newContactForm = new CompanyForm();
		}
		this.add(newContactForm);
		binder = newContactForm.getBinder();
	}

	private void validateAndSave() {
		if (binder.isValid()) {
			contactRepository.save(binder.getBean());
			this.getUI().ifPresent(ui -> ui.navigate(""));
		}
	}

	private void discardChangeAndExist() {
		this.getUI().ifPresent(ui -> ui.navigate(""));
	}
}
