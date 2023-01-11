package com.wirtz.fpdual.proyecto.e2.domain.repository;

import com.wirtz.fpdual.proyecto.e2.domain.dto.student.StudentDTO;

import java.util.List;

public interface StudentRepository {

    List<StudentDTO> getAllStudents();
    StudentDTO getStudentById(Integer studentId);

    void createStudent(StudentDTO studentDTO);
    void updateStudent(Integer studentId, StudentDTO studentDTO);
    void deleteStudentById(Integer id);

    StudentDTO getStudentByEmail(String email);
}
