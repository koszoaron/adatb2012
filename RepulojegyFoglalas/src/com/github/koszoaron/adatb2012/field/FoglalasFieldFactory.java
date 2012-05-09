package com.github.koszoaron.adatb2012.field;

import com.github.koszoaron.adatb2012.pojo.Felhasznalo;
import com.github.koszoaron.adatb2012.pojo.Jegy;
import com.github.koszoaron.adatb2012.util.Constants;
import com.github.koszoaron.adatb2012.util.DatabaseService;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.Select;

public class FoglalasFieldFactory implements FormFieldFactory {
    private static final long serialVersionUID = 1L;

    public Field createField(Item item, Object propertyId, Component uiContext) {
        String pid = (String)propertyId;
        
        if (pid.equals(Constants.FELHASZNALO_POJO)) {
            Select selectFelhasznalo = new Select(Constants.FELHASZNALO_POJO);
            DatabaseService service = DatabaseService.getInstance();
            Property p = item.getItemProperty(propertyId);
            for (Felhasznalo f : service.getAllFelhasznalo()) {
                selectFelhasznalo.addItem(f);
                selectFelhasznalo.setItemCaption(f, f.getUsername() + " (" + f.getNeve() + ")");
                
                if (f.equals(((Felhasznalo)p.getValue()))) {
                    selectFelhasznalo.setValue(f);
                }
            }
            
            return selectFelhasznalo;
        } else if (pid.equals(Constants.JEGY_POJO)) {
            Select selectJegy = new Select(Constants.JEGY_POJO);
            DatabaseService service = DatabaseService.getInstance();
            Property p = item.getItemProperty(propertyId);
            for (Jegy j : service.getAllJegy()) {
                selectJegy.addItem(j);
                selectJegy.setItemCaption(j, j.getJegyId() + " (" + j.getJarat().getHonnan().getVarosnev() + " - " + j.getJarat().getHova().getVarosnev() + " - " + j.getOsztaly().getSzama() + ". osztály)");
                
                if (j.equals(((Jegy)p.getValue()))) {
                    selectJegy.setValue(j);
                }
            }
            
            return selectJegy;
        }
        return null;
    }

}
