package com.github.koszoaron.adatb2012.field;

import com.github.koszoaron.adatb2012.util.Constants;
import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class FelhasznaloFieldFactory implements FormFieldFactory {
    private static final long serialVersionUID = 1L;

    public Field createField(Item item, Object propertyId, Component uiContext) {
        String pid = (String)propertyId;
        
        if (pid.equals(Constants.USERNAME)) {
            return new TextField(Constants.USERNAME);
        } else if (pid.equals(Constants.PASS)) {
            return new PasswordField(Constants.PASS);
        } else if (pid.equals(Constants.OKMANYSZAM)) {
            return new TextField(Constants.OKMANYSZAM);
        } else if (pid.equals(Constants.NEVE)) {
            return new TextField(Constants.NEVE);
        } else if (pid.equals(Constants.SZULETETT)) {
            DateField df = new DateField(Constants.SZULETETT);
            df.setDateFormat("yyyy-MM-dd");
            return df;
        } else if (pid.equals(Constants.BANKKARTYASZAM)) {
            return new TextField(Constants.BANKKARTYASZAM);
        } else if (pid.equals(Constants.LAKCIM)) {
            return new TextField(Constants.LAKCIM);
        } else if (pid.equals(Constants.TELEFONSZAM)) {
            return new TextField(Constants.TELEFONSZAM);
        }
        
        return null;

    }

}
