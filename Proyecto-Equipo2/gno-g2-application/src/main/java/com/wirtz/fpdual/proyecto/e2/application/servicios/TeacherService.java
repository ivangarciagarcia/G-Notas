package com.wirtz.fpdual.proyecto.e2.application.servicios;

import com.wirtz.fpdual.proyecto.e2.domain.dto.TeacherDTO;
import com.wirtz.fpdual.proyecto.e2.domain.repository.TeacherRepository;
import com.wirtz.fpdual.proyecto.e2.domain.service.TeacherServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService implements TeacherServiceInterface {

    @Autowired
    private TeacherRepository repository;

    @Override
    public List<TeacherDTO> getAllTeachers() {
        return repository.getAllTeachers();
    }

    @Override
    public TeacherDTO getTeacherById(Integer id) {
        return repository.getTeacherById(id);
    }

    @Override
    public void createTeacher(TeacherDTO teacherDTO) {
        repository.insertTeacher(teacherDTO);
    }

    @Override
    public void updateTeacher(TeacherDTO teacherDTO) {
        repository.updateTeacher(teacherDTO);
    }

    @Override
    public void deleteTeacher(Integer id) {
        repository.deleteTeacher(id);
    }
}
