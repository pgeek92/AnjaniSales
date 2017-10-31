@echo off

FOR /f "tokens=1-8 delims=:./ " %%G IN ("%date%_%time%") DO (

SET datetime=%%G%%H%%I_%%J_%%K

)

set result_file=../output/AsalesTableCreationLogs%datetime%.log
set schema_name_table=ASALES 
set user_name=root
set password=root
set net_service_name=localhost:3306/asales

if exist %result_file% (
    del %result_file%
)

cd ../scripts

echo exit | sqlplus -s  %user_name%/%password%@%net_service_name% @SecureQuest.sql %schema_name_table% >> %result_file%
echo exit | sqlplus -s  %user_name%/%password%@%net_service_name% @User.sql %schema_name_table% >> %result_file%
echo exit | sqlplus -s  %user_name%/%password%@%net_service_name% @LoginDetail.sql %schema_name_table% >> %result_file%

echo exit | sqlplus -s  %user_name%/%password%@%net_service_name% @ItemDetails.sql %schema_name_table% >> %result_file%
echo exit | sqlplus -s  %user_name%/%password%@%net_service_name% @BrokerDetails.sql %schema_name_table% >> %result_file%
echo exit | sqlplus -s  %user_name%/%password%@%net_service_name% @PartyDetails.sql %schema_name_table% >> %result_file%
echo exit | sqlplus -s  %user_name%/%password%@%net_service_name% @BillDetails.sql %schema_name_table% >> %result_file%
echo exit | sqlplus -s  %user_name%/%password%@%net_service_name% @SalesTransaction.sql %schema_name_table% >> %result_file%

echo exit | sqlplus -s  %user_name%/%password%@%net_service_name% @InsertSecureQuest.sql %schema_name_table% >> %result_file%
echo exit | sqlplus -s  %user_name%/%password%@%net_service_name% @InsertUserDetails.sql %schema_name_table% >> %result_file%

cd ../batch
pause:
