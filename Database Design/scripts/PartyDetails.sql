--Party Table

DROP TABLE IF EXISTS &&1..PARTY_DETAILS CASCADE CONSTRAINTS;

CREATE TABLE &&1..PARTY_DETAILS 
( 
	PARTY_ID INT AUTO_INCREMENT,
    PARTY_NAME VARCHAR(50) NOT NULL,
	ADDRESS VARCHAR(100) NOT NULL,
	LANDMARK VARCHAR(30),
   	CITY VARCHAR(30) NOT NULL,
	PARTY_CATEGORY VARCHAR(15) NOT NULL,
	TOTAL_OUTSTANDING_AMOUNT DECIMAL(12,2) DEFAULT 0,
	PARTY_STATUS VARCHAR(20) NOT NULL,
	MOBILE_NUMBER VARCHAR(10),
	WHATSAPP_NUMBER VARCHAR(10),
	LANDLINE_NUMBER VARCHAR(20),
	REMARKS VARCHAR(100),
	CREATED_DATE DATETIME NOT NULL,
	CREATED_BY VARCHAR(20) NOT NULL,
	UPDATED_DATE DATETIME,
	UPDATED_BY VARCHAR(20),
	CONSTRAINT partyid_pk PRIMARY KEY (PARTY_ID),
	CONSTRAINT partycat_ck CHECK(PARTY_CATEGORY IN ('Local','Outstation')),
	CONSTRAINT createdby_pd_fk FOREIGN KEY (CREATED_BY) REFERENCES &&1..USER (USER_ID),
	CONSTRAINT updatedby_pd_fk FOREIGN KEY (UPDATED_BY) REFERENCES &&1..USER (USER_ID)
);