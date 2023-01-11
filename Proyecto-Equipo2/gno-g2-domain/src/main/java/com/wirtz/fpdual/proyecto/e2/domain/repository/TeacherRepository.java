package com.wirtz.fpdual.proyecto.e2.domain.repository;

import com.wirtz.fpdual.proyecto.e2.domain.dto.teacher.TeacherDTO;

import java.util.List;

public interface TeacherRepository {

    List<TeacherDTO> getAllTeachers();

    TeacherDTO getTeacherById(Integer id);

    void insertTeacher(TeacherDTO teacherDTO);

    void updateTeacher(TeacherDTO teacherDTO);

    void deleteTeacher(Integer id);
}