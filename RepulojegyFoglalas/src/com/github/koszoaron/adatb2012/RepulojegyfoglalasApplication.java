package com.github.koszoaron.adatb2012;

import java.text.SimpleDateFormat;
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
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.LoginForm;
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
    private HorizontalLayout hl_myResDetails;
    private VerticalLayout vl_ResultDetails;
    private Form elementEditor = new Form();
    private Form registrationForm;
    private Form formUserEdit;
    private Form formTicket;
    private Label formBookings;
    private Label resultLabel;
    private Table table_Results;
    private Table table_myRes;
    private Button btnAdd;
    private Button btnDelete;
    private Button btnRefresh;
    private Button button_DeleteRes;
    private Button button_BookFlight;
    private Button button_CalcPrice;
    private Button button_payOK;
    private Label label_currUser;
    private Label label_Payment;
    
    private Window mainWindow;
    private Window loginWindow;
    private Window registrationWindow;
    private Window userWindow;
    private Window adminWindow;
    private Window ticketWindow;
    private Window paymentWindow;
    
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
    private Felhasznalo currentUser = null;
    private BeanItem<Felhasznalo> currentUserBean = null;
    private Jarat selectedJarat = null;

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
        
        userWindow = new Window(Constants.WINDOW_USER_CAPS, initUserLayout());
        userWindow.setClosable(false);
        userWindow.setResizable(false);
        userWindow.setModal(true);
        userWindow.setDraggable(false);
        
        ticketWindow = new Window("JEGY", initTicketCreator());
        ticketWindow.setResizable(false);
        ticketWindow.setModal(true);
        
        paymentWindow = new Window("FIZETÉS", initPaymentWindow());
        paymentWindow.setResizable(false);
        paymentWindow.setModal(true);
        paymentWindow.setClosable(false);
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
                currentUser = null;
                currentUserBean = null;
                mainWindow.removeWindow(adminWindow);
                mainWindow.addWindow(loginWindow);
            }
        });
        labelLogout.addComponent(button_logoff);
     
        // button_stats
        Button button_stats = new Button();
        button_stats.setCaption("Statisztikák");
        button_stats.setImmediate(false);
        button_stats.setWidth("100.0%");
        button_stats.setHeight("-1px");
        button_stats.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                int popJarat = service.callPopularis();
                Jarat pj = service.getJaratById(popJarat);
                showNotification("A legpopulárisabb járat: " + pj.getHonnan().getVarosnev() + " - " + pj.getHova().getVarosnev(), "Statisztikák");
            }
        });
        labelLogout.addComponent(button_stats);
        
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
                    currentUser = service.getFelhasznaloByUsername((String)textField_1.getValue());
                    currentUserBean = new BeanItem<Felhasznalo>(currentUser);
                    System.out.println("Current user: " + currentUser.getUsername());
                    mainWindow.removeWindow(loginWindow);
                    mainWindow.addWindow(userWindow);
                    
                    onUserLogon();
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
                    currentUser = service.getFelhasznaloByUsername((String)textField_1.getValue());
                    currentUserBean = new BeanItem<Felhasznalo>(currentUser);
                    System.out.println("Current user: " + currentUser.getUsername());
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
        AbsoluteLayout mainLayout = new AbsoluteLayout();
        mainLayout.setImmediate(false);
        mainLayout.setWidth("900px");
        mainLayout.setHeight("600px");
        mainLayout.setMargin(false);
        
        // vl_main
        VerticalLayout vl_main = new VerticalLayout();
        vl_main.setImmediate(false);
        vl_main.setWidth("100.0%");
        vl_main.setHeight("100.0%");
        vl_main.setMargin(true);
        
        // hl_top
        HorizontalLayout hl_top = new HorizontalLayout();
        hl_top.setImmediate(false);
        hl_top.setWidth("100.0%");
        hl_top.setHeight("-1px");
        hl_top.setMargin(true);
        
        // label_currUser
        label_currUser = new Label();
        label_currUser.setImmediate(false);
        label_currUser.setWidth("-1px");
        label_currUser.setHeight("-1px");
        hl_top.addComponent(label_currUser);
        
        // button_logout
        Button button_logout = new Button();
        button_logout.setCaption("Kijelentkezés");
        button_logout.setImmediate(true);
        button_logout.setWidth("-1px");
        button_logout.setHeight("-1px");
        button_logout.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                currentUser = null;
                currentUserBean = null;
                mainWindow.removeWindow(userWindow);
                mainWindow.addWindow(loginWindow);
            }
        });
        hl_top.addComponent(button_logout);
        hl_top.setComponentAlignment(button_logout, new Alignment(6));
        vl_main.addComponent(hl_top);
        
        // accordion_1
        Accordion accordion_1 = new Accordion();
        accordion_1.setImmediate(true);
        accordion_1.setWidth("100.0%");
        accordion_1.setHeight("100.0%");
        
        // vl_SearchAndList
        VerticalLayout vl_SearchAndList = new VerticalLayout();
        vl_SearchAndList.setImmediate(false);
        vl_SearchAndList.setWidth("100.0%");
        vl_SearchAndList.setHeight("100.0%");
        vl_SearchAndList.setMargin(true);
        
        // hl_SearchControls
        HorizontalLayout hl_SearchControls = new HorizontalLayout();
        hl_SearchControls.setImmediate(false);
        hl_SearchControls.setWidth("100.0%");
        hl_SearchControls.setHeight("-1px");
        hl_SearchControls.setMargin(false);
        hl_SearchControls.setSpacing(true);
        
        // txtSearch
        final TextField txtSearch = new TextField();
        txtSearch.setImmediate(false);
        txtSearch.setWidth("100.0%");
        txtSearch.setHeight("-1px");
        hl_SearchControls.addComponent(txtSearch);
        hl_SearchControls.setExpandRatio(txtSearch, 1.0f);
        
        // button_SearchFlights
        Button button_SearchFlights = new Button();
        button_SearchFlights.setCaption("Járatok keresése");
        button_SearchFlights.setImmediate(true);
        button_SearchFlights.setWidth("-1px");
        button_SearchFlights.setHeight("-1px");
        button_SearchFlights.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                Varos v = service.getVarosByName(txtSearch.getValue().toString());
                if (v != null) {
                    //final BeanItemContainer<Szalloda> hotels = new BeanItemContainer<Szalloda>(Szalloda.class, service.getSzallodaByVaros(v));
                    final BeanItemContainer<Jarat> flights = new BeanItemContainer<Jarat>(Jarat.class, service.getJaratByVaros(v));
                    
                    table_Results.setContainerDataSource(flights);
                    table_Results.setVisibleColumns(new String[] {Constants.HONNAN, Constants.HOVA});
                    table_Results.setSelectable(true);
                    table_Results.setImmediate(true);
                    table_Results.setSortAscending(true);
                    table_Results.addListener(new Property.ValueChangeListener() {
                        private static final long serialVersionUID = 1L;

                        public void valueChange(ValueChangeEvent event) {
                            Object id = table_Results.getValue();
                            vl_ResultDetails.setVisible(id != null);
                            
                            Jarat j = flights.getItem(id).getBean();
                            selectedJarat = j;
                            Menetrend m = service.getMenetrendByJarat(j.getJaratId());
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                            String datumI = "";
                            String datumE = "";
                            if (m != null) {
                                datumI = " (" + dateFormat.format(m.getIndul()) + ")";
                                datumE = " (" + dateFormat.format(m.getErkezik()) + ")";
                            }
                            
                            String toDisplay = j.getHonnan().getVarosnev() + datumI + " - " + j.getHova().getVarosnev() + datumE;
                            resultLabel.setValue(toDisplay);     
                            button_BookFlight.setVisible(true);
                        }
                    });
                }
            }
        });
        hl_SearchControls.addComponent(button_SearchFlights);
        
        // button_SearchHotels
        Button button_SearchHotels = new Button();
        button_SearchHotels.setCaption("Szállodák keresése");
        button_SearchHotels.setImmediate(true);
        button_SearchHotels.setWidth("-1px");
        button_SearchHotels.setHeight("-1px");
        button_SearchHotels.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                Varos v = service.getVarosByName(txtSearch.getValue().toString());
                if (v != null) {
                    final BeanItemContainer<Szalloda> hotels = new BeanItemContainer<Szalloda>(Szalloda.class, service.getSzallodaByVaros(v));
                    
                    table_Results.setContainerDataSource(hotels);
                    table_Results.setVisibleColumns(new String[] {Constants.NEVE_POJO});
                    table_Results.setSelectable(true);
                    table_Results.setImmediate(true);
                    table_Results.setSortAscending(true);
                    table_Results.addListener(new Property.ValueChangeListener() {
                        private static final long serialVersionUID = 1L;

                        public void valueChange(ValueChangeEvent event) {
                            Object id = table_Results.getValue();
                            vl_ResultDetails.setVisible(id != null);
                            
                            String toDisplay = "" + hotels.getItem(id).getBean().getLeiras();
                            resultLabel.setValue(toDisplay);     
                            button_BookFlight.setVisible(false);
                        }
                    });
                }
                
            }
        });
        hl_SearchControls.addComponent(button_SearchHotels);
        vl_SearchAndList.addComponent(hl_SearchControls);
        
        // hsp_Results
        HorizontalSplitPanel hsp_Results = new HorizontalSplitPanel();
        hsp_Results.setImmediate(false);
        hsp_Results.setWidth("100.0%");
        hsp_Results.setHeight("100.0%");
        hsp_Results.setMargin(false);
        
        // table_Results
        table_Results = new Table();
        table_Results.setImmediate(false);
        table_Results.setWidth("100.0%");
        table_Results.setHeight("100.0%");
        hsp_Results.addComponent(table_Results);
        
        // vl_ResultDetails
        vl_ResultDetails = new VerticalLayout();
        vl_ResultDetails.setImmediate(false);
        vl_ResultDetails.setWidth("100.0%");
        vl_ResultDetails.setHeight("100.0%");
        vl_ResultDetails.setMargin(true);
        vl_ResultDetails.setVisible(false);
        
        // loginForm_1
        resultLabel = new Label();
        resultLabel.setStyleName("v-loginform");
        resultLabel.setImmediate(false);
        resultLabel.setWidth("100.0%");
        resultLabel.setHeight("100.0%");
        vl_ResultDetails.addComponent(resultLabel);
        vl_ResultDetails.setExpandRatio(resultLabel, 1.0f);
        vl_ResultDetails.setComponentAlignment(resultLabel, new Alignment(48));
        
        // button_BookFlight
        button_BookFlight = new Button();
        button_BookFlight.setVisible(false);
        button_BookFlight.setCaption("Book Flight");
        button_BookFlight.setImmediate(true);
        button_BookFlight.setWidth("-1px");
        button_BookFlight.setHeight("-1px");
        button_BookFlight.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                //open new window with createJegy form
                openNewTicketWindow(selectedJarat);
                //create a new ticket
                //calculate the price
                //ask the user to pay
                    //new window - yes or no
                //if yes, create new booking, close windows
                //if no, close windows
            }
        });
        vl_ResultDetails.addComponent(button_BookFlight);
        vl_ResultDetails.setComponentAlignment(button_BookFlight, new Alignment(48));
        
        hsp_Results.addComponent(vl_ResultDetails);
        
        vl_SearchAndList.addComponent(hsp_Results);
        vl_SearchAndList.setExpandRatio(hsp_Results, 1.0f);
        
        accordion_1.addTab(vl_SearchAndList, "Search & List", null);
        
        // vl_Reservations
        VerticalLayout vl_Reservations = new VerticalLayout();
        vl_Reservations.setImmediate(false);
        vl_Reservations.setWidth("100.0%");
        vl_Reservations.setHeight("100.0%");
        vl_Reservations.setMargin(true);
        
        // table_myRes
        table_myRes = new Table();
        table_myRes.setImmediate(false);
        table_myRes.setWidth("100.0%");
        table_myRes.setHeight("100.0%");
        vl_Reservations.addComponent(table_myRes);
        vl_Reservations.setExpandRatio(table_myRes, 1.0f);
        
        // hl_myResDetails
        hl_myResDetails = new HorizontalLayout();
        hl_myResDetails.setImmediate(false);
        hl_myResDetails.setWidth("100.0%");
        hl_myResDetails.setHeight("100.0%");
        hl_myResDetails.setMargin(true);
        hl_myResDetails.setVisible(false);
        
        // loginForm_2
        formBookings = new Label();
        formBookings.setImmediate(false);
        formBookings.setWidth("100.0%");
        formBookings.setHeight("100.0%");
        hl_myResDetails.addComponent(formBookings);
        hl_myResDetails.setExpandRatio(formBookings, 1.0f);
        
        // vl_myResButtons
        VerticalLayout vl_myResButtons = new VerticalLayout();
        vl_myResButtons.setImmediate(false);
        vl_myResButtons.setWidth("-1px");
        vl_myResButtons.setHeight("100.0%");
        vl_myResButtons.setMargin(true);
        vl_myResButtons.setSpacing(true);
        
        // button_DeleteRes
        button_DeleteRes = new Button();
        button_DeleteRes.setCaption("Delete");
        button_DeleteRes.setImmediate(true);
        button_DeleteRes.setWidth("-1px");
        button_DeleteRes.setHeight("-1px");
        vl_myResButtons.addComponent(button_DeleteRes);
        vl_myResButtons.setComponentAlignment(button_DeleteRes, new Alignment(24));
        
        // button_ExchangeRes
        Button button_ExchangeRes = new Button();
        button_ExchangeRes.setCaption("Change");
        button_ExchangeRes.setImmediate(true);
        button_ExchangeRes.setWidth("-1px");
        button_ExchangeRes.setHeight("-1px");
        vl_myResButtons.addComponent(button_ExchangeRes);
        vl_myResButtons.setComponentAlignment(button_ExchangeRes, new Alignment(20));
        hl_myResDetails.addComponent(vl_myResButtons);
        vl_Reservations.addComponent(hl_myResDetails);
        vl_Reservations.setExpandRatio(hl_myResDetails, 1.0f);
        accordion_1.addTab(vl_Reservations, "Reservations", null);
        
        // vl_ModifyUser
        VerticalLayout vl_ModifyUser = new VerticalLayout();
        vl_ModifyUser.setImmediate(false);
        vl_ModifyUser.setWidth("100.0%");
        vl_ModifyUser.setHeight("100.0%");
        vl_ModifyUser.setMargin(true);
        
        // loginForm_3
        formUserEdit = new Form();
        formUserEdit.setStyleName("v-loginform");
        formUserEdit.setImmediate(false);
        formUserEdit.setWidth("100.0%");
        formUserEdit.setHeight("100.0%");
        formUserEdit.setFormFieldFactory(new FelhasznaloFieldFactory());
        
        vl_ModifyUser.addComponent(formUserEdit);
        vl_ModifyUser.setExpandRatio(formUserEdit, 1.0f);
        
        // hl_ModifyControls
        HorizontalLayout hl_ModifyControls = new HorizontalLayout();
        hl_ModifyControls.setImmediate(false);
        hl_ModifyControls.setWidth("-1px");
        hl_ModifyControls.setHeight("-1px");
        hl_ModifyControls.setMargin(false);
        
        // button_UserSave
        Button button_UserSave = new Button();
        button_UserSave.setCaption("Save");
        button_UserSave.setImmediate(true);
        button_UserSave.setWidth("-1px");
        button_UserSave.setHeight("-1px");
        button_UserSave.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                formUserEdit.commit();
                System.out.println(currentUserBean.getBean().toString());
                service.updateFelhasznalo(currentUserBean.getBean());
            }
        });
        hl_ModifyControls.addComponent(button_UserSave);
        
        // button_UserCancel
        Button button_UserCancel = new Button();
        button_UserCancel.setCaption("Cancel");
        button_UserCancel.setImmediate(true);
        button_UserCancel.setWidth("-1px");
        button_UserCancel.setHeight("-1px");
        button_UserCancel.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                currentUserBean = new BeanItem<Felhasznalo>(currentUser);
                formUserEdit.discard();
            }
        });
        hl_ModifyControls.addComponent(button_UserCancel);
        
        vl_ModifyUser.addComponent(hl_ModifyControls);
        
        accordion_1.addTab(vl_ModifyUser, "Modify User", null);
        
        vl_main.addComponent(accordion_1);
        vl_main.setExpandRatio(accordion_1, 1.0f);
        
        mainLayout.addComponent(vl_main, "top:0.0px;left:0.0px;");
        
        return mainLayout;
    }
    
    private AbsoluteLayout initTicketCreator() {
        AbsoluteLayout mainLayout = new AbsoluteLayout();
        mainLayout.setImmediate(false);
        mainLayout.setWidth("400px");
        mainLayout.setHeight("450px");
        mainLayout.setMargin(false);
        
        // verticalLayout_1
        VerticalLayout verticalLayout_1 = new VerticalLayout();
        verticalLayout_1.setImmediate(false);
        verticalLayout_1.setWidth("100.0%");
        verticalLayout_1.setHeight("100.0%");
        verticalLayout_1.setMargin(true);
        
        // loginForm_1
        formTicket = new Form();
        formTicket.setStyleName("v-loginform");
        formTicket.setImmediate(false);
        formTicket.setWidth("100.0%");
        formTicket.setHeight("100.0%");
        verticalLayout_1.addComponent(formTicket);
        verticalLayout_1.setExpandRatio(formTicket, 1.0f);
        
        formTicket.setFormFieldFactory(new JegyFieldFactory());
        
        
        // button_CalcPrice
        button_CalcPrice = new Button();
        button_CalcPrice.setCaption("OK");
        button_CalcPrice.setImmediate(false);
        button_CalcPrice.setWidth("-1px");
        button_CalcPrice.setHeight("-1px");
        verticalLayout_1.addComponent(button_CalcPrice);
        verticalLayout_1.setComponentAlignment(button_CalcPrice, new Alignment(48));
        mainLayout.addComponent(verticalLayout_1, "left:0.0px;");
        
        return mainLayout;
    }
    
    private AbsoluteLayout initPaymentWindow() {
        AbsoluteLayout mainLayout = new AbsoluteLayout();
        mainLayout.setImmediate(false);
        mainLayout.setWidth("400px");
        mainLayout.setHeight("150px");
        mainLayout.setMargin(false);
        
        // verticalLayout_1
        VerticalLayout verticalLayout_1 = new VerticalLayout();
        verticalLayout_1.setImmediate(false);
        verticalLayout_1.setWidth("100.0%");
        verticalLayout_1.setHeight("100.0%");
        verticalLayout_1.setMargin(true);
        
        // label_Payment
        label_Payment = new Label();
        label_Payment.setImmediate(false);
        label_Payment.setWidth("100.0%");
        label_Payment.setHeight("100.0%");
        verticalLayout_1.addComponent(label_Payment);
        verticalLayout_1.setExpandRatio(label_Payment, 1.0f);
        
        // horizontalLayout_1
        HorizontalLayout horizontalLayout_1 = new HorizontalLayout();
        horizontalLayout_1.setImmediate(false);
        horizontalLayout_1.setWidth("100.0%");
        horizontalLayout_1.setHeight("100.0%");
        horizontalLayout_1.setMargin(false);
        horizontalLayout_1.setSpacing(true);
        
        // button_payOK
        button_payOK = new Button();
        button_payOK.setCaption("Igen");
        button_payOK.setImmediate(false);
        button_payOK.setWidth("-1px");
        button_payOK.setHeight("-1px");
        horizontalLayout_1.addComponent(button_payOK);
        horizontalLayout_1.setComponentAlignment(button_payOK, new Alignment(34));
        
        // button_payCancel
        Button button_payCancel = new Button();
        button_payCancel.setCaption("Nem");
        button_payCancel.setImmediate(false);
        button_payCancel.setWidth("-1px");
        button_payCancel.setHeight("-1px");
        button_payCancel.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                mainWindow.removeWindow(paymentWindow);
            }
        });
        horizontalLayout_1.addComponent(button_payCancel);
        horizontalLayout_1.setComponentAlignment(button_payCancel, new Alignment(33));
        
        verticalLayout_1.addComponent(horizontalLayout_1);
        verticalLayout_1.setExpandRatio(horizontalLayout_1, 1.0f);
        
        mainLayout.addComponent(verticalLayout_1, "left:0.0px;");
        
        return mainLayout;
    }
    
    private void onUserLogon() {
        label_currUser.setValue(Constants.LABEL_GREETING + currentUser.getUsername());
        
        final BeanItemContainer<Foglalas> myBookings = new BeanItemContainer<Foglalas>(Foglalas.class, service.getAllFoglalasByUser(currentUser.getUsername()));
        table_myRes.setContainerDataSource(myBookings);
        table_myRes.setVisibleColumns(new String[] {Constants.JEGY_POJO});
        table_myRes.setSelectable(true);
        table_myRes.setImmediate(true);
        table_myRes.setSortAscending(true);
        table_myRes.addListener(new Property.ValueChangeListener() {
            private static final long serialVersionUID = 1L;

            public void valueChange(ValueChangeEvent event) {
                Object id = table_myRes.getValue();
                hl_myResDetails.setVisible(id != null);
                
                Jegy j = myBookings.getItem(id).getBean().getJegy();
                Menetrend m = service.getMenetrendByJarat(j.getJarat().getJaratId());
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd HH:mm");
                String datum = "";
                if (m != null) {
                    datum = dateFormat.format(m.getIndul())  + " - ";
                }
                String toDisplay = datum + j.getJarat().getHonnan().getVarosnev() + " - " + j.getJarat().getHova().getVarosnev() + " járat (" + j.getJarat().getRepulo().getTarsasag().getTarsasagNev() + "), " + j.getOsztaly().getSzama() + ". osztály";

                formBookings.setValue(toDisplay);
            }
        });
        button_DeleteRes.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                Foglalas f = myBookings.getItem(table_myRes.getValue()).getBean();
                service.deleteFoglalas(f);
                myBookings.removeAllItems();
                myBookings.addAll(service.getAllFoglalasByUser(currentUser.getUsername()));
                table_myRes.setContainerDataSource(myBookings);
                hl_myResDetails.setVisible(false);
            }
        });
        
        formUserEdit.setItemDataSource(currentUserBean);
        Vector<Object> regFormOrder = new Vector<Object>();
        regFormOrder.add(Constants.USERNAME);
        regFormOrder.add(Constants.PASS);
        regFormOrder.add(Constants.OKMANYSZAM);
        regFormOrder.add(Constants.NEVE);
        regFormOrder.add(Constants.SZULETETT);
        regFormOrder.add(Constants.BANKKARTYASZAM);
        regFormOrder.add(Constants.LAKCIM);
        regFormOrder.add(Constants.TELEFONSZAM);
        formUserEdit.setVisibleItemProperties(regFormOrder);
        formUserEdit.getField(Constants.USERNAME).setReadOnly(true);
               
    }
    
    private void openNewTicketWindow(Jarat j) {
        mainWindow.addWindow(ticketWindow);
        
        final BeanItem<Jegy> newTicket = new BeanItem<Jegy>(new Jegy(0, j, null, null));
        formTicket.setItemDataSource(newTicket);
        Vector<Object> tickFormOrder = new Vector<Object>();
        tickFormOrder.add(Constants.JEGY_ID_POJO);
        tickFormOrder.add(Constants.JARAT_POJO);
        tickFormOrder.add(Constants.OSZTALY_POJO);
        tickFormOrder.add(Constants.BIZTOSITAS_POJO);
        formTicket.setVisibleItemProperties(tickFormOrder);
        formTicket.getField(Constants.JEGY_ID_POJO).setReadOnly(true);
        button_CalcPrice.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                Jegy j = newTicket.getBean();
                openNewPaymentWindow(j);
            }
        });
    }
    
    private void openNewPaymentWindow(final Jegy j) {
        mainWindow.removeWindow(ticketWindow);
        mainWindow.addWindow(paymentWindow);
        
        int price = service.callJegyAr(j.getJarat(), j.getOsztaly(), j.getBiztositas());
        label_Payment.setValue("A fizetendő összeg " + price + " Ft. Elfogadja?");
        button_payOK.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                mainWindow.removeWindow(paymentWindow);
                                
                service.insertJegy(j);
                int max = 0;
                for (Jegy uj : service.getAllJegy()) {
                    if (uj.getJegyId() > max) {
                        max = uj.getJegyId();
                    }
                }
                service.insertFoglalas(new Foglalas(currentUser, service.getJegyById(max)));
                onUserLogon();
            }
        });
        
    }
    
    @Override
    public void terminalError(com.vaadin.terminal.Terminal.ErrorEvent event) {
        super.terminalError(event);
        
        if (getMainWindow() != null) {
            showError(event.getThrowable().toString(), "Hiba történt!");
        }
    }
}
