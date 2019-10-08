CREATE TABLE
    his_order
    (
        id INT NOT NULL AUTO_INCREMENT,
        domainId VARCHAR(6),
        patientId VARCHAR(20),
        cardNO VARCHAR(20),
        area VARCHAR(20),
        areaCode VARCHAR(20),
        bedNO VARCHAR(10),
        identityNO VARCHAR(20),
        yiBao VARCHAR(30),
        patientName VARCHAR(20),
        phone VARCHAR(20),
        sex VARCHAR(6),
        brithday VARCHAR(20),
        address VARCHAR(200),
        maritalStatus VARCHAR(6),
        ethnicGroupCode VARCHAR(8),
        employee VARCHAR(50),
        organization VARCHAR(50),
        citizen VARCHAR(30),
        contactTel VARCHAR(20),
        contactName VARCHAR(20),
        authorTime VARCHAR(20),
        authorCode VARCHAR(20),
        authorName VARCHAR(20),
        applyOfficeCode VARCHAR(20),
        applyOffice VARCHAR(20),
        verifierTime VARCHAR(30),
        verifierCode VARCHAR(20),
        verifier VARCHAR(20),
        applyCode VARCHAR(20),
        orderClass VARCHAR(20),
        applyContent text,
        statusCode VARCHAR(20),
        specimenNO VARCHAR(20),
        specimen VARCHAR(50),
        executeTime VARCHAR(20),
        executeOfficeCode VARCHAR(20),
        executeOffice VARCHAR(30),
        orderCode VARCHAR(20),
        checkCode VARCHAR(20),
        checkt VARCHAR(30),
        checkMethodCode VARCHAR(20),
        checkMethod VARCHAR(30),
        checkPartCode VARCHAR(20),
        checkPart VARCHAR(30),
        note VARCHAR(200),
        visitNO VARCHAR(20),
        visitStatus VARCHAR(20),
        observationCode VARCHAR(20),
        observation VARCHAR(200),
        observationTime VARCHAR(20),
        diseaseCode VARCHAR(20),
        disease VARCHAR(100),
        historyCode VARCHAR(20),
        history VARCHAR(200),
        appointmentCode VARCHAR(20),
        appointmentTime VARCHAR(20),
        modality VARCHAR(20),
        performerCode VARCHAR(20),
        performer VARCHAR(20),
        readFlag INT DEFAULT 0,
        validFlag INT DEFAULT 0,
        SCHEDULE INT DEFAULT 0,
        orderClassCode VARCHAR(20),
        createDate VARCHAR(20),
        createTime VARCHAR(20),
        charge VARCHAR(20),
        data_source VARCHAR(30) DEFAULT 'MQ' NOT NULL,
        applyDate DATE,
        applyTime TIME,
        result VARCHAR(300),
        patientCode VARCHAR(10),
        PRIMARY KEY (id),
        INDEX ix1 (cardNO),
        INDEX ix2 (applyCode),
        INDEX ix3 (orderCode),
        INDEX ix4 (applyDate)
    )
    ENGINE=InnoDB DEFAULT CHARSET=gbk;