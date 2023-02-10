package com.wirtz.fpdual.proyecto.e2.application.servicios;

import com.wirtz.fpdual.proyecto.e2.domain.dto.StudentDTO;
import com.wirtz.fpdual.proyecto.e2.domain.repository.StudentRepository;
import com.wirtz.fpdual.proyecto.e2.domain.service.StudentServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService implements StudentServiceInterface {

    @Autowired
    private final StudentRepository studentRepository;

    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    @Override
    public StudentDTO getStudentById(Integer studentId) {
        return studentRepository.getStudentById(studentId);
    }

    @Override
    public void createStudent(StudentDTO studentDTO){
       studentRepository.createStudent(studentDTO);
    }

    @Override
    public void updateStudent(Integer studentId, StudentDTO student) {
        studentRepository.updateStudent(studentId, student);
    }

    @Override
    public void deleteStudent(Integer studentId){
        studentRepository.deleteStudentById(studentId);
    }

    @Override
    public StudentDTO getStudentByEmail(String email) {
        return studentRepository.getStudentByEmail(email);
    }
}
