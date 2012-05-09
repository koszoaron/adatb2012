package com.github.koszoaron.adatb2012.field;

import com.github.koszoaron.adatb2012.pojo.Jarat;
import com.github.koszoaron.adatb2012.util.Constants;
import com.github.koszoaron.adatb2012.util.DatabaseService;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.Select;

public class MenetrendFieldFactory implements FormFieldFactory {
    private static final long serialVersionUID = 1L;

    public Field createField(Item item, Object propertyId, Component uiContext) {
        String pid = (String)propertyId;
        
        if (pid.equals(Constants.JARAT_POJO)) {
            Select selectJarat = new Select(Constants.JARAT_POJO);
            DatabaseService service = DatabaseService.getInstance();
            Property p = item.getItemProperty(propertyId);
            
            for (Jarat j : service.getAllJarat()) {
                selectJarat.addItem(j);
                selectJarat.setItemCaption(j, j.getHonnan().getVarosnev() + " - " + j.getHova().getVarosnev());
                
                if (j.equals(((Jarat)p.getValue()))) {
                    selectJarat.setValue(j);
                }
            }
            
            return selectJarat;
        } else if (pid.equals(Constants.INDUL)) {
            DateField df = new DateField(Constants.INDUL);
            df.setDateFormat("yyyy-MM-dd HH:mm");
            return df;
        } else if (pid.equals(Constants.ERKEZIK)) {
            DateField df = new DateField(Constants.ERKEZIK);
            df.setDateFormat("yyyy-MM-dd HH:mm");
            return df;
        }
        
        return null;
    }

}
