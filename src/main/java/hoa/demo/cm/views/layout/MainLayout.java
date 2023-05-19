package hoa.demo.cm.views.layout;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;

import hoa.demo.cm.security.SecurityService;

public class MainLayout extends AppLayout {

	private final SecurityService securityService;

	public MainLayout(SecurityService securityService) {
		this.securityService = securityService;
		createHeader();
		createDrawer();
	}

	private void createHeader() {
		H1 logo = new H1("Vaadin CRM");
		logo.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.MEDIUM);

		String u = securityService.getAuthenticatedUser().getUsername();
		Button logout = new Button("Log out " + u, e -> securityService.logout());

		var header = new HorizontalLayout(new DrawerToggle(), logo, logout);

		header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
		header.expand(logo);
		header.setWidthFull();
		header.addClassNames(LumoUtility.Padding.Vertical.NONE, LumoUtility.Padding.Horizontal.MEDIUM);

		addToNavbar(header);

	}

	private void createDrawer() {
		MenuBar menuBar = new MenuBar();
		MenuItem newContact = menuBar.addItem(new Div(new Span("New Contact"), VaadinIcon.ANGLE_RIGHT.create()));

		MenuItem newPerson = newContact.getSubMenu().addItem("New Person");
		newPerson.addClickListener(e -> newPerson.getUI().ifPresent(ui -> ui.navigate("newcontact/Person")));

		MenuItem newCompany = newContact.getSubMenu().addItem("New Company");
		newCompany.addClickListener(e -> newPerson.getUI().ifPresent(ui -> ui.navigate("newcontact/Company")));

		addToDrawer(menuBar);
	}
}