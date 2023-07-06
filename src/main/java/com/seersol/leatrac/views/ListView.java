package com.seersol.leatrac.views;

import javax.annotation.security.PermitAll;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.seersol.leatrac.entity.Contact;
import com.seersol.leatrac.service.LeaTrackDataService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Component
@Scope("prototype")
@Route(value = "listview", layout = MainLayout.class)
@PageTitle("Contacts | LeaTrack LTM")
@PermitAll
public class ListView extends VerticalLayout {
	public Grid<Contact> grid = new Grid<>(Contact.class);
	TextField filterText = new TextField();
	public ContactForm form;
	LeaTrackDataService service;

	public ListView(LeaTrackDataService service) {
		this.service = service;
		addClassName("list-view");
		setSizeFull();
		configureGrid();
		configureForm();

		add(getToolbar(), getContent());
		updateList();
		closeEditor();
	}

	private HorizontalLayout getContent() {
		HorizontalLayout content = new HorizontalLayout(grid, form);
		content.setFlexGrow(2, grid);
		content.setFlexGrow(1, form);
		content.addClassNames("content");
		content.setSizeFull();
		return content;
	}

	private void configureForm() {
		form = new ContactForm(service.findAllCompanies(), service.findAllStatuses());
		form.setWidth("25em");
		form.addListener(ContactForm.SaveEvent.class, this::saveContact);
		form.addListener(ContactForm.DeleteEvent.class, this::deleteContact);
		form.addListener(ContactForm.CloseEvent.class, e -> closeEditor());
	}

	private void configureGrid() {
		grid.addClassNames("contact-grid");
		grid.setSizeFull();
		grid.setColumns("firstName", "lastName", "email", "city", "state");
		grid.addColumn(contact -> contact.getStatus().getName()).setHeader("Status");
		grid.addColumn(contact -> contact.getCompany().getName()).setHeader("Property");
		grid.getColumns().forEach(col -> col.setAutoWidth(true));
		grid.asSingleSelect().addValueChangeListener(event -> editContact(event.getValue()));
	}

	private HorizontalLayout getToolbar() {
		filterText.setPlaceholder("Filter by name...");
		filterText.setClearButtonVisible(true);
		filterText.setValueChangeMode(ValueChangeMode.LAZY);
		filterText.addValueChangeListener(e -> updateList());

		Button addContactButton = new Button("Add  New Lead");
		addContactButton.addClickListener(click -> addContact());

		HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton);
		toolbar.addClassName("toolbar");
		return toolbar;
	}

	private void saveContact(ContactForm.SaveEvent event) {
		service.saveContact(event.getContact());
		updateList();
		closeEditor();
	}

	private void deleteContact(ContactForm.DeleteEvent event) {
		service.deleteContact(event.getContact());
		updateList();
		closeEditor();
	}

	public void editContact(Contact contact) {
		if (contact == null) {
			closeEditor();
		} else {
			form.setContact(contact);
			form.setVisible(true);
			addClassName("editing");
		}
	}

	private void addContact() {
		grid.asSingleSelect().clear();
		editContact(new Contact());
	}

	private void closeEditor() {
		form.setContact(null);
		form.setVisible(false);
		removeClassName("editing");
	}

	private void updateList() {
		grid.setItems(service.findAllContacts(filterText.getValue()));
	}
}
