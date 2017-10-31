--Billing Details Table

DROP TABLE IF EXISTS &&1..BILL_DETAILS CASCADE CONSTRAINTS;

CREATE TABLE BILL_DETAILS
(
	BILLING_ID INT PRIMARY KEY AUTO_INCREMENT, 
	BILLING_DATE DATETIME NOT NULL, 
	PARTY_ID INT NOT NULL, 
	BARDAANA INT, 
	MAZDOORI INT, 
	DISCOUNT INT,
	MUDDAT INT,
	TOTAL_BILL_AMOUNT INT, 
	CREATED_DATE DATETIME NOT NULL,
	CREATED_BY VARCHAR(20) NOT NULL,
	UPDATED_DATE DATETIME,
	UPDATED_BY VARCHAR(20), 
	CONSTRAINT createdby_bt_fk FOREIGN KEY (CREATED_BY) REFERENCES &&1..USER (USER_ID),
	CONSTRAINT updatedby_bt_fk FOREIGN KEY (UPDATED_BY) REFERENCES &&1..USER (USER_ID),
	CONSTRAINT partyid_bt_fk FOREIGN KEY (PARTY_ID) REFERENCES &&1..PARTY_DETAILS (PARTY_ID)
);