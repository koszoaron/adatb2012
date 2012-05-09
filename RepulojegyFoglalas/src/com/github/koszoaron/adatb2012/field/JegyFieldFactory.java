package com.github.koszoaron.adatb2012.field;

import com.github.koszoaron.adatb2012.pojo.Biztositas;
import com.github.koszoaron.adatb2012.pojo.Jarat;
import com.github.koszoaron.adatb2012.pojo.Osztaly;
import com.github.koszoaron.adatb2012.util.Constants;
import com.github.koszoaron.adatb2012.util.DatabaseService;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;

public class JegyFieldFactory implements FormFieldFactory {
    private static final long serialVersionUID = 1L;

    public Field createField(Item item, Object propertyId, Component uiContext) {
        String pid = (String)propertyId;
        
        if (pid.equals(Constants.JEGY_ID_POJO)) {
            return new TextField(Constants.JEGY_ID_POJO);
        } else if (pid.equals(Constants.JARAT_POJO)) {
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
        } else if (pid.equals(Constants.OSZTALY_POJO)) {
            Select selectOsztaly = new Select(Constants.OSZTALY_POJO);
            DatabaseService service = DatabaseService.getInstance();
            Property p = item.getItemProperty(propertyId);
            for (Osztaly o : service.getAllOsztaly()) {
                selectOsztaly.addItem(o);
                selectOsztaly.setItemCaption(o, o.getSzama() + "");
                
                if (o.equals(((Osztaly)p.getValue()))) {
                    selectOsztaly.setValue(o);
                }
            }
            
            return selectOsztaly;
        } else if (pid.equals(Constants.BIZTOSITAS_POJO)) {
            Select selectBiztositas = new Select(Constants.BIZTOSITAS_POJO);
            DatabaseService service = DatabaseService.getInstance();
            Property p = item.getItemProperty(propertyId);
            for (Biztositas b : service.getAllBiztositas()) {
                selectBiztositas.addItem(b);
                selectBiztositas.setItemCaption(b, b.getBiztId() + "");
                
                if (b.equals(((Biztositas)p.getValue()))) {
                    selectBiztositas.setValue(b);
                }
            }
            
            return selectBiztositas;
        }
        
        return null;
    }

}
