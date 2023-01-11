package com.wirtz.fpdual.proyecto.e2.application.servicios;

import com.wirtz.fpdual.proyecto.e2.domain.dto.school.SchoolDTO;
import com.wirtz.fpdual.proyecto.e2.domain.repository.SchoolRepository;
import com.wirtz.fpdual.proyecto.e2.domain.service.SchoolServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class SchoolService implements SchoolServiceInterface {
  @Autowired
  private final SchoolRepository schoolRepository;

  @Override
  public List<SchoolDTO> getAllSchools() {
    return schoolRepository.getAllSchools();
  }

  @Override
  public SchoolDTO getSchoolById(Integer schoolId) {
    return schoolRepository.getSchoolById(schoolId);
  }

  @Override
  public void createSchool(SchoolDTO school) {
    schoolRepository.createSchool(school);
  }

  @Override
  public void updateSchool(Integer schoolId, SchoolDTO school) {
    schoolRepository.updateSchool(schoolId,school);
  }

  @Override
  public void deleteSchool(Integer schoolId) {
    schoolRepository.deleteSchoolById(schoolId);
  }
}
