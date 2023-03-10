use gnotas_g2_ProyectoServidor;

/*TABLA student*/
insert into gnotas_g2_student (student_name, student_lastname, student_birthdate, student_email, student_dni, student_address)
values('Pedro','Garcia Martinez','2002-01-20','pedro@gmail.com','32568974P','Calle de prueba, 2 ,4A 15002');

insert into gnotas_g2_student (student_name, student_lastname, student_birthdate, student_email, student_dni, student_address)
values('Martin','Perez Gonzalez','2002-11-05','martin@gmail.com','69858974P','Calle aleatoria, 4 ,8A 15062');

/*TABLA school*/
insert into gnotas_g2_school(school_name) values ("FERNANDO-WIRTZ");
insert into gnotas_g2_school(school_name) values ("Ángel Casal");

/*TABLA course*/
insert into gnotas_g2_course(course_name) values ("Desarrollo Aplicaciones Web");
insert into gnotas_g2_course(course_name) values ("Desarrollo Aplicaciones Multiplataforma");

/*TABLA module*/
insert into gnotas_g2_module(module_name) values ("DWS");
insert into gnotas_g2_module(module_name) values ("DWC");
insert into gnotas_g2_module(module_name) values ("DIW");

/*TABLA course_module*/
insert into gnotas_g2_course_module(course_id, module_id, school_id, course_module_grade, teacher_id, school_year)
values (1, 1, 1, "SEGUNDO", 1, 2022);
insert into gnotas_g2_course_module(course_id, module_id, school_id, course_module_grade, teacher_id, school_year)
values (1, 2, 1, "SEGUNDO", 2, 2022);
insert into gnotas_g2_course_module(course_id, module_id, school_id, course_module_grade, teacher_id, school_year)
values (1, 3, 1, "SEGUNDO", 1, 2021);
insert into gnotas_g2_course_module(course_id, module_id, school_id, course_module_grade, teacher_id, school_year)
values (2, 1, 1, "PRIMERO", 1, 2022);

/*TABLA student_module*/
insert into  gnotas_g2_student_module( student_id,  course_module_id)
values (1, 1);
insert into  gnotas_g2_student_module( student_id,  course_module_id)
values (1, 2);
insert into  gnotas_g2_student_module( student_id,  course_module_id)
values (1, 3);
insert into  gnotas_g2_student_module( student_id,  course_module_id)
values (2, 1);

/*select * from student a ;*/
/*select * from gnotas_g2_teacher ggt ;*/

/*-----------------------------------------------------------------------------------------------------------------------------------------------*/

/*TABLA course-module*/

/* TABLA evaluation*/
insert into gnotas_g2_evaluation(evaluation_type, evaluation_exams_percentage,
evaluation_percentage, course_module_id)
values('PRIMERA', 80, 25, 1);

insert into gnotas_g2_evaluation(evaluation_type, evaluation_exams_percentage,
evaluation_percentage, course_module_id)
values('EXTRAORDINARIA', 100, 100, 1);

/* TABLA teacher*/
insert into gnotas_g2_teacher(teacher_name, teacher_login, teacher_email,teacher_password, teacher_is_active)
values ('Alfonso', 'pma', 'pma@fernandowirtz.com', '$2a$12$IBfZ.5eLH8.oN9aUEzgTMedLWEVgpwvY3ByPBzcVe8X625ZFjkrGu', false);

insert into gnotas_g2_teacher(teacher_name, teacher_login, teacher_email, teacher_password, teacher_is_active)
values ('Tino', 'guc', 'guc@fernandowirtz.com', '$2a$12$IBfZ.5eLH8.oN9aUEzgTMedLWEVgpwvY3ByPBzcVe8X625ZFjkrGu', true);

/* TABLA score*/
insert into gnotas_g2_score(score_name, score_number, score_type, evaluation_id, student_id)
values ('Practica:1', 2,  'TEORICO', 1, 1);


