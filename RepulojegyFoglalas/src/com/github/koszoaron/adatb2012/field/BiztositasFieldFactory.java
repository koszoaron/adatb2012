package com.github.koszoaron.adatb2012.field;

import com.github.koszoaron.adatb2012.util.Constants;
import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.TextField;

public class BiztositasFieldFactory implements FormFieldFactory {
    private static final long serialVersionUID = 1L;

    public Field createField(Item item, Object propertyId, Component uiContext) {
        String pid = (String)propertyId;
        
        if (pid.equals(Constants.BIZT_ID_POJO)) {
            return new TextField(Constants.BIZT_ID_POJO);
        } else if (pid.equals(Constants.UTLEMONDAS)) {
            return new TextField(Constants.UTLEMONDAS);
        } else if (pid.equals(Constants.POGGYASZ)) {
            return new TextField(Constants.POGGYASZ);
        } else if (pid.equals(Constants.ARVISSZAAD)) {
            return new TextField(Constants.ARVISSZAAD);
        }
        
        return null;
    }

}
