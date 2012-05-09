package com.github.koszoaron.adatb2012.field;

import com.github.koszoaron.adatb2012.pojo.Repulo;
import com.github.koszoaron.adatb2012.pojo.Varos;
import com.github.koszoaron.adatb2012.util.Constants;
import com.github.koszoaron.adatb2012.util.DatabaseService;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;

public class JaratFieldFactory implements FormFieldFactory {
    private static final long serialVersionUID = 1L;

    public Field createField(Item item, Object propertyId, Component uiContext) {
        String pid = (String)propertyId;
        
        if (pid.equals(Constants.JARAT_ID_POJO)) {
            return new TextField(Constants.JARAT_ID_POJO);
        } else if (pid.equals(Constants.HONNAN)) {
            Select selectVaros = new Select(Constants.HONNAN);
            DatabaseService service = DatabaseService.getInstance();
            Property p = item.getItemProperty(propertyId);
            for (Varos v : service.getAllVaros()) {
                selectVaros.addItem(v);
                selectVaros.setItemCaption(v, v.getVarosnev() + " (" + v.getNemzet().getOrszagnev() + ")");
                
                if (v.equals(((Varos)p.getValue()))) {
                    selectVaros.setValue(v);
                }
            }
            
            return selectVaros;
        } else if (pid.equals(Constants.HOVA)) {
            Select selectVaros = new Select(Constants.HOVA);
            DatabaseService service = DatabaseService.getInstance();
            Property p = item.getItemProperty(propertyId);
            for (Varos v : service.getAllVaros()) {
                selectVaros.addItem(v);
                selectVaros.setItemCaption(v, v.getVarosnev() + " (" + v.getNemzet().getOrszagnev() + ")");
                
                if (v.equals(((Varos)p.getValue()))) {
                    selectVaros.setValue(v);
                }
            }
            
            return selectVaros;
        } else if (pid.equals(Constants.REPULO_POJO)) {
            Select selectRepulo = new Select(Constants.REPULO_POJO);
            DatabaseService service = DatabaseService.getInstance();
            Property p = item.getItemProperty(propertyId);
            
            for (Repulo r : service.getAllRepulo()) {
                selectRepulo.addItem(r);
                selectRepulo.setItemCaption(r, r.getTipus() + " (" + r.getTarsasag().getTarsasagNev() + ")");
                
                if (r.equals(((Repulo)p.getValue()))) {
                    selectRepulo.setValue(r);
                }
            }
            
            return selectRepulo;
        }
        
        return null;
    }

}
