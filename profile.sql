create database profile_management_;
use profile_management_;
create table users(
      username varchar(50) primary key,
      password varchar(50) not null,
      email varchar(100),
      contact varchar(100)
      );
      
create table profiles(
	username VARCHAR(50) PRIMARY KEY,
    personal_information TEXT,
    education TEXT,
    work_experience TEXT,
    skills TEXT,
    interests TEXT,
    profile_picture TEXT
   
);

CREATE TABLE activity_log (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50),
    activity TEXT,
    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP
    
);

select * from users;
select * from profiles;
select * from activity_log;
drop table users;