CREATE TABLE Country
(countryID int identity(1,1) not null,
country nvarchar(50) not null,
Primary key (countryID));

CREATE TABLE City
(CityID int not null,  
city nvarchar(50) not null,
FK_country_ID int not null,
PRIMARY KEY (cityID),
FOREIGN KEY (FK_country_ID) references Country(countryID));

CREATE TABLE Worker
(WorkerID nvarchar(30) not null,
[name] nvarchar(50) not null,
phoneno nvarchar(30), 
email nvarchar(50), 
cpr nvarchar(15) not null, 
streetName nvarchar(40) not null,
houseNumber int not null,
FK_city_ID int not null,
signedIn int not null,
PRIMARY KEY (workerID),
FOREIGN KEY (FK_city_ID) references City(cityID));

CREATE TABLE Absence
(AbsenceID int identity (1,1) not null,
[type] nvarchar (100) not null,
[start] datetime not null,
[end] datetime not null, 
FK_worker_ID nvarchar(30) not null,
PRIMARY KEY (absenceID),
FOREIGN KEY (FK_worker_ID) references Worker(workerID));

CREATE TABLE Schedule
(ScheduleID int identity(1,1) not null,
[name] nvarchar(50) not null,
[start] datetime not null,
[end] datetime not null,
PRIMARY KEY (scheduleID));

CREATE TABLE WorkerSchedule
(worker_ID nvarchar(30) not null,
schedule_ID int not null,
created_By nvarchar(50) not null,
PRIMARY KEY (worker_ID, schedule_ID),
FOREIGN KEY (worker_ID) references Worker(workerID),
FOREIGN KEY (schedule_ID) references Schedule(scheduleID));

CREATE TABLE [Shift]
(ShiftID int identity(1,1) not null,
[start] datetime not null,
[end] datetime,
past_or_future_shift int not null,
FK_schedule_ID int,
FK_worker_ID nvarchar(30) not null,
PRIMARY KEY (shiftID),
FOREIGN KEY (FK_schedule_ID) references Schedule(scheduleID),
FOREIGN KEY (FK_worker_ID) references Worker(workerID));

CREATE TABLE Post
(PostID int identity(1,1) not null,
[date] datetime not null,
[text] nvarchar(200) not null,
FK_shift_ID int not null,
FK_worker_ID nvarchar(30) not null,
PRIMARY KEY (postID),
FOREIGN KEY (FK_worker_ID) references Worker(workerID),
FOREIGN KEY (FK_shift_ID) references [Shift](shiftID));

CREATE TABLE Bulletin
(BulletinID int identity(1,1) not null,
[date] datetime not null,
name nvarchar(30) not null,
FK_post_ID int not null
PRIMARY KEY (bulletinID)
FOREIGN KEY (FK_post_ID) references Post(postID));

ALTER TABLE Worker
ADD FOREIGN KEY (FK_post_ID) references Post(postID);

Insert into country (country)
values ('Danmark')

Insert into City(cityID, city, FK_country_ID)
values (9000, 'Aalborg', 1)

Insert into Worker(WorkerID, [name], phoneno, email, cpr, streetName, houseNumber, FK_city_ID, FK_post_ID, signedIn)
values ('050607', 'Mikkel Nielsen', '21786500', 'mikkel@gmail.com', '050607-5467', 'Gaden', 17, 9000, null, 0)

Insert into Worker(WorkerID, [name], phoneno, email, cpr, streetName, houseNumber, FK_city_ID, FK_post_ID, signedIn)
values ('010203', 'Karsten Jensen', '04758936', 'karsten@gmail.com', '010203-1243', 'Gaden', 19, 9000, null, 0)

