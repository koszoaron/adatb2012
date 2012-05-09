package com.github.koszoaron.adatb2012.field;

import com.github.koszoaron.adatb2012.pojo.Tarsasag;
import com.github.koszoaron.adatb2012.util.Constants;
import com.github.koszoaron.adatb2012.util.DatabaseService;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;

public class RepuloFieldFactory implements FormFieldFactory {
    private static final long serialVersionUID = 1L;

    public Field createField(Item item, Object propertyId, Component uiContext) {
        String pid = (String)propertyId;
        
        if (pid.equals(Constants.REPULO_ID_POJO)) {
            return new TextField(Constants.REPULO_ID_POJO);
        } else if (pid.equals(Constants.TIPUS)) {
            return new TextField(Constants.TIPUS);
        } else if (pid.equals(Constants.ULOHELY)) {
            return new TextField(Constants.ULOHELY);
        } else if (pid.equals(Constants.TARSASAG_POJO)) {
            Select selectTarsasag = new Select(Constants.TARSASAG_POJO);
            DatabaseService service = DatabaseService.getInstance();
            Property p = item.getItemProperty(propertyId);
            
            for (Tarsasag t : service.getAllTarsasag()) {
                selectTarsasag.addItem(t);
                selectTarsasag.setItemCaption(t, t.getTarsasagNev());
                
                if (t.equals(((Tarsasag)p.getValue()))) {
                    selectTarsasag.setValue(t);
                }
            }
            
            return selectTarsasag;
        }
        
        return null;
    }

}
