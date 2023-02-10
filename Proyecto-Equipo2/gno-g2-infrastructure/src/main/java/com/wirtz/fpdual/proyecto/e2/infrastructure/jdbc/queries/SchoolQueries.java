package com.wirtz.fpdual.proyecto.e2.infrastructure.jdbc.queries;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(value = "school-queries.properties")
public class SchoolQueries {

  @Value("${selectAllSchools}")
  private String selectAllSchools;

  @Value("${selectBySchoolId}")
  private String selectBySchoolId;

  @Value("${createSchool}")
  private String createSchool;

  @Value("${updateSchool}")
  private String updateSchool;

  @Value("${deleteSchool}")
  private String deleteSchool;

  @Value("${selectIdFromSchoolObject}")
  private String selectIdFromSchoolObject;
}
