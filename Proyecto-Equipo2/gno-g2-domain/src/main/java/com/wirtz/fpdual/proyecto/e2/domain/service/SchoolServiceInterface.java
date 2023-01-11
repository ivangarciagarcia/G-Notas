package com.wirtz.fpdual.proyecto.e2.domain.service;

import com.wirtz.fpdual.proyecto.e2.domain.dto.school.SchoolDTO;

import java.util.List;

public interface SchoolServiceInterface {
  List<SchoolDTO> getAllSchools();
  SchoolDTO getSchoolById(Integer schoolId);
  void createSchool(SchoolDTO school);
  void updateSchool(Integer schoolId, SchoolDTO school);
  void deleteSchool(Integer schoolId);
}
