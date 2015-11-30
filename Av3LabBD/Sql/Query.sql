CREATE DATABASE back1
go
CREATE DATABASE back2
USE master

SELECT * FROM sys.sysdatabases



SELECT dbid,name,crdate FROM sys.sysdatabases where name not like 'tempdb'


SELECT dbid,name,convert(varchar(10),crdate, 103) as data, 
	substring(convert(varchar(10),crdate, 108),1,5) as hora FROM sys.sysdatabases where name not like 'tempdb'
	


--TOPICO 2
create procedure sp_backupBD(@nome varchar(100))
as
declare @pasta varchar(100), @bak varchar(4), @caminho varchar(max)
declare c_backup cursor for
select name FROM sys.sysdatabases where name not like 'tempdb'
open c_backup
fetch next from c_backup into @nome
set @pasta = 'C:\Users\Pedro\Documents\'
set @bak = '.bak'

while @@fetch_status=0
begin
	set @caminho = @pasta + @nome + @bak
	backup database @nome  
		to disk=@caminho
	
	fetch next from c_backup into @nome
end
close c_backup
deallocate c_backup


exec sp_backupBD 'ecommerce'

















CREATE DATABASE back1
go
CREATE DATABASE back2
USE master

SELECT * FROM sys.sysdatabases


SELECT dbid,name,crdate FROM sys.sysdatabases where name not like 'tempdb'

select convert(varchar(10),crdate, 109) FROM sys.sysdatabases where name not like 'tempdb'

SELECT dbid,name,convert(varchar(10),crdate, 103) as data, 
	substring(convert(varchar(10),crdate, 108),1,5) as hora FROM sys.sysdatabases where name not like 'tempdb'

convert(varchar(30),crdate, 103) as data

backup database ecommerce 
to disk = 'C:\Users\Pedro\Documents\ecommerce.bak'

backup database ? 
to disk = 'C:\Users\Pedro\Documents\ ? ' 

alter procedure sp_backupBD(@pasta varchar(100))
as
declare @nome varchar(100), @bak varchar(4), @caminho varchar(max)
declare c_backup cursor for
select name FROM sys.sysdatabases where name not like 'tempdb'
open c_backup
fetch next from c_backup into @nome
set @bak = '.bak'

while @@fetch_status=0
begin
	set @caminho = @pasta + @nome + @bak
	backup database @nome  
		to disk=@caminho
	
	fetch next from c_backup into @nome
end
close c_backup
deallocate c_backup

create procedure sp_backupSelecionado(@pasta varchar(max), @nome varchar(max))
as
declare @bak varchar(4), @caminho varchar(max)
set @bak = '.bak'
set @caminho = @pasta + @nome + @bak
backup database @nome 
		to disk=@caminho

backup database pessoas 
		to disk='C:\Users\Pedro\Documents\'

exec sp_backupSelecionado 'C:\Users\Pedro\Documents\', 'pessoas'