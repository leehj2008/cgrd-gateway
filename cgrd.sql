CREATE TABLE
    movetask
    (
        ckey INT NOT NULL AUTO_INCREMENT,
        PatientName VARCHAR(64),
        PatientID VARCHAR(32),
        StudyDate VARCHAR(32),
        StudyTime VARCHAR(32),
        study_instance_uid VARCHAR(64),
        status VARCHAR(20) DEFAULT 'INIT',
        error_message text,
        complete INT,
        remain INT,
        failed INT,
        startMoveTime DATETIME,
        SOPInstanceUID VARCHAR(64),
        SeriesInstanceUID VARCHAR(64),
        last_update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        LOC VARCHAR(10),
        DTYM VARCHAR(6),
        IDCODE VARCHAR(100),
        CHTNO VARCHAR(100),
        SOURID VARCHAR(2),
        ODDPT VARCHAR(10),
        ODDRNO VARCHAR(10),
        ODDAT VARCHAR(8),
        ODTM VARCHAR(6),
        RCVDAT VARCHAR(8),
        RCVTM VARCHAR(6),
        EXDPT VARCHAR(10),
        EXMITNO VARCHAR(300),
        EXMITNM VARCHAR(100),
        PACSNO VARCHAR(200),
        EXDPTNM VARCHAR(50),
        EXMTYPE VARCHAR(20),
        HEADER_ID VARCHAR(20),
        HASHED_IDCODE VARCHAR(200),
        CHTNO_ENC VARCHAR(200),
        FRMID VARCHAR(30),
        APRVDAT VARCHAR(14),
        Procedure_Code VARCHAR(200),
        GE_EXPMARK VARCHAR(10),
        GE_EXPDAT VARCHAR(8),
        GE_EXPTM VARCHAR(6),
        PRIMARY KEY (ckey)
    )
    ENGINE=Innodb DEFAULT CHARSET=utf8;