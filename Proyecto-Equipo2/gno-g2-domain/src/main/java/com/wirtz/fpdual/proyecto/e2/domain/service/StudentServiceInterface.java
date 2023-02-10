package com.wirtz.fpdual.proyecto.e2.domain.service;

import com.wirtz.fpdual.proyecto.e2.domain.dto.StudentDTO;

import java.util.List;

public interface StudentServiceInterface {
    List<StudentDTO> getAllStudents();

    StudentDTO getStudentById(Integer studentId);

    void createStudent(StudentDTO student);

    void updateStudent(Integer studentId, StudentDTO student);

    void deleteStudent(Integer studentId);

    StudentDTO getStudentByEmail(String email);
}
