package com.wirtz.fpdual.proyecto.e2.domain.service;

import com.wirtz.fpdual.proyecto.e2.domain.dto.TeacherDTO;

import java.util.List;

public interface TeacherServiceInterface {

    public List<TeacherDTO> getAllTeachers();

    public TeacherDTO getTeacherById(Integer id);

    public void createTeacher(TeacherDTO teacherDTO);

    public void  updateTeacher(TeacherDTO teacherDTO);

    public void deleteTeacher(Integer id);
}
