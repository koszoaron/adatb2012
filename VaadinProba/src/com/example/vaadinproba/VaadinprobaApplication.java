package com.example.vaadinproba;

import com.vaadin.Application;
import com.vaadin.ui.*;

public class VaadinprobaApplication extends Application {
	@Override
	public void init() {
		Window mainWindow = new Window("Vaadinproba Application");
		Label label = new Label("Hello Vaadin user");
		mainWindow.addComponent(label);
		setMainWindow(mainWindow);
	}

}
