CREATE DATABASE back1
go
CREATE DATABASE back2
USE master

SELECT * FROM sys.sysdatabases

SELECT name, crdate FROM sys.sysdatabases 

SELECT dbid,name,crdate FROM sys.sysdatabases