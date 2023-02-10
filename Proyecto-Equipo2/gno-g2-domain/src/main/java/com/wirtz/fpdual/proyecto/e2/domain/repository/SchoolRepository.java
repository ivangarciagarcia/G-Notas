package com.wirtz.fpdual.proyecto.e2.domain.repository;

import com.wirtz.fpdual.proyecto.e2.domain.dto.SchoolDTO;

import java.util.List;

public interface SchoolRepository {

  List<SchoolDTO> getAllSchools();
  SchoolDTO getSchoolById(Integer schoolId);

  void createSchool(SchoolDTO schoolDTO);
  void updateSchool(Integer schoolId, SchoolDTO schoolDTO);
  void deleteSchoolById(Integer id);

    Integer getSchoolIdByObject(SchoolDTO schoolDTO);
}
