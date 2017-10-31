CREATE TABLE BPHBUS.SECURE_QUEST (SECURITY_QUESTION_ID NUMBER,
                                  SECURITY_QUESTION VARCHAR2(100) NOT NULL,
                                  CREATED_DATE DATE,
                                  CREATED_BY VARCHAR2(50),
                                  CONSTRAINT SQ_ID_PK PRIMARY KEY(SECURITY_QUESTION_ID));