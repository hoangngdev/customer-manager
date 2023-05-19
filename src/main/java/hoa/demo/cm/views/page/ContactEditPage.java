package hoa.demo.cm.views.page;

import org.springframework.context.annotation.Scope;

import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;

import hoa.demo.cm.data.entity.ABContact;
import hoa.demo.cm.data.repository.ContactRepository;
import hoa.demo.cm.views.form.ContactCommonPage;
import hoa.demo.cm.views.layout.InsideLayout;
import jakarta.annotation.security.PermitAll;

@SpringComponent
@Scope("prototype")
@PermitAll
@Route(value = "/editcontact/:id([0-9]*)", layout = InsideLayout.class)
@PageTitle("Edit Contacts")
public class ContactEditPage extends ContactCommonPage implements BeforeEnterObserver {

	public static final String ID_Parameter_Route = "id";

	public ContactEditPage(ContactRepository contactRepository) {
		super(contactRepository);

	}

	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		String userID = event.getRouteParameters().get(ID_Parameter_Route).get();
		if (userID != null) {
			ABContact person = contactRepository.findById(Long.valueOf(userID)).get();
			if (person != null) {
				super.renderView(person);
			}
		}
	}
}
