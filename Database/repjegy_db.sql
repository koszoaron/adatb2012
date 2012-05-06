CREATE TABLE felhasznalo (
    username VARCHAR2(16) primary key not null,
    pass VARCHAR2(25),
    okmanyszam VARCHAR2(45),
    neve VARCHAR2(60),
    szuletett DATE,
    bankkartyaszam NUMBER(16),
    lakcim VARCHAR2(60),
    telefonszam NUMBER(13)
)

CREATE TABLE nemzet (
    nemzet_id NUMBER(3) primary key not null,
    orszagnev VARCHAR2(30)
)

CREATE TABLE tarsasag (
    tarsasag_id NUMBER(5) primary key not null,
    tarsasag_nev VARCHAR2(30),
    nemzet_id NUMBER(3),
    FOREIGN KEY (nemzet_id) REFERENCES nemzet(nemzet_id) ON DELETE CASCADE
)

CREATE TABLE varos (
    varos_id NUMBER(3) primary key not null,
    varosnev VARCHAR2(30),
    nemzet_id NUMBER(3),
    FOREIGN KEY (nemzet_id) REFERENCES nemzet(nemzet_id) ON DELETE CASCADE
)

CREATE TABLE repulo (
    repulo_id NUMBER(6) primary key not null,
    tipus VARCHAR2(50),
    ulohely NUMBER(3),
    tarsasag_id NUMBER(5),
    FOREIGN KEY (tarsasag_id) REFERENCES tarsasag(tarsasag_id) ON DELETE CASCADE
)

CREATE TABLE jarat (
    jarat_id NUMBER(6) primary key not null,
    honnan NUMBER(3),
    hova NUMBER(3),
    repulo_id NUMBER(6),
    FOREIGN KEY (repulo_id) REFERENCES repulo(repulo_id) ON DELETE CASCADE,
    FOREIGN KEY (honnan) REFERENCES varos(varos_id) ON DELETE CASCADE,
    FOREIGN KEY (hova) REFERENCES varos(varos_id) ON DELETE CASCADE
)

CREATE TABLE menetrend (
    jarat_id NUMBER(6),
    indul DATE,
    erkezik DATE,
    PRIMARY KEY (jarat_id, indul),
    FOREIGN KEY (jarat_id) REFERENCES jarat(jarat_id) ON DELETE CASCADE
)

CREATE TABLE osztaly (
    szama NUMBER(1) primary key not null,
    etkezes NUMBER(1),
    relax NUMBER(1),
    extra_borond NUMBER(1),
    internet NUMBER(1)
)

CREATE TABLE szalloda (
    varos_id NUMBER(3),
    neve VARCHAR2(30),
    leiras VARCHAR2(4000),
    PRIMARY KEY (varos_id, neve),
    FOREIGN KEY (varos_id) REFERENCES varos(varos_id) ON DELETE CASCADE
)

CREATE TABLE biztositas (
    bizt_id NUMBER(3) primary key not null,
    utlemondas NUMBER(1),
    poggyasz NUMBER(1),
    arvisszaad NUMBER(1)
)

CREATE TABLE akcio (
    jarat_id NUMBER(6),
    ujar NUMBER(6),
    PRIMARY KEY (jarat_id, ujar),
    FOREIGN KEY (jarat_id) REFERENCES jarat(jarat_id) ON DELETE CASCADE
)

CREATE TABLE jegy (
    jegy_id NUMBER(9) primary key not null,
    jarat_id NUMBER(6),
    osztaly_id NUMBER(1),
    bizt_id NUMBER(3),
    FOREIGN KEY (jarat_id) REFERENCES jarat (jarat_id) ON DELETE CASCADE,
    FOREIGN KEY (osztaly_id) REFERENCES osztaly(szama) ON DELETE CASCADE,
    FOREIGN KEY (bizt_id) REFERENCES biztositas(bizt_id) ON DELETE CASCADE
)

CREATE TABLE foglalas (
    username VARCHAR2(16),
    jegy_id NUMBER(9),
    PRIMARY KEY (username, jegy_id),
    FOREIGN KEY (username) REFERENCES felhasznalo(username) ON DELETE CASCADE,
    FOREIGN KEY (jegy_id) REFERENCES jegy(jegy_id) ON DELETE CASCADE
);
