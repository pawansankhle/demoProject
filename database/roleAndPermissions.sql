insert into roles values(1,'role for admin','ROLE_ADMIN');
insert into roles values(2,'role for normal user','ROLE_USER');
insert into roles values(3,'role for supplier','ROLE_SUPPLIER');


insert into permissions values(1,'permissioin for admin','admin_permission');
insert into permissions values(2,'permissioin for admin','user_permission');
insert into permissions values(3,'permissioin for admin','supplier_permission');

insert into rolepermission values(1,1);
insert into rolepermission values(2,2);
insert into rolepermission values(3,3);

