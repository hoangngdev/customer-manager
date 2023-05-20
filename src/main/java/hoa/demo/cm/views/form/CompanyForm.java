package hoa.demo.cm.views.form;

import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;

import hoa.demo.cm.data.entity.ABContact;
import hoa.demo.cm.data.entity.Company;

public class CompanyForm extends ContactCommonForm {

	private TextField name = new TextField("Name");

	private BeanValidationBinder<Company> binder = new BeanValidationBinder<>(Company.class);

	public CompanyForm(Company company) {
		binder.bindInstanceFields(this);
		binder.setBean(company);
		add(name);
		addCommonField();
	}

	@Override
	public BeanValidationBinder<? extends ABContact> getBinder() {
		return binder;
	}
}
