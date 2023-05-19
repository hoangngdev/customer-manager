package hoa.demo.cm.views.layout;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;

import hoa.demo.cm.security.SecurityService;

public class InsideLayout extends AppLayout {

	private final SecurityService securityService;

	public InsideLayout(SecurityService securityService) {
		this.securityService = securityService;
		createHeader();

	}

	private void createHeader() {
		H1 logo = new H1("Vaadin CRM");
		logo.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.MEDIUM);

		String u = securityService.getAuthenticatedUser().getUsername();
		Button logout = new Button("Log out " + u, e -> securityService.logout()); // <2>

		var header = new HorizontalLayout(new DrawerToggle(), logo, logout);

		header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
		header.expand(logo); // <4>
		header.setWidthFull();
		header.addClassNames(LumoUtility.Padding.Vertical.NONE, LumoUtility.Padding.Horizontal.MEDIUM);

		addToNavbar(header);

	}

}