package hoa.demo.cm.views.page;

import org.springframework.context.annotation.Scope;

import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;

import hoa.demo.cm.data.entity.Company;
import hoa.demo.cm.data.entity.Person;
import hoa.demo.cm.data.repository.ContactRepository;
import hoa.demo.cm.views.layout.InsideLayout;
import jakarta.annotation.security.PermitAll;

@SpringComponent
@Scope("prototype")
@PermitAll
@Route(value = "/newcontact/:contactType(Person|Company)", layout = InsideLayout.class)
@PageTitle("New Contact")
public class ContactNewPage extends ContactCommonPage implements BeforeEnterObserver {

	public static final String Contact_Type_Parameter_Route = "contactType";

	public ContactNewPage(ContactRepository contactRepository) {
		super(contactRepository);
	}

	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		String contactType = event.getRouteParameters().get(Contact_Type_Parameter_Route).get();
		if (contactType != null) {
			if (contactType.equals("Person")) {
				super.renderView(new Person());
			} else if (contactType.equals("Company")) {
				super.renderView(new Company());
			}

		}
	}
}
