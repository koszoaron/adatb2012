package com.github.koszoaron.adatb2012;

import java.util.Vector;
import com.github.koszoaron.adatb2012.field.AkcioFieldFactory;
import com.github.koszoaron.adatb2012.field.BiztositasFieldFactory;
import com.github.koszoaron.adatb2012.field.FelhasznaloFieldFactory;
import com.github.koszoaron.adatb2012.field.FoglalasFieldFactory;
import com.github.koszoaron.adatb2012.field.JaratFieldFactory;
import com.github.koszoaron.adatb2012.field.JegyFieldFactory;
import com.github.koszoaron.adatb2012.field.MenetrendFieldFactory;
import com.github.koszoaron.adatb2012.field.NemzetFieldFactory;
import com.github.koszoaron.adatb2012.field.OsztalyFieldFactory;
import com.github.koszoaron.adatb2012.field.RepuloFieldFactory;
import com.github.koszoaron.adatb2012.field.SzallodaFieldFactory;
import com.github.koszoaron.adatb2012.field.TarsasagFieldFactory;
import com.github.koszoaron.adatb2012.field.VarosFieldFactory;
import com.github.koszoaron.adatb2012.pojo.Akcio;
import com.github.koszoaron.adatb2012.pojo.Biztositas;
import com.github.koszoaron.adatb2012.pojo.Felhasznalo;
import com.github.koszoaron.adatb2012.pojo.Foglalas;
import com.github.koszoaron.adatb2012.pojo.Jarat;
import com.github.koszoaron.adatb2012.pojo.Jegy;
import com.github.koszoaron.adatb2012.pojo.Menetrend;
import com.github.koszoaron.adatb2012.pojo.Nemzet;
import com.github.koszoaron.adatb2012.pojo.Osztaly;
import com.github.koszoaron.adatb2012.pojo.Repulo;
import com.github.koszoaron.adatb2012.pojo.Szalloda;
import com.github.koszoaron.adatb2012.pojo.Tarsasag;
import com.github.koszoaron.adatb2012.pojo.Varos;
import com.github.koszoaron.adatb2012.util.Constants;
import com.github.koszoaron.adatb2012.util.DatabaseService;
import com.vaadin.Application;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.LoginForm.LoginEvent;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.LoginForm.LoginListener;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class RepulojegyfoglalasApplication extends Application {
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private static int currentTable;
    
    private Table elementList = new Table();
    private VerticalLayout editorLayout = new VerticalLayout();
    private Form elementEditor = new Form();
    private Form registrationForm;
    private Button btnAdd;
    private Button btnDelete;
    private Button btnRefresh;
    
    private Window mainWindow;
    private Window loginWindow;
    private Window registrationWindow;
    private Window userWindow;
    private Window adminWindow;
    
    private BeanItemContainer<Felhasznalo> felhasznalok;
    private BeanItemContainer<Nemzet> nemzetek;
    private BeanItemContainer<Tarsasag> tarsasagok;
    private BeanItemContainer<Varos> varosok;
    private BeanItemContainer<Repulo> repulok;
    private BeanItemContainer<Jarat> jaratok;
    private BeanItemContainer<Menetrend> menetrendek;
    private BeanItemContainer<Osztaly> osztalyok;
    private BeanItemContainer<Szalloda> szallodak;
    private BeanItemContainer<Biztositas> biztositasok;
    private BeanItemContainer<Akcio> akciok;
    private BeanItemContainer<Jegy> jegyek;
    private BeanItemContainer<Foglalas> foglalasok;
    private DatabaseService service;
    
    private boolean isNewItem = false;

    @Override
	public void init() {
        service = DatabaseService.getInstance();
        
        elementEditor.setWriteThrough(false);  //enable buffering
        
        felhasznalok = new BeanItemContainer<Felhasznalo>(Felhasznalo.class);
        nemzetek = new BeanItemContainer<Nemzet>(Nemzet.class);
        tarsasagok = new BeanItemContainer<Tarsasag>(Tarsasag.class);
        varosok = new BeanItemContainer<Varos>(Varos.class);
        repulok = new BeanItemContainer<Repulo>(Repulo.class);
        jaratok = new BeanItemContainer<Jarat>(Jarat.class);
        menetrendek = new BeanItemContainer<Menetrend>(Menetrend.class);
        osztalyok = new BeanItemContainer<Osztaly>(Osztaly.class);
        szallodak = new BeanItemContainer<Szalloda>(Szalloda.class);
        biztositasok = new BeanItemContainer<Biztositas>(Biztositas.class);
        akciok = new BeanItemContainer<Akcio>(Akcio.class);
        jegyek = new BeanItemContainer<Jegy>(Jegy.class);
        foglalasok = new BeanItemContainer<Foglalas>(Foglalas.class);
        
        initWindows();
        
        mainWindow.addWindow(loginWindow);
        
        setMainWindow(mainWindow);
        setListeners();
	}
        
    private void loadData(int type) {
        Container container = null;
        String [] cols = null;
        FormFieldFactory fieldFactory = null;
        currentTable = type;
        
        switch (type) {
            case Constants.TABLE_FELHASZNALO:
                container = felhasznalok;
                cols = Constants.COLS_FELHASZNALO;
                fieldFactory = new FelhasznaloFieldFactory();
                
                felhasznalok.removeAllItems();
                for (Felhasznalo f : service.getAllFelhasznalo()) {
                    if (f != null) {
                        felhasznalok.addBean(f);
                    }
                }
                break;
            case Constants.TABLE_NEMZET:
                container = nemzetek;
                cols = Constants.COLS_NEMZET;
                fieldFactory = new NemzetFieldFactory();
                
                nemzetek.removeAllItems();
                for (Nemzet n : service.getAllNemzet()) {
                    if (n != null) {
                        nemzetek.addBean(n);
                    }
                }
                break;
            case Constants.TABLE_TARSASAG:
                container = tarsasagok;
                cols = Constants.COLS_TARSASAG;
                fieldFactory = new TarsasagFieldFactory();
                
                tarsasagok.removeAllItems();
                for (Tarsasag t : service.getAllTarsasag()) {
                    if (t != null) {
                        tarsasagok.addBean(t);
                    }
                }
                        
                break;
            case Constants.TABLE_VAROS:
                container = varosok;
                cols = Constants.COLS_VAROS;
                fieldFactory = new VarosFieldFactory();
                
                varosok.removeAllItems();
                for (Varos v : service.getAllVaros()) {
                    if (v != null) {
                        varosok.addBean(v);
                    }
                }
                        
                break;
            case Constants.TABLE_REPULO:
                container = repulok;
                cols = Constants.COLS_REPULO;
                fieldFactory = new RepuloFieldFactory();
                
                repulok.removeAllItems();
                for (Repulo r : service.getAllRepulo()) {
                    if (r != null) {
                        repulok.addBean(r);
                    }
                }
                        
                break;
            case Constants.TABLE_JARAT:
                container = jaratok;
                cols = Constants.COLS_JARAT;
                fieldFactory = new JaratFieldFactory();
                
                jaratok.removeAllItems();
                for (Jarat j : service.getAllJarat()) {
                    if (j != null) {
                        jaratok.addBean(j);
                    }
                }
                        
                break;
            case Constants.TABLE_MENETREND:
                container = menetrendek;
                cols = Constants.COLS_MENETREND;
                fieldFactory = new MenetrendFieldFactory();
                
                menetrendek.removeAllItems();
                for (Menetrend m : service.getAllMenetrend()) {
                    if (m != null) {
                        menetrendek.addBean(m);
                    }
                }
                        
                break;
            case Constants.TABLE_OSZTALY:
                container = osztalyok;
                cols = Constants.COLS_OSZTALY;
                fieldFactory = new OsztalyFieldFactory();
                
                osztalyok.removeAllItems();
                for (Osztaly o : service.getAllOsztaly()) {
                    if (o != null) {
                        osztalyok.addBean(o);
                    }
                }
                        
                break;
            case Constants.TABLE_SZALLODA:
                container = szallodak;
                cols = Constants.COLS_SZALLODA;
                fieldFactory = new SzallodaFieldFactory();
                
                szallodak.removeAllItems();
                for (Szalloda sz : service.getAllSzalloda()) {
                    if (sz != null) {
                        szallodak.addBean(sz);
                    }
                }
                        
                break;
            case Constants.TABLE_BIZTOSITAS:
                container = biztositasok;
                cols = Constants.COLS_BIZTOSITAS;
                fieldFactory = new BiztositasFieldFactory();
                
                biztositasok.removeAllItems();
                for (Biztositas b : service.getAllBiztositas()) {
                    if (b != null) {
                        biztositasok.addBean(b);
                    }
                }
                        
                break;
            case Constants.TABLE_AKCIO:
                container = akciok;
                cols = Constants.COLS_AKCIO;
                fieldFactory = new AkcioFieldFactory();
                
                akciok.removeAllItems();
                for (Akcio a : service.getAllAkcio()) {
                    if (a != null) {
                        akciok.addBean(a);
                    }
                }
                        
                break;
            case Constants.TABLE_JEGY:
                container = jegyek;
                cols = Constants.COLS_JEGY;
                fieldFactory = new JegyFieldFactory();
                
                jegyek.removeAllItems();
                for (Jegy j : service.getAllJegy()) {
                    if (j != null) {
                        jegyek.addBean(j);
                    }
                }
                        
                break;
            case Constants.TABLE_FOGLALAS:
                container = foglalasok;
                cols = Constants.COLS_FOGLALAS;
                fieldFactory = new FoglalasFieldFactory();
                
                foglalasok.removeAllItems();
                for (Foglalas f : service.getAllFoglalas()) {
                    if (f != null) {
                        foglalasok.addBean(f);
                    }
                }
                        
                break;
        }
        
        elementEditor.setFormFieldFactory(fieldFactory);
        elementList.setContainerDataSource(container);
        elementList.setVisibleColumns(cols);
        elementList.setSelectable(true);
        elementList.setImmediate(true);
        elementList.setSortAscending(true);
        elementList.addListener(new Property.ValueChangeListener() {
            private static final long serialVersionUID = 1L;

            public void valueChange(ValueChangeEvent event) {
                Object id = elementList.getValue();
                elementEditor.setItemDataSource(id == null ? null : elementList.getItem(id));
                editorLayout.setVisible(id != null);
                btnDelete.setEnabled(true);
            }
        });
    }
    
    private void setListeners() {
        if (btnRefresh != null) {
            btnRefresh.addListener(new ClickListener() {
                private static final long serialVersionUID = 1L;

                public void buttonClick(ClickEvent event) {
                    loadData(currentTable);
                }
            });
        }
        
        if (btnAdd != null) {
            btnAdd.addListener(new ClickListener() {
                private static final long serialVersionUID = 1L;

                public void buttonClick(ClickEvent event) {
                    switch (currentTable) {
                        case Constants.TABLE_FELHASZNALO:
                            BeanItem<Felhasznalo> newFelhasznalo = felhasznalok.addBean(new Felhasznalo());
                            elementEditor.setItemDataSource(newFelhasznalo);
                            
                            break;
                        case Constants.TABLE_NEMZET:
                            BeanItem<Nemzet> newNemzet = nemzetek.addBean(new Nemzet());
                            elementEditor.setItemDataSource(newNemzet);
                            
                            break;
                        case Constants.TABLE_TARSASAG:
                            BeanItem<Tarsasag> newTarsasag = tarsasagok.addBean(new Tarsasag());
                            elementEditor.setItemDataSource(newTarsasag);
                            
                            break;
                        case Constants.TABLE_VAROS:
                            BeanItem<Varos> newVaros = varosok.addBean(new Varos());
                            elementEditor.setItemDataSource(newVaros);
                            
                            break;
                        case Constants.TABLE_REPULO:
                            BeanItem<Repulo> newRepulo = repulok.addBean(new Repulo());
                            elementEditor.setItemDataSource(newRepulo);
                            
                            break;
                        case Constants.TABLE_JARAT:
                            BeanItem<Jarat> newJarat = jaratok.addBean(new Jarat());
                            elementEditor.setItemDataSource(newJarat);
                            
                            break;
                        case Constants.TABLE_MENETREND:
                            BeanItem<Menetrend> newMenetrend = menetrendek.addBean(new Menetrend());
                            elementEditor.setItemDataSource(newMenetrend);
                            
                            break;
                        case Constants.TABLE_OSZTALY:
                            BeanItem<Osztaly> newOsztaly = osztalyok.addBean(new Osztaly());
                            elementEditor.setItemDataSource(newOsztaly);
                            
                            break;
                        case Constants.TABLE_SZALLODA:
                            BeanItem<Szalloda> newSzalloda = szallodak.addBean(new Szalloda());
                            elementEditor.setItemDataSource(newSzalloda);
                            
                            break;
                        case Constants.TABLE_BIZTOSITAS:
                            BeanItem<Biztositas> newBiztositas = biztositasok.addBean(new Biztositas());
                            elementEditor.setItemDataSource(newBiztositas);
                            
                            break;
                        case Constants.TABLE_AKCIO:
                            BeanItem<Akcio> newAkcio = akciok.addBean(new Akcio());
                            elementEditor.setItemDataSource(newAkcio);
                            
                            break;
                        case Constants.TABLE_JEGY:
                            BeanItem<Jegy> newJegy = jegyek.addBean(new Jegy());
                            elementEditor.setItemDataSource(newJegy);
                            
                            break;
                        case Constants.TABLE_FOGLALAS:
                            BeanItem<Foglalas> newFoglalas = foglalasok.addBean(new Foglalas());
                            elementEditor.setItemDataSource(newFoglalas);
                            
                            break;
                    }
                    
                    if (!editorLayout.isVisible()) {
                        editorLayout.setVisible(true);
                    }
                    isNewItem = true;
                }
            });
        }
        
        if (btnDelete != null) {
            btnDelete.addListener(new ClickListener() {
                private static final long serialVersionUID = 1L;

                public void buttonClick(ClickEvent event) {
                    Object id = elementList.getValue();
                    if (id == null) {
                        return;
                    }
                    
                    switch (currentTable) {
                        case Constants.TABLE_FELHASZNALO:
                            Felhasznalo f = felhasznalok.getItem(id).getBean();
                            service.deleteFelhasznalo(f);
                            break;
                        case Constants.TABLE_NEMZET:
                            Nemzet n = nemzetek.getItem(id).getBean();
                            service.deleteNemzet(n);
                            break;
                        case Constants.TABLE_TARSASAG:
                            Tarsasag t = tarsasagok.getItem(id).getBean();
                            service.deleteTarsasag(t);                           
                            break;
                        case Constants.TABLE_VAROS:
                            Varos v = varosok.getItem(id).getBean();
                            service.deleteVaros(v);                            
                            break;
                        case Constants.TABLE_REPULO:
                            Repulo r = repulok.getItem(id).getBean();
                            service.deleteRepulo(r);
                            break;
                        case Constants.TABLE_JARAT:
                            Jarat j = jaratok.getItem(id).getBean();
                            service.deleteJarat(j);
                            break;
                        case Constants.TABLE_MENETREND:
                            Menetrend m = menetrendek.getItem(id).getBean();
                            service.deleteMenetrend(m);
                            break;
                        case Constants.TABLE_OSZTALY:
                            Osztaly o = osztalyok.getItem(id).getBean();
                            service.deleteOsztaly(o);
                            break;
                        case Constants.TABLE_SZALLODA:
                            Szalloda sz = szallodak.getItem(id).getBean();
                            service.deleteSzalloda(sz);
                            break;
                        case Constants.TABLE_BIZTOSITAS:
                            Biztositas b = biztositasok.getItem(id).getBean();
                            service.deleteBiztositas(b);                            
                            break;
                        case Constants.TABLE_AKCIO:
                            Akcio a = akciok.getItem(id).getBean();
                            service.deleteAkcio(a);
                            break;
                        case Constants.TABLE_JEGY:
                            Jegy je = jegyek.getItem(id).getBean();
                            service.deleteJegy(je);
                            break;
                        case Constants.TABLE_FOGLALAS:
                            Foglalas fo = foglalasok.getItem(id).getBean();
                            service.deleteFoglalas(fo);
                            break;
                    }
                    btnDelete.setEnabled(false);
                    loadData(currentTable);
                }
            });
        }
    }
    
    private void saveData(Object id) {
        if (id != null) {
            switch (currentTable) {
                case Constants.TABLE_FELHASZNALO:
                    Felhasznalo f = felhasznalok.getItem(id).getBean();
                    
                    if (isNewItem) {
                        service.insertFelhasznalo(f);
                    } else {
                        service.updateFelhasznalo(f);
                    }
                    
                    break;
                case Constants.TABLE_NEMZET:
                    Nemzet n = nemzetek.getItem(id).getBean();
                    
                    if (isNewItem) {
                        service.insertNemzet(n);
                    } else {
                        service.updateNemzet(n);
                    }
                    break;
                case Constants.TABLE_TARSASAG:
                    Tarsasag t = tarsasagok.getItem(id).getBean();
                    
                    if (isNewItem) {
                        service.insertTarsasag(t);
                    } else {
                        service.updateTarsasag(t);
                    }
                    break;
                case Constants.TABLE_VAROS:
                    Varos v = varosok.getItem(id).getBean();
                    
                    if (isNewItem) {
                        service.insertVaros(v);
                    } else {
                        service.updateVaros(v);
                    }
                    break;
                case Constants.TABLE_REPULO:
                    Repulo r = repulok.getItem(id).getBean();
                    
                    if (isNewItem) {
                        service.insertRepulo(r);
                    } else {
                        service.updateRepulo(r);
                    }
                    break;
                case Constants.TABLE_JARAT:
                    Jarat j = jaratok.getItem(id).getBean();
                    
                    if (isNewItem) {
                        service.insertJarat(j);
                    } else {
                        service.updateJarat(j);
                    }
                    break;
                case Constants.TABLE_MENETREND:
                    Menetrend m = menetrendek.getItem(id).getBean();
                    
                    if (isNewItem) {
                        service.insertMenetrend(m);
                    } else {
                        service.updateMenetrend(m);
                    }
                    break;
                case Constants.TABLE_OSZTALY:
                    Osztaly o = osztalyok.getItem(id).getBean();
                    
                    if (isNewItem) {
                        service.insertOsztaly(o);
                    } else {
                        service.updateOsztaly(o);
                    }
                    break;
                case Constants.TABLE_SZALLODA:
                    Szalloda sz = szallodak.getItem(id).getBean();
                    
                    if (isNewItem) {
                        service.insertSzalloda(sz);
                    } else {
                        service.updateSzalloda(sz);
                    }                    
                    break;
                case Constants.TABLE_BIZTOSITAS:
                    Biztositas b = biztositasok.getItem(id).getBean();
                    
                    if (isNewItem) {
                        service.insertBiztositas(b);
                    } else {
                        service.updateBiztositas(b);
                    }
                    break;
                case Constants.TABLE_AKCIO:
                    Akcio a = akciok.getItem(id).getBean();
                    
                    if (isNewItem) {
                        service.insertAkcio(a);
                    } else {
                        service.updateAkcio(a);
                    }
                    break;
                case Constants.TABLE_JEGY:
                    Jegy je = jegyek.getItem(id).getBean();
                    
                    if (isNewItem) {
                        service.insertJegy(je);
                    } else {
                        service.updateJegy(je);
                    }
                    break;
                case Constants.TABLE_FOGLALAS:
                    Foglalas fo = foglalasok.getItem(id).getBean();
                    
                    if (isNewItem) {
                        service.insertFoglalas(fo);
                    } else {
                        service.updateFoglalas(fo);
                    }
                    break;
            }
            loadData(currentTable);
        }
    }
    
    private void showNotification(String message) {
        Window.Notification n = new Window.Notification("", message, Window.Notification.TYPE_HUMANIZED_MESSAGE);
        n.setDelayMsec(Window.Notification.DELAY_FOREVER);
        getMainWindow().showNotification(n);
    }
    
    private void showNotification(String message, String title) {
        Window.Notification n = new Window.Notification(title, message, Window.Notification.TYPE_HUMANIZED_MESSAGE);
        n.setDelayMsec(Window.Notification.DELAY_FOREVER);
        getMainWindow().showNotification(n);
    }
    
    private void showWarning(String message) {
        Window.Notification n = new Window.Notification("", message, Window.Notification.TYPE_WARNING_MESSAGE);
        n.setDelayMsec(Window.Notification.DELAY_FOREVER);
        getMainWindow().showNotification(n);
    }
    
    private void showWarning(String message, String title) {
        Window.Notification n = new Window.Notification(title, message, Window.Notification.TYPE_WARNING_MESSAGE);
        n.setDelayMsec(Window.Notification.DELAY_FOREVER);
        getMainWindow().showNotification(n);
    }
    
    private void showError(String message) {
        Window.Notification n = new Window.Notification("", message, Window.Notification.TYPE_ERROR_MESSAGE);
        n.setDelayMsec(Window.Notification.DELAY_FOREVER);
        getMainWindow().showNotification(n);
    }
    
    private void showError(String message, String title) {
        Window.Notification n = new Window.Notification(title, message, Window.Notification.TYPE_ERROR_MESSAGE);
        n.setDelayMsec(Window.Notification.DELAY_FOREVER);
        getMainWindow().showNotification(n);
    }
    
    private void initWindows() {
        mainWindow = new Window(Constants.APP_NAME_STRING);
        
        loginWindow = new Window(Constants.WINDOW_LOGIN_CAPS, initLoginLayout());
        loginWindow.setClosable(false);
        loginWindow.setResizable(false);
        loginWindow.setModal(true);
        loginWindow.setDraggable(false);
        
        registrationWindow = new Window(Constants.WINDOW_REG_CAPS, initRegistrationLayout());
        registrationWindow.setResizable(false);
        registrationWindow.setModal(true);
        registrationWindow.setDraggable(false);
        
        adminWindow = new Window(Constants.WINDOW_ADMIN_CAPS, initAdminLayout());
        adminWindow.setClosable(false);
        adminWindow.setResizable(false);
        adminWindow.setModal(true);
        adminWindow.setDraggable(false);
        
        userWindow = new Window(Constants.WINDOW_USER_CAPS);
        userWindow.setClosable(false);
        userWindow.setResizable(false);
        userWindow.setModal(true);
        userWindow.setDraggable(false);
    }
    
    private AbsoluteLayout initAdminLayout() {
        AbsoluteLayout mainLayout = new AbsoluteLayout();
        mainLayout.setImmediate(false);
        mainLayout.setWidth("900px");
        mainLayout.setHeight("600px");
        mainLayout.setMargin(false);
        
        //setMainWindow(new Window(Constants.APP_NAME_STRING + " - Admin interface", mainLayout));
        
        // verticalLayout_main
        VerticalLayout verticalLayout_main = new VerticalLayout();
        verticalLayout_main.setImmediate(false);
        verticalLayout_main.setWidth("100.0%");
        verticalLayout_main.setHeight("100.0%");
        verticalLayout_main.setMargin(false);
        
        // topBar
        HorizontalLayout topBar = new HorizontalLayout();
        topBar.setImmediate(false);
        topBar.setWidth("100.0%");
        topBar.setHeight("-1px");
        topBar.setMargin(false);
        
        // labelLogout
        VerticalLayout labelLogout = new VerticalLayout();
        labelLogout.setImmediate(false);
        labelLogout.setWidth("-1px");
        labelLogout.setHeight("-1px");
        labelLogout.setMargin(false);
        
        // button_logoff
        Button button_logoff = new Button();
        button_logoff.setCaption(Constants.BTN_LABEL_LOGOFF);
        button_logoff.setImmediate(false);
        button_logoff.setWidth("100.0%");
        button_logoff.setHeight("-1px");
        button_logoff.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                mainWindow.removeWindow(adminWindow);
                mainWindow.addWindow(loginWindow);
            }
        });
        labelLogout.addComponent(button_logoff);
        
        topBar.addComponent(labelLogout);
        
        // tableselectors
        VerticalLayout tableselectors = new VerticalLayout();
        tableselectors.setImmediate(false);
        tableselectors.setWidth("-1px");
        tableselectors.setHeight("-1px");
        tableselectors.setMargin(false);
        
        // horizontalLayout_topBar1
        HorizontalLayout horizontalLayout_topBar1 =  new HorizontalLayout();
        horizontalLayout_topBar1.setImmediate(false);
        horizontalLayout_topBar1.setWidth("100.0%");
        horizontalLayout_topBar1.setHeight("100.0%");
        horizontalLayout_topBar1.setMargin(false);
        
        // button_1
        Button button_1 = new Button();
        button_1.setCaption(Constants.BTN_LABEL_TABLE_FELHASZNALO);
        button_1.setImmediate(false);
        button_1.setWidth("-1px");
        button_1.setHeight("-1px");
        button_1.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                loadData(Constants.TABLE_FELHASZNALO);
            }
        });
        horizontalLayout_topBar1.addComponent(button_1);
        
        // button_2
        Button button_2 = new Button();
        button_2.setCaption(Constants.BTN_LABEL_TABLE_FOGLALAS);
        button_2.setImmediate(false);
        button_2.setWidth("-1px");
        button_2.setHeight("-1px");
        button_2.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                loadData(Constants.TABLE_FOGLALAS);
            }
        });
        horizontalLayout_topBar1.addComponent(button_2);
        
        // button_3
        Button button_3 = new Button();
        button_3.setCaption(Constants.BTN_LABEL_TABLE_NEMZET);
        button_3.setImmediate(false);
        button_3.setWidth("-1px");
        button_3.setHeight("-1px");
        button_3.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                loadData(Constants.TABLE_NEMZET);
            }
        });
        horizontalLayout_topBar1.addComponent(button_3);
        
        // button_4
        Button button_4 = new Button();
        button_4.setCaption(Constants.BTN_LABEL_TABLE_TARSASAG);
        button_4.setImmediate(false);
        button_4.setWidth("-1px");
        button_4.setHeight("-1px");
        button_4.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                loadData(Constants.TABLE_TARSASAG);
            }
        });
        horizontalLayout_topBar1.addComponent(button_4);
        
        // button_5
        Button button_5 = new Button();
        button_5.setCaption(Constants.BTN_LABEL_TABLE_VAROS);
        button_5.setImmediate(false);
        button_5.setWidth("-1px");
        button_5.setHeight("-1px");
        button_5.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                loadData(Constants.TABLE_VAROS);
            }
        });
        horizontalLayout_topBar1.addComponent(button_5);
        
        // button_6
        Button button_6 = new Button();
        button_6.setCaption(Constants.BTN_LABEL_TABLE_REPULO);
        button_6.setImmediate(false);
        button_6.setWidth("-1px");
        button_6.setHeight("-1px");
        button_6.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                loadData(Constants.TABLE_REPULO);
            }
        });
        horizontalLayout_topBar1.addComponent(button_6);
        
        tableselectors.addComponent(horizontalLayout_topBar1);
        
        // horizontalLayout_topBar2
        HorizontalLayout horizontalLayout_topBar2 = new HorizontalLayout();
        horizontalLayout_topBar2.setImmediate(false);
        horizontalLayout_topBar2.setWidth("100.0%");
        horizontalLayout_topBar2.setHeight("100.0%");
        horizontalLayout_topBar2.setMargin(false);
        
        // button_7
        Button button_7 = new Button();
        button_7.setCaption(Constants.BTN_LABEL_TABLE_JARAT);
        button_7.setImmediate(false);
        button_7.setWidth("-1px");
        button_7.setHeight("-1px");
        button_7.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                loadData(Constants.TABLE_JARAT);
            }
        });
        horizontalLayout_topBar2.addComponent(button_7);
        
        // button_8
        Button button_8 = new Button();
        button_8.setCaption(Constants.BTN_LABEL_TABLE_MENETREND);
        button_8.setImmediate(false);
        button_8.setWidth("-1px");
        button_8.setHeight("-1px");
        button_8.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                loadData(Constants.TABLE_MENETREND);
            }
        });
        horizontalLayout_topBar2.addComponent(button_8);
        
        // button_9
        Button button_9 = new Button();
        button_9.setCaption(Constants.BTN_LABEL_TABLE_OSZTALY);
        button_9.setImmediate(false);
        button_9.setWidth("-1px");
        button_9.setHeight("-1px");
        button_9.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                loadData(Constants.TABLE_OSZTALY);
            }
        });
        horizontalLayout_topBar2.addComponent(button_9);
        
        // button_10
        Button button_10 = new Button();
        button_10.setCaption(Constants.BTN_LABEL_TABLE_SZALLODA);
        button_10.setImmediate(false);
        button_10.setWidth("-1px");
        button_10.setHeight("-1px");
        button_10.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                loadData(Constants.TABLE_SZALLODA);
            }
        });
        horizontalLayout_topBar2.addComponent(button_10);
        
        // button_11
        Button button_11 = new Button();
        button_11.setCaption(Constants.BTN_LABEL_TABLE_BIZTOSITAS);
        button_11.setImmediate(false);
        button_11.setWidth("-1px");
        button_11.setHeight("-1px");
        button_11.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                loadData(Constants.TABLE_BIZTOSITAS);
            }
        });
        horizontalLayout_topBar2.addComponent(button_11);
        
        // button_12
        Button button_12 = new Button();
        button_12.setCaption(Constants.BTN_LABEL_TABLE_AKCIO);
        button_12.setImmediate(false);
        button_12.setWidth("-1px");
        button_12.setHeight("-1px");
        button_12.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                loadData(Constants.TABLE_AKCIO);
            }
        });
        horizontalLayout_topBar2.addComponent(button_12);
        
        // button_13
        Button button_13 = new Button();
        button_13.setCaption(Constants.BTN_LABEL_TABLE_JEGY);
        button_13.setImmediate(false);
        button_13.setWidth("-1px");
        button_13.setHeight("-1px");
        button_13.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                loadData(Constants.TABLE_JEGY);
            }
        });
        horizontalLayout_topBar2.addComponent(button_13);
        
        tableselectors.addComponent(horizontalLayout_topBar2);
        
        topBar.addComponent(tableselectors);
        topBar.setComponentAlignment(tableselectors, new Alignment(48));
        
        verticalLayout_main.addComponent(topBar);
        
        // horizontalSplitPanel
        HorizontalSplitPanel horizontalSplitPanel = new HorizontalSplitPanel();
        horizontalSplitPanel.setImmediate(false);
        horizontalSplitPanel.setWidth("100.0%");
        horizontalSplitPanel.setHeight("100.0%");
        horizontalSplitPanel.setMargin(false);
        
        // left
        VerticalLayout left = new VerticalLayout();
        left.setImmediate(false);
        left.setWidth("100.0%");
        left.setHeight("100.0%");
        left.setMargin(false);
        
        // elementList
        elementList.setImmediate(false);
        elementList.setWidth("100.0%");
        elementList.setHeight("100.0%");
        left.addComponent(elementList);
        left.setExpandRatio(elementList, 1.0f);
        
        // bottomLeftCorner
        HorizontalLayout bottomLeftCorner = new HorizontalLayout();
        bottomLeftCorner.setImmediate(false);
        bottomLeftCorner.setWidth("100.0%");
        bottomLeftCorner.setHeight("-1px");
        bottomLeftCorner.setMargin(false);
        
        // btnRefresh
        btnRefresh = new Button();
        btnRefresh.setCaption(Constants.BTN_LABEL_REFRESH);
        btnRefresh.setImmediate(false);
        btnRefresh.setWidth("100.0%");
        btnRefresh.setHeight("-1px");
        bottomLeftCorner.addComponent(btnRefresh);
        bottomLeftCorner.setExpandRatio(btnRefresh, 1.0f);
        bottomLeftCorner.setComponentAlignment(btnRefresh, new Alignment(48));
        
        // btnAdd
        btnAdd = new Button();
        btnAdd.setCaption(Constants.BTN_LABEL_ADD);
        btnAdd.setImmediate(false);
        btnAdd.setWidth("100.0%");
        btnAdd.setHeight("-1px");
        bottomLeftCorner.addComponent(btnAdd);
        bottomLeftCorner.setExpandRatio(btnAdd, 1.0f);
        bottomLeftCorner.setComponentAlignment(btnAdd, new Alignment(48));
        
        // btnDelete
        btnDelete = new Button();
        btnDelete.setCaption(Constants.BTN_LABEL_REMOVE);
        btnDelete.setImmediate(false);
        btnDelete.setWidth("100.0%");
        btnDelete.setHeight("-1px");
        btnDelete.setEnabled(false);
        bottomLeftCorner.addComponent(btnDelete);
        bottomLeftCorner.setExpandRatio(btnDelete, 1.0f);
        bottomLeftCorner.setComponentAlignment(btnDelete, new Alignment(48));
        
        left.addComponent(bottomLeftCorner);
        
        horizontalSplitPanel.addComponent(left);
        
        // editorLayout
        elementEditor.setSizeFull();
        elementEditor.getLayout().setMargin(true);
        elementEditor.setImmediate(true);
        
        HorizontalLayout buttonHolder = new HorizontalLayout();
        buttonHolder.addComponent(new Button(Constants.BTN_LABEL_SAVE, new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                elementEditor.commit();
                saveData(elementList.getValue());
                isNewItem = false;
            }
        }));
        buttonHolder.addComponent(new Button(Constants.BTN_LABEL_CANCEL, new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                elementEditor.discard();
                isNewItem = false;
            }
        }));
        
        editorLayout.addComponent(elementEditor);
        editorLayout.addComponent(buttonHolder);
        editorLayout.setVisible(false);
        
        horizontalSplitPanel.addComponent(editorLayout);
        
        verticalLayout_main.addComponent(horizontalSplitPanel);
        verticalLayout_main.setExpandRatio(horizontalSplitPanel, 1.0f);
        
        mainLayout.addComponent(verticalLayout_main, "left:0.0px;");
    
        return mainLayout;
    }
    
    private AbsoluteLayout initLoginLayout() {
        AbsoluteLayout mainLayout = new AbsoluteLayout();
        mainLayout.setImmediate(false);
        mainLayout.setWidth("400px");
        mainLayout.setHeight("200px");
        mainLayout.setMargin(false);
        
        // horizontalLayout_2
        HorizontalLayout horizontalLayout_2 = new HorizontalLayout();
        horizontalLayout_2.setImmediate(false);
        horizontalLayout_2.setWidth("100.0%");
        horizontalLayout_2.setHeight("100.0%");
        horizontalLayout_2.setMargin(false);
        
        // verticalLayout_2
        VerticalLayout verticalLayout_2 = new VerticalLayout();
        verticalLayout_2.setImmediate(false);
        verticalLayout_2.setWidth("100.0%");
        verticalLayout_2.setHeight("100.0%");
        verticalLayout_2.setMargin(true);
        
        // Reg button
        Button button_3 = new Button();
        button_3.setCaption("Regisztrálok");
        button_3.setImmediate(false);
        button_3.setWidth("80.0%");
        button_3.setHeight("-1px");
        button_3.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                mainWindow.addWindow(registrationWindow);
                registrationForm.discard(); //nemjo
            }
        });
        verticalLayout_2.addComponent(button_3);
        verticalLayout_2.setComponentAlignment(button_3, new Alignment(33));
        
        horizontalLayout_2.addComponent(verticalLayout_2);
        horizontalLayout_2.setExpandRatio(verticalLayout_2, 1.0f);
        
        VerticalLayout verticalLayout_1 = new VerticalLayout();
        verticalLayout_1.setImmediate(false);
        verticalLayout_1.setWidth("100.0%");
        verticalLayout_1.setHeight("100.0%");
        verticalLayout_1.setMargin(true);
        
        // label_1
        Label label_1 = new Label();
        label_1.setImmediate(false);
        label_1.setWidth("-1px");
        label_1.setHeight("-1px");
        label_1.setValue(Constants.FIELD_LABEL_USERNAME);
        verticalLayout_1.addComponent(label_1);
        
        // textField_1
        final TextField textField_1 = new TextField();
        textField_1.setImmediate(false);
        textField_1.setWidth("-1px");
        textField_1.setHeight("-1px");
        //textField_1.setSecret(false);
        verticalLayout_1.addComponent(textField_1);
        
        // label_2
        Label label_2 = new Label();
        label_2.setImmediate(false);
        label_2.setWidth("-1px");
        label_2.setHeight("-1px");
        label_2.setValue(Constants.FIELD_LABEL_PASS);
        verticalLayout_1.addComponent(label_2);
        
        // passwordField_1
        final PasswordField passwordField_1 = new PasswordField();
        passwordField_1.setImmediate(false);
        passwordField_1.setWidth("-1px");
        passwordField_1.setHeight("-1px");
        verticalLayout_1.addComponent(passwordField_1);
        
        // verticalLayout_3
        VerticalLayout verticalLayout_3 = new VerticalLayout();
        verticalLayout_3.setImmediate(false);
        verticalLayout_3.setWidth("100.0%");
        verticalLayout_3.setHeight("100.0%");
        verticalLayout_3.setMargin(false);
        
        // Login button
        Button button_1 = new Button();
        button_1.setCaption("Login");
        button_1.setImmediate(false);
        button_1.setWidth("100.0%");
        button_1.setHeight("-1px");
        button_1.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                boolean isRegOk = service.callRegisztralte((String)textField_1.getValue(), (String)passwordField_1.getValue());
                
                if (isRegOk) {
                    mainWindow.removeWindow(loginWindow);
                    mainWindow.addWindow(userWindow);
                } else {
                    showWarning("Hibás felhasználónév vagy jelszó", "Bejelentkezés");
                }
            }
        });
        verticalLayout_3.addComponent(button_1);
        verticalLayout_3.setComponentAlignment(button_1, new Alignment(48));
        
        // Admin button
        Button button_4 = new Button();
        button_4.setCaption("Admin");
        button_4.setImmediate(true);
        button_4.setWidth("100.0%");
        button_4.setHeight("-1px");
        button_4.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                boolean isRegOk = service.callRegisztralte((String)textField_1.getValue(), (String)passwordField_1.getValue());
                
                if (isRegOk) {
                    mainWindow.removeWindow(loginWindow);
                    mainWindow.addWindow(adminWindow);
                } else {
                    showWarning("Hibás felhasználónév vagy jelszó", "Bejelentkezés");
                }
            }
        });
        verticalLayout_3.addComponent(button_4);
        verticalLayout_3.setComponentAlignment(button_4, new Alignment(20));
        
        verticalLayout_1.addComponent(verticalLayout_3);
        verticalLayout_1.setExpandRatio(verticalLayout_3, 1.0f);
        
        horizontalLayout_2.addComponent(verticalLayout_1);
        horizontalLayout_2.setExpandRatio(verticalLayout_1, 1.0f);

        mainLayout.addComponent(horizontalLayout_2, "left:0.0px;");
        
        return mainLayout;
    }
    
    private AbsoluteLayout initRegistrationLayout() {
        AbsoluteLayout mainLayout = new AbsoluteLayout();
        mainLayout.setImmediate(false);
        mainLayout.setWidth("400px");
        mainLayout.setHeight("500px");
        mainLayout.setMargin(false);
        
        // verticalLayout_1
        VerticalLayout verticalLayout_1 = new VerticalLayout();
        verticalLayout_1.setImmediate(false);
        verticalLayout_1.setWidth("100.0%");
        verticalLayout_1.setHeight("100.0%");
        verticalLayout_1.setMargin(true);
        verticalLayout_1.setSpacing(true);
        
        // registrationForm
        registrationForm = new Form();
        registrationForm.setStyleName("v-loginform");
        registrationForm.setImmediate(true);
        registrationForm.setWidth("-1px");
        registrationForm.setHeight("100.0%");
        
        registrationForm.setFormFieldFactory(new FelhasznaloFieldFactory());
        final BeanItem<Felhasznalo> regFelhasznalo = new BeanItem<Felhasznalo>(new Felhasznalo("", "", "", "", null, 0, "", 0));
        registrationForm.setItemDataSource(regFelhasznalo);
        Vector<Object> regFormOrder = new Vector<Object>();
        regFormOrder.add(Constants.USERNAME);
        regFormOrder.add(Constants.PASS);
        regFormOrder.add(Constants.OKMANYSZAM);
        regFormOrder.add(Constants.NEVE);
        regFormOrder.add(Constants.SZULETETT);
        regFormOrder.add(Constants.BANKKARTYASZAM);
        regFormOrder.add(Constants.LAKCIM);
        regFormOrder.add(Constants.TELEFONSZAM);
        registrationForm.setVisibleItemProperties(regFormOrder);
        
        verticalLayout_1.addComponent(registrationForm);
        verticalLayout_1.setExpandRatio(registrationForm, 1.0f);
        verticalLayout_1.setComponentAlignment(registrationForm, new Alignment(48));
        
        // horizontalLayout_1
        HorizontalLayout horizontalLayout_1 = new HorizontalLayout();
        horizontalLayout_1.setImmediate(false);
        horizontalLayout_1.setWidth("100.0%");
        horizontalLayout_1.setHeight("-1px");
        horizontalLayout_1.setMargin(false);
        
        // OK button
        Button button_1 = new Button();
        button_1.setCaption("OK");
        button_1.setImmediate(false);
        button_1.setWidth("100.0%");
        button_1.setHeight("-1px");
        button_1.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                //save form values
                registrationForm.commit();
                System.out.println(regFelhasznalo.getBean().toString());
                service.insertFelhasznalo(regFelhasznalo.getBean());
                
                mainWindow.removeWindow(registrationWindow);
            }
        });
        
        horizontalLayout_1.addComponent(button_1);
        horizontalLayout_1.setExpandRatio(button_1, 1.0f);
        horizontalLayout_1.setComponentAlignment(button_1, new Alignment(48));
        
        verticalLayout_1.addComponent(horizontalLayout_1);
        verticalLayout_1.setComponentAlignment(horizontalLayout_1,
                new Alignment(24));
        mainLayout.addComponent(verticalLayout_1, "left:0.0px;");
        
        return mainLayout;
    }
    
    private AbsoluteLayout initUserLayout() {
        return null;
    }
    
    @Override
    public void terminalError(com.vaadin.terminal.Terminal.ErrorEvent event) {
        super.terminalError(event);
        
        if (getMainWindow() != null) {
            showError(event.getThrowable().toString(), "Hiba történt!");
        }
    }
}
