package com.github.koszoaron.adatb2012;

import com.github.koszoaron.adatb2012.pojo.Felhasznalo;
import com.github.koszoaron.adatb2012.util.DatabaseService;
import com.vaadin.Application;
import com.vaadin.ui.*;

public class RepulojegyfoglalasApplication extends Application {
	/**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
	public void init() {
        DatabaseService service = DatabaseService.getInstance();
        Felhasznalo user = service.getFelhasznaloByUsername("ARON");
        System.out.println("proba");
        System.out.println(user.toString());
        String toDisplay = "null";
        if (user != null) {
            toDisplay = user.toString();
        }
        
		Window mainWindow = new Window("Repulojegy Foglalas");
		Label label = new Label(toDisplay);
		mainWindow.addComponent(label);
		setMainWindow(mainWindow);
	}

}
