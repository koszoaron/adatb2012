package com.github.koszoaron.adatb2012.field;

import com.github.koszoaron.adatb2012.util.Constants;
import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.TextField;

public class NemzetFieldFactory implements FormFieldFactory {
    private static final long serialVersionUID = 1L;

    public Field createField(Item item, Object propertyId, Component uiContext) {
        String pid = (String)propertyId;
        
        if (pid.equals("nemzetId")) {
            return new TextField("nemzetId");
        } else if (pid.equals(Constants.ORSZAGNEV)) {
            return new TextField(Constants.ORSZAGNEV);
        }
        
        return null;
    }

}
