CREATE DATABASE back1
go
CREATE DATABASE back2
USE master

SELECT * FROM sys.sysdatabases



SELECT dbid,name,crdate FROM sys.sysdatabases where name not like 'tempdb'


SELECT dbid,name,convert(varchar(10),crdate, 103) as data, 
	substring(convert(varchar(10),crdate, 108),1,5) as hora FROM sys.sysdatabases where name not like 'tempdb'