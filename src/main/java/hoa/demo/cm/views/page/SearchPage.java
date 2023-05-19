package hoa.demo.cm.views.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Scope;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.GridMultiSelectionModel;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouteParameters;
import com.vaadin.flow.spring.annotation.SpringComponent;

import hoa.demo.cm.data.entity.ABContact;
import hoa.demo.cm.data.repository.ContactRepository;
import hoa.demo.cm.data.service.ContactService;
import hoa.demo.cm.views.layout.MainLayout;
import jakarta.annotation.security.PermitAll;

@SpringComponent
@Scope("prototype")
@PermitAll
@Route(value = "", layout = MainLayout.class)
@PageTitle("Search Contacts")
public class SearchPage extends VerticalLayout {

	private ContactService contactService;

	private final ContactRepository contactRepository;

	private Grid<ABContact> grid = new Grid<>(ABContact.class, false);

	private static final List<ABContact> contactSearchResult = new ArrayList<ABContact>();

	public SearchPage(ContactService contactService, ContactRepository contactRepository) {
		this.contactService = contactService;
		this.contactRepository = contactRepository;
		add(getSearchPanel());

		add(new Hr());

		add(createSearchResultLayout());

	}

	private FormLayout getSearchPanel() {
		TextField nameField = new TextField();
		TextField emailField = new TextField();
		FormLayout formLayout = new FormLayout();
		formLayout.setResponsiveSteps(
				// Use one column by default
				new ResponsiveStep("0", 1));
		formLayout.addFormItem(nameField, "Name / First name");
		formLayout.addFormItem(emailField, "E-Mail");

		Button searchButton = new Button("Search");
		searchButton.addClickListener(e -> {
			contactSearchResult.clear();
			contactSearchResult
					.addAll(contactService.findContactByNameAndEmail(nameField.getValue(), emailField.getValue()));
			grid.getDataProvider().refreshAll();
		});

		searchButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		Button resetButton = new Button("Reset");
		resetButton.addClickListener(e -> {
			nameField.clear();
			emailField.clear();
		});
		HorizontalLayout buttonLayout = new HorizontalLayout(searchButton, resetButton);
		formLayout.add(buttonLayout);
		return formLayout;
	}

	private VerticalLayout createSearchResultLayout() {
		contactSearchResult.clear();
		VerticalLayout searchResult = new VerticalLayout();
		Button deleteButton = new Button("Delete");
		deleteButton.setEnabled(false);

		HorizontalLayout headerPanel = new HorizontalLayout(new Label("Search Result"), deleteButton);
		searchResult.add(headerPanel);


		searchResult.add(grid);

		GridMultiSelectionModel<ABContact> selectionModel = (GridMultiSelectionModel<ABContact>) grid
				.setSelectionMode(SelectionMode.MULTI);


		grid.addColumn(new ComponentRenderer<>(
				p -> new Anchor(RouteConfiguration.forSessionScope().getUrl(ContactEditPage.class,
						new RouteParameters(ContactEditPage.ID_Parameter_Route, p.getId().toString())),
						p.getDisplayName())))
				.setHeader("First name");


		grid.addColumn(ABContact::getEmail).setHeader("E-mail");
		
		selectionModel.addMultiSelectionListener(e -> {
			deleteButton.setEnabled(e.getAllSelectedItems().size() > 0);
		});
		deleteButton.addClickListener(e -> {
			Set<ABContact> selectedItems = grid.getSelectedItems();
			contactSearchResult.removeAll(selectedItems);
			contactRepository.deleteAll(selectedItems);
			grid.getDataProvider().refreshAll();

		});
		grid.setItems(contactSearchResult);
		return searchResult;
	}


}