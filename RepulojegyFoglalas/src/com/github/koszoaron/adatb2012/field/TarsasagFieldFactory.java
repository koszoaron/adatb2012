package com.github.koszoaron.adatb2012.field;

import com.github.koszoaron.adatb2012.pojo.Nemzet;
import com.github.koszoaron.adatb2012.util.Constants;
import com.github.koszoaron.adatb2012.util.DatabaseService;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;

public class TarsasagFieldFactory implements FormFieldFactory {
    private static final long serialVersionUID = 1L;

    public Field createField(Item item, Object propertyId, Component uiContext) {
        String pid = (String)propertyId;
        
        if (pid.equals(Constants.TARSASAG_ID_POJO)) {
            return new TextField(Constants.TARSASAG_ID_POJO);
        } else if (pid.equals(Constants.TARSASAG_NEV_POJO)) {
            return new TextField(Constants.TARSASAG_NEV_POJO);
        } else if (pid.equals(Constants.NEMZET_POJO)) {
            Select selectNemzet = new Select(Constants.NEMZET_POJO);
            DatabaseService service = DatabaseService.getInstance();
            Property p = item.getItemProperty(propertyId);
            
            for (Nemzet n : service.getAllNemzet()) {
                selectNemzet.addItem(n);
                selectNemzet.setItemCaption(n, n.getOrszagnev());
                
                if (n.equals(((Nemzet)p.getValue()))) {
                    selectNemzet.setValue(n);
                }
            }
            
            return selectNemzet;
        }
        
        return null;
    }

}
