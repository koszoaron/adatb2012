package com.github.koszoaron.adatb2012.util;

public class Constants {
    public static final String PROPERTIES_FILENAME = "db.properties";
    public static final String PROPERTIES_URL = "URL";
    public static final String PROPERTIES_USER = "user";
    public static final String PROPERTIES_PASS = "pass";
    
    public static final String FELHASZNALO = "felhasznalo";
    public static final String USERNAME = "username";
    public static final String PASS = "pass";
    public static final String OKMANYSZAM = "okmanyszam";
    public static final String NEVE = "neve";
    public static final String SZULETETT = "szuletett";
    public static final String BANKKARTYASZAM = "bankkartyaszam";
    public static final String LAKCIM = "lakcim";
    public static final String TELEFONSZAM = "telefonszam";

    public static final String NEMZET = "nemzet";
    public static final String NEMZET_ID = "nemzet_id";
    public static final String ORSZAGNEV = "orszagnev";

    public static final String TARSASAG = "tarsasag";
    public static final String TARSASAG_ID = "tarsasag_id";
    public static final String TARSASAG_NEV = "tarsasag_nev";
    /* NEMZET_ID*/

    public static final String VAROS = "varos"; 
    public static final String VAROS_ID = "varos_id";
    public static final String VAROSNEV = "varosnev";
    /* NEMZET_ID*/

    public static final String REPULO = "repulo"; 
    public static final String REPULO_ID = "repulo_id";
    public static final String TIPUS = "tipus";
    public static final String ULOHELY = "ulohely";
    /* TARSASAG_ID */

    public static final String JARAT = "jarat"; 
    public static final String JARAT_ID = "jarat_id";
    public static final String HONNAN = "honnan";
    public static final String HOVA = "hova";
    /* REPULO_ID */

    public static final String MENETREND = "menetrend"; 
    /* JARAT_ID */
    public static final String INDUL = "indul";
    public static final String ERKEZIK = "erkezik";

    public static final String OSZTALY = "osztaly"; 
    public static final String SZAMA = "szama";
    public static final String ETKEZES = "etkezes";
    public static final String RELAX = "relax";
    public static final String EXTRA_BOROND = "extra_borond";
    public static final String INTERNET = "internet";

    public static final String SZALLODA = "szalloda"; 
    /* VAROS_ID */
    /* NEVE */
    public static final String LEIRAS = "leiras";

    public static final String BIZTOSITAS = "biztositas"; 
    public static final String BIZT_ID = "bizt_id";
    public static final String UTLEMONDAS = "utlemondas";
    public static final String POGGYASZ = "poggyasz";
    public static final String ARVISSZAAD = "arvisszaad";

    public static final String AKCIO = "akcio";
    /* JARAT_ID */
    public static final String UJAR = "ujar";

    public static final String JEGY = "jegy";
    public static final String JEGY_ID = "jegy_id";  
    /* JARAT_ID*/
    /* OSZTALY_ID */
    /* BIZT_ID */
    
    public static final String FOGLALAS = "foglalas"; 
    /* USERNAME */
    /* JEGY_ID */
    
    public static final String[] COLS_FELHASZNALO = {USERNAME, PASS, OKMANYSZAM, NEVE, SZULETETT, BANKKARTYASZAM, LAKCIM, TELEFONSZAM};
    public static final String[] COLS_NEMZET = {NEMZET_ID, ORSZAGNEV};
    public static final String[] COLS_TARSASAG = {TARSASAG_ID, TARSASAG_NEV, NEMZET_ID};
    public static final String[] COLS_VAROS = {VAROS_ID, VAROSNEV, NEMZET_ID};
    public static final String[] COLS_REPULO = {REPULO_ID, TIPUS, ULOHELY, TARSASAG_ID};
    public static final String[] COLS_JARAT = {JARAT_ID, HONNAN, HOVA, REPULO_ID};
    public static final String[] COLS_MENETREND = {JARAT_ID, INDUL, ERKEZIK};
    public static final String[] COLS_OSZTALY = {SZAMA, ETKEZES, RELAX, EXTRA_BOROND, INTERNET};
    public static final String[] COLS_SZALLODA = {VAROS_ID, NEVE, LEIRAS};
    public static final String[] COLS_BIZTOSITAS = {BIZT_ID, UTLEMONDAS, POGGYASZ, ARVISSZAAD};
    public static final String[] COLS_AKCIO = {JARAT_ID, UJAR};
    public static final String[] COLS_JEGY = {JEGY_ID, JARAT_ID, SZAMA, BIZT_ID};
    public static final String[] COLS_FOGLALAS = {USERNAME, JEGY_ID};
    
    public static final String[] HEADERS_FELHASZNALO = {"Username", "Password", "Okmányszám", "Név", "Született", "Bankkártyaszám", "Lakcím", "Telefonszám"};
}