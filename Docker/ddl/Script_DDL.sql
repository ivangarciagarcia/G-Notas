drop database if exists gnotas_g2_proyectoservidor;

create database if not exists gnotas_g2_proyectoservidor;

use gnotas_g2_proyectoservidor;

create table if not exists gnotas_g2_teacher(
teacher_id integer primary key auto_increment,
teacher_name varchar(100) ,
teacher_login varchar(100) unique ,
teacher_email varchar(100) unique ,
teacher_password varchar (100) ,
teacher_is_active boolean default false
);

create table if not exists gnotas_g2_student(
student_id integer primary key auto_increment,
student_name varchar(50) ,
student_lastname varchar(100) ,
student_birthdate date ,
student_email varchar(200) unique,
student_dni varchar(20) unique ,
student_address varchar(200)
);


create table if not exists gnotas_g2_course(
course_id integer primary key auto_increment,
course_name varchar(100));

create table if not exists gnotas_g2_school(
school_id integer primary key auto_increment,
school_name varchar(100));

create table if not exists gnotas_g2_module(
module_id integer primary key auto_increment, 
module_name varchar(100)
);

create table if not exists gnotas_g2_course_module(
course_module_id integer primary key auto_increment ,
course_id integer references gnotas_g2_course(course_id),
module_id integer references gnotas_g2_module(module_id),
school_id integer references gnotas_g2_school(school_id),
course_module_grade enum ("PRIMERO","SEGUNDO") ,
school_year integer,
teacher_id integer references gnotas_g2_teacher(teacher_id) on delete set null
);

create table if not exists gnotas_g2_evaluation(
evaluation_id integer primary key auto_increment ,
evaluation_type enum('PRIMERA','SEGUNDA','TERCERA','FINAL','EXTRAORDINARIA') ,
evaluation_exams_percentage integer,
evaluation_percentage integer,
course_module_id integer references gnotas_g2_course_module(course_module_id) on delete set null
);
create table if not exists gnotas_g2_score(
score_id integer primary key auto_increment,
score_name varchar(50),
score_number float ,
score_type enum('PRACTICA','TEORICO','TAREA') ,
score_percentage integer,
evaluation_id integer references gnotas_g2_evaluation(evaluation_id) on delete set null,
student_id integer references gnotas_g2_student(student_id) on delete set null,
score_version float
);

create table if not exists gnotas_g2_student_module(
student_module_id integer primary key auto_increment,
student_id integer references gnotas_g2_student(student_id) on delete set null,
course_module_id integer references gnotas_g2_course_module(course_module_id) on delete set null
);
