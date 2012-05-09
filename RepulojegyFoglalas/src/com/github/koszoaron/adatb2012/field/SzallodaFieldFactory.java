package com.github.koszoaron.adatb2012.field;

import com.github.koszoaron.adatb2012.pojo.Varos;
import com.github.koszoaron.adatb2012.util.Constants;
import com.github.koszoaron.adatb2012.util.DatabaseService;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class SzallodaFieldFactory implements FormFieldFactory {
    private static final long serialVersionUID = 1L;

    public Field createField(Item item, Object propertyId, Component uiContext) {
        String pid = (String)propertyId;
        
        if (pid.equals(Constants.NEVE_POJO)) {
            return new TextField(Constants.NEVE_POJO);
        } else if (pid.equals(Constants.LEIRAS)) {
            return new TextArea(Constants.LEIRAS);
        } else if (pid.equals(Constants.VAROS_POJO)) {
            Select selectVaros = new Select(Constants.VAROS_POJO);
            DatabaseService service = DatabaseService.getInstance();
            Property p = item.getItemProperty(propertyId);
            
            for (Varos v : service.getAllVaros()) {
                selectVaros.addItem(v);
                selectVaros.setItemCaption(v, v.getVarosnev());
                
                if (v.equals(((Varos)p.getValue()))) {
                    selectVaros.setValue(v);
                }
            }
            
            return selectVaros;
        }
        
        return null;
    }

}
