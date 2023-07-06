package com.seersol.leatrac.views;

import javax.annotation.security.PermitAll;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.seersol.leatrac.entity.AppUser;
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
@Route(value = "userview", layout = MainLayout.class)
@PageTitle("Application Users | LeaTrack LTM")
@PermitAll
public class UserView extends VerticalLayout {
	public Grid<AppUser> grid = new Grid<>(AppUser.class);
	TextField filterText = new TextField();
	public UserForm form;
	LeaTrackDataService service;

	public UserView(LeaTrackDataService service) {
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
		form = new UserForm(service.findAllUsers(),service.findAllUserStatus());
		form.setWidth("25em");
		form.addListener(UserForm.SaveEvent.class, this::saveUser);
		form.addListener(UserForm.CloseEvent.class, e -> closeEditor());
	}

	private void configureGrid() {
		grid.addClassNames("contact-grid");
		grid.setSizeFull();
		grid.setColumns("userName", "password", "email");
		grid.addColumn(user -> user.getStatus().getName()).setHeader("Status");
		grid.getColumns().forEach(col -> col.setAutoWidth(true));
		grid.asSingleSelect().addValueChangeListener(event -> editUser(event.getValue()));
	}

	private HorizontalLayout getToolbar() {
		filterText.setPlaceholder("Filter by User Name...");
		filterText.setClearButtonVisible(true);
		filterText.setValueChangeMode(ValueChangeMode.LAZY);
		filterText.addValueChangeListener(e -> updateList());

		Button addUserButton = new Button("Add  New User");
		addUserButton.addClickListener(click -> addUser());

		HorizontalLayout toolbar = new HorizontalLayout(filterText, addUserButton);
		toolbar.addClassName("toolbar");
		return toolbar;
	}

	private void saveUser(UserForm.SaveEvent event) {
		service.saveUser(event.getUser());
		updateList();
		closeEditor();
	}

	public void editUser(AppUser user) {
		if (user == null) {
			closeEditor();
		} else {
			form.setUser(user);
			form.setVisible(true);
			addClassName("editing");
		}
	}

	private void addUser() {
		grid.asSingleSelect().clear();
		editUser(new AppUser());
	}

	private void closeEditor() {
		form.setUser(null);
		form.setVisible(false);
		removeClassName("editing");
	}

	private void updateList() {
		grid.setItems(service.findAllUsers(filterText.getValue()));
	}
}
