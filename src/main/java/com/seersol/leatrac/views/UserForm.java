package com.seersol.leatrac.views;

import java.util.List;

import com.seersol.leatrac.entity.AppUser;
import com.seersol.leatrac.entity.Status;
import com.seersol.leatrac.entity.UserStatus;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

public class UserForm extends FormLayout {

	private static final long serialVersionUID = 1L;

	private AppUser user;

	public TextField userName = new TextField("User Name");
	public TextField password = new TextField("Password");
	public EmailField email = new EmailField("Email");
	public ComboBox<UserStatus> status = new ComboBox<>("Status");
	public Binder<AppUser> binder = new BeanValidationBinder<>(AppUser.class);

	public Button save = new Button("Save");
	public Button close = new Button("Cancel");

	public UserForm(List<AppUser> users, List<UserStatus> statuses) {
		addClassName("contact-form");
		binder.bindInstanceFields(this);
		status.setItems(statuses);
		status.setItemLabelGenerator(UserStatus::getName);
		add(userName, password, email, status, createButtonsLayout());
	}

	private HorizontalLayout createButtonsLayout() {
		save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

		save.addClickShortcut(Key.ENTER);
		close.addClickShortcut(Key.ESCAPE);

		save.addClickListener(event -> validateAndSave());
		close.addClickListener(event -> fireEvent(new CloseEvent(this)));

		binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

		HorizontalLayout buttonLayout = new HorizontalLayout(save, close);
		buttonLayout.setAlignItems(Alignment.CENTER);
		return buttonLayout;
	}

	public void setUser(AppUser user) {
		this.user = user;
		binder.readBean(user);
	}

	private void validateAndSave() {
		try {
			binder.writeBean(user);
			fireEvent(new SaveEvent(this, user));
		} catch (ValidationException e) {
			e.printStackTrace();
		}
	}

	// Events
	public static abstract class UserFormEvent extends ComponentEvent<UserForm> {
		private AppUser user;

		protected UserFormEvent(UserForm source, AppUser user) {
			super(source, false);
			this.user = user;
		}

		public AppUser getUser() {
			return user;
		}
	}

	public static class SaveEvent extends UserFormEvent {
		SaveEvent(UserForm source, AppUser user) {
			super(source, user);
		}
	}

	public static class CloseEvent extends UserFormEvent {
		CloseEvent(UserForm source) {
			super(source, null);
		}
	}

	public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
			ComponentEventListener<T> listener) {
		return getEventBus().addListener(eventType, listener);
	}
}