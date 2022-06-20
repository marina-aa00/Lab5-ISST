insert into users(username,password,enabled) values ('alumno','{bcrypt}$2a$04$AEqgP0ngGhMg5DIpHQONfeFjkdYdXdYn65md3SWa/ZxfvmreWv4hm',true);
insert into users(username,password,enabled) values ('profesor','{bcrypt}$2a$04$If9hJTzt5esk4eEB9hCPQeXN4P.VwVjXZgzHGsBhRrEgx4c4/LUsG',true); 

/*alumno1*/  /*profesor1*/


insert into authorities(username,authority) values ('alumno','ROLE_ALUM');
insert into authorities(username,authority) values ('profesor','ROLE_PROF');