
SELECT Sname,Sdept from Student inner join SC on SC.Sno=Student.Sno where SC.Cno='C002'
go

select Sname,Student.Sno,Grade from Student inner join SC on SC.Sno=Student.Sno where Grade>80
go

select Sname,Ssex,Grade from Student inner join SC on SC.Sno=Student.Sno where Sdept='计算机系' and Ssex='男' and Cno='C005'
go

select COUNT(Cno),Sno,Sname,Cno,Grade from Student inner join SC on SC.Sno=Student.Sno