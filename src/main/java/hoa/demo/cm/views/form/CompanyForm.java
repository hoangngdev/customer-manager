package hoa.demo.cm.views.form;

import com.vaadin.flow.data.binder.BeanValidationBinder;

import hoa.demo.cm.data.entity.ABContact;
import hoa.demo.cm.data.entity.Company;
import hoa.demo.cm.data.entity.Person;

public class CompanyForm extends ContactCommonForm {

	private BeanValidationBinder<Company> binder = new BeanValidationBinder<>(Company.class);

	public CompanyForm() {
		// super(new BeanValidationBinder<>(Company.class), new Company());
	}


	void addNameTextFields() {
		// TODO Auto-generated method stub

	}

	@Override
	protected BeanValidationBinder<? extends ABContact> getBinder() {
		return binder;
	}
}
