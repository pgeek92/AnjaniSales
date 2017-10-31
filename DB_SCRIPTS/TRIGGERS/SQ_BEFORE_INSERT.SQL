create or replace TRIGGER SQ_BEFORE_INSERT
BEFORE INSERT
   ON SECURE_QUEST
   FOR EACH ROW

DECLARE
   v_userId VARCHAR2(15);

BEGIN

   -- Find username of person performing INSERT into table
   SELECT USER_ID 
   INTO v_userId
   FROM USER_DETAILS
   WHERE USER_ROLE = 'ADMIN';

   -- Update create_date field to current system date
   :NEW.CREATED_DATE := SYSDATE;

   -- Update created_by field to the username of the person performing the INSERT
   :NEW.CREATED_BY := v_userId;

END;