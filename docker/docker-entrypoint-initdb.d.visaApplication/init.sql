create table ADDRESS (id bigint not null, address varchar(255), city varchar(255), country varchar(255), phoneNumber varchar(255), postCode varchar(255), primary key (id)) engine=InnoDB;
create table APPLICANT (birthDate datetime(6), id bigint not null, fullName varchar(255), nationality varchar(255), primary key (id)) engine=InnoDB;
create table PASSPORT (expirationDate date, id bigint not null auto_increment, issuingCountry varchar(255), passportNumber varchar(255), primary key (id)) engine=InnoDB;
create table VISA_APPLICATION (applicationDate date, intendedArriveDate date, applicantAddress_id bigint, applicantPassportInfo_id bigint, applicant_id bigint, id bigint not null, payment_id bigint, purposeOfVisit enum ('BUSINESS','EDUCATION','MEDICAL','TOURIST'), status enum ('APPROVED','REJECTED','SECURITY_FAILED','SECURITY_PASSED','SUBMITTED'), primary key (id)) engine=InnoDB;
create table VISA_FEE_RECEIPT (chargedAmount decimal(38,2), valid bit, id bigint not null, primary key (id)) engine=InnoDB;
alter table VISA_APPLICATION add constraint UK_ffogis26c7eifot3g16usudw7 unique (applicantPassportInfo_id);
alter table VISA_APPLICATION add constraint UK_pcum7rl8jn38l8aywklfwiscj unique (payment_id);
alter table VISA_APPLICATION add constraint FK7nj0hsh3py8weehq1lssf4fso foreign key (applicant_id) references APPLICANT (id);
alter table VISA_APPLICATION add constraint FKfsmicwkekg3jd2pbhtof60ir4 foreign key (applicantAddress_id) references ADDRESS (id);
alter table VISA_APPLICATION add constraint FK1uvqv296e1ch42xs8gx8id58w foreign key (applicantPassportInfo_id) references PASSPORT (id);
alter table VISA_APPLICATION add constraint FKojmn599bb44ecinmuy9as5js3 foreign key (payment_id) references VISA_FEE_RECEIPT (id);


INSERT INTO visaApplication.ADDRESS
(id, address, city, country, phoneNumber, postCode)
VALUES(1, 'xxx', 'xxx', 'xxx', 'xxx', 'xxx');
INSERT INTO visaApplication.ADDRESS
(id, address, city, country, phoneNumber, postCode)
VALUES(2, 'yyy', 'yyy', 'yyy', 'yyy', 'yyy');
INSERT INTO visaApplication.ADDRESS
(id, address, city, country, phoneNumber, postCode)
VALUES(3, 'zzz', 'zzz', 'zzz', 'zzz', 'zzz');


INSERT INTO visaApplication.APPLICANT
(birthDate, id, fullName, nationality)
VALUES(NULL, 1, 'xxx', 'xxx');
INSERT INTO visaApplication.APPLICANT
(birthDate, id, fullName, nationality)
VALUES(NULL, 2, 'xxx', 'xxx');
INSERT INTO visaApplication.APPLICANT
(birthDate, id, fullName, nationality)
VALUES(NULL, 3, 'xxx', 'xxx');

INSERT INTO visaApplication.VISA_FEE_RECEIPT
(chargedAmount, valid, id)
VALUES(11.00, 1, 1);

INSERT INTO visaApplication.VISA_FEE_RECEIPT
(chargedAmount, valid, id)
VALUES(11.00, 1, 2);


INSERT INTO visaApplication.VISA_FEE_RECEIPT
(chargedAmount, valid, id)
VALUES(11.00, 1, 3);