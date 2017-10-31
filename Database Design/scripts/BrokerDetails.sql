--Broker Table

DROP TABLE IF EXISTS &&1..BROKER_DETAILS CASCADE CONSTRAINTS;

CREATE TABLE  &&1..BROKER_DETAILS 
(
	BROKER_ID INT AUTO_INCREMENT,
    BROKER_NAME VARCHAR(50) NOT NULL,
    TOTAL_AMOUNT_REMAINING DECIMAL(12,2) DEFAULT 0,
	BROKER_STATUS VARCHAR(20) NOT NULL,
	MOBILE_NUMBER VARCHAR(10),
	WHATSAPP_NUMBER VARCHAR(10),
	LANDLINE_NUMBER VARCHAR(20),
	REMARKS VARCHAR(100),
	CREATED_DATE DATETIME NOT NULL,
	CREATED_BY VARCHAR(20) NOT NULL,
	UPDATED_DATE DATETIME,
	UPDATED_BY VARCHAR(20),
	CONSTRAINT brokerid_pk PRIMARY KEY (BROKER_ID),
	CONSTRAINT createdby_bd_fk FOREIGN KEY (CREATED_BY) REFERENCES &&1..USER (USER_ID),
	CONSTRAINT updatedby_bd_fk FOREIGN KEY (UPDATED_BY) REFERENCES &&1..USER (USER_ID)
);