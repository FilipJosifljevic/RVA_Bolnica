INSERT INTO "bolnica"("id","naziv","adresa","budzet")
VALUES(nextval('bolnica_seq'),'Zdravstveni centar Grbavica','Brace Ribnikara 11',250000);
INSERT INTO "bolnica"("id","naziv","adresa","budzet")
VALUES(nextval('bolnica_seq'),'Zdravstveni centar Podbara','Laze Kostica 74',400000);
INSERT INTO "bolnica"("id","naziv","adresa","budzet")
VALUES(nextval('bolnica_seq'),'Zdravstveni centar Petrovaradin','Petrovaradinska 33',300000);
INSERT INTO "bolnica"("id","naziv","adresa","budzet")
VALUES(nextval('bolnica_seq'),'Zdravstveni centar Novo Naselje','Novosadska 44',350000);


INSERT INTO "dijagnoza"("id","naziv","opis","oznaka")
VALUES(nextval('dijagnoza_seq'),'Kasalj','Suvi kasalj','sk345');
INSERT INTO "dijagnoza"("id","naziv","opis","oznaka")
VALUES(nextval('dijagnoza_seq'),'Prehlada','Sezonska prehlada','ph244');
INSERT INTO "dijagnoza"("id","naziv","opis","oznaka")
VALUES(nextval('dijagnoza_seq'),'Kriva kicma','Iskrivljenost u predelu lumbalnog dela','kc568');
INSERT INTO "dijagnoza"("id","naziv","opis","oznaka")
VALUES(nextval('dijagnoza_seq'),'Boginje','Slucaj malih boginja','bo122');

INSERT INTO "odeljenje"("id","naziv","lokacija","klinika")
VALUES(nextval('odeljenje_seq'),'Odeljenje za stomatologiju','Novi Sad',3);
INSERT INTO "odeljenje"("id","naziv","lokacija","klinika")
VALUES(nextval('odeljenje_seq'),'Odeljenje za stomatologiju','Novi Sad',4);
INSERT INTO "odeljenje"("id","naziv","lokacija","klinika")
VALUES(nextval('odeljenje_seq'),'Odeljenje za hirurgiju','Novi Sad',1);
INSERT INTO "odeljenje"("id","naziv","lokacija","klinika")
VALUES(nextval('odeljenje_seq'),'Odeljenje za pedijatriju','Novi Sad',3);
INSERT INTO "odeljenje"("id","naziv","lokacija","klinika")
VALUES(nextval('odeljenje_seq'),'Odeljenje za reumatologiju','Novi Sad',2);
INSERT INTO "odeljenje"("id","naziv","lokacija","klinika")
VALUES(nextval('odeljenje_seq'),'Odeljenje za oftamatologiju','Novi Sad',1);


INSERT INTO "pacijent"("id","ime","prezime","datum_rodjenja","zdr_osiguranje","dijagnoza","odeljenje")
VALUES(nextval('pacijent_seq'),'Milan','Mitrovic','1987-05-13',true,1,3);
INSERT INTO "pacijent"("id","ime","prezime","datum_rodjenja","zdr_osiguranje","dijagnoza","odeljenje")
VALUES(nextval('pacijent_seq'),'Marko','Ilic','1997-06-10',true,1,2);
INSERT INTO "pacijent"("id","ime","prezime","datum_rodjenja","zdr_osiguranje","dijagnoza","odeljenje")
VALUES(nextval('pacijent_seq'),'Petar','Petrovic','1994-02-04',false,2,4);
INSERT INTO "pacijent"("id","ime","prezime","datum_rodjenja","zdr_osiguranje","dijagnoza","odeljenje")
VALUES(nextval('pacijent_seq'),'Janko','Jankovic','1987-08-19',true,2,1);
INSERT INTO "pacijent"("id","ime","prezime","datum_rodjenja","zdr_osiguranje","dijagnoza","odeljenje")
VALUES(nextval('pacijent_seq'),'Srdjan','Markovic','1995-01-20',false,3,2);
INSERT INTO "pacijent"("id","ime","prezime","datum_rodjenja","zdr_osiguranje","dijagnoza","odeljenje")
VALUES(nextval('pacijent_seq'),'Nikola','Petrovic','1977-02-27',false,3,1);
