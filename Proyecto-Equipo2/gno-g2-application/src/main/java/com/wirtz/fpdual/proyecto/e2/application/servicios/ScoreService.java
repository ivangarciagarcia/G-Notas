package com.wirtz.fpdual.proyecto.e2.application.servicios;

import com.wirtz.fpdual.proyecto.e2.domain.dto.ScoreDTO;
import com.wirtz.fpdual.proyecto.e2.domain.repository.ScoreRepository;
import com.wirtz.fpdual.proyecto.e2.domain.service.ScoreServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ScoreService implements ScoreServiceInterface {

    @Autowired
    private final ScoreRepository scoreRepository;

    @Override
    public List<ScoreDTO> getAllScores() {
        return scoreRepository.getAllScores();
    }

//
//    @Override
//    public StudentDTO getStudentById(Integer studentId) {
//        return studentRepository.getStudentById(studentId);
//    }
//
//    @Override
//    public void createStudent(StudentDTO studentDTO){
//       studentRepository.createStudent(studentDTO);
//    }
//
//    @Override
//    public void updateStudent(Integer studentId, StudentDTO student) {
//        studentRepository.updateStudent(studentId, student);
//    }
//
//    @Override
//    public void deleteStudent(Integer studentId){
//        studentRepository.deleteStudentById(studentId);
//    }
}
