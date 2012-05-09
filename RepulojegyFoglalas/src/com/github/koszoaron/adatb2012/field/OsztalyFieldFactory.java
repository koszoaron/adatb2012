package com.github.koszoaron.adatb2012.field;

import com.github.koszoaron.adatb2012.util.Constants;
import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.TextField;

public class OsztalyFieldFactory implements FormFieldFactory {
    private static final long serialVersionUID = 1L;

    public Field createField(Item item, Object propertyId, Component uiContext) {
        String pid = (String)propertyId;
        
        if (pid.equals(Constants.SZAMA)) {
            return new TextField(Constants.SZAMA);
        } else if (pid.equals(Constants.ETKEZES)) {
            return new TextField(Constants.ETKEZES);
        } else if (pid.equals(Constants.RELAX)) {
            return new TextField(Constants.RELAX);
        } else if (pid.equals(Constants.EXTRA_BOROND_POJO)) {
            return new TextField(Constants.EXTRA_BOROND_POJO);
        } else if (pid.equals(Constants.INTERNET)) {
            return new TextField(Constants.INTERNET);
        }
        
        return null;
    }

}
