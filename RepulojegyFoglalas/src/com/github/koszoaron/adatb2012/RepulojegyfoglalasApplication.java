package com.github.koszoaron.adatb2012;

import com.github.koszoaron.adatb2012.pojo.Felhasznalo;
import com.github.koszoaron.adatb2012.util.Constants;
import com.github.koszoaron.adatb2012.util.DatabaseService;
import com.vaadin.Application;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

public class RepulojegyfoglalasApplication extends Application {
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private Table elementList = new Table();
    private VerticalLayout editorLayout = new VerticalLayout();
    private Form elementEditor = new Form();
    private HorizontalLayout bottomLeftCorner = new HorizontalLayout();
    
    private BeanItemContainer<Felhasznalo> felhasznalok;
    private DatabaseService service;

    @Override
	public void init() {
        service = DatabaseService.getInstance();
        StringBuilder toDisplay = new StringBuilder("");
        
        felhasznalok = new BeanItemContainer<Felhasznalo>(Felhasznalo.class);
        for (Felhasznalo f : service.getAllFelhasznalo()) {
            if (f != null) {
                felhasznalok.addBean(f);
                toDisplay.append(f.toString());
                toDisplay.append("\n");
            }
        }

        initLayout();
        loadData(felhasznalok, Constants.COLS_FELHASZNALO);
	}
    
    private void initLayout() {
        HorizontalSplitPanel splitPanel = new HorizontalSplitPanel();
        setMainWindow(new Window("Repulojegy Foglalas", splitPanel));
        
        VerticalLayout left = new VerticalLayout();
        left.setSizeFull();
        left.addComponent(elementList);
        elementList.setSizeFull();
        left.setExpandRatio(elementList, 1);
        splitPanel.addComponent(left);
        
        splitPanel.addComponent(editorLayout);
        
        elementEditor.setSizeFull();
        elementEditor.getLayout().setMargin(true);
        elementEditor.setImmediate(true);
        editorLayout.addComponent(elementEditor);
        editorLayout.addComponent(new Button("Save", new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                elementEditor.commit();
                saveData(elementList.getValue());
            }
        }));
        editorLayout.setVisible(false);
        
        bottomLeftCorner.setWidth("100%");
        left.addComponent(bottomLeftCorner);
    }

    private void loadData(Container container, String[] cols) {
        elementList.setContainerDataSource(container);
        elementList.setVisibleColumns(cols);
        elementList.setSelectable(true);
        elementList.setImmediate(true);
        elementList.addListener(new Property.ValueChangeListener() {
            private static final long serialVersionUID = 1L;

            public void valueChange(ValueChangeEvent event) {
                Object id = elementList.getValue();
                elementEditor.setItemDataSource(id == null ? null : elementList.getItem(id));
                editorLayout.setVisible(id != null);
            }
        });
    }
    
    private void saveData(Object id) {
        if (id != null) {
            Felhasznalo f = felhasznalok.getItem(id).getBean();
            //update
        }
    }
    
    private void showNotification(String message) {
        Window.Notification n = new Window.Notification("", message, Window.Notification.TYPE_TRAY_NOTIFICATION);
        n.setDelayMsec(Window.Notification.DELAY_FOREVER);
        getMainWindow().showNotification(n);
    }
    
    private void showNotification(String message, String title) {
        Window.Notification n = new Window.Notification(title, message, Window.Notification.TYPE_TRAY_NOTIFICATION);
        n.setDelayMsec(Window.Notification.DELAY_FOREVER);
        getMainWindow().showNotification(n);
    }
}
