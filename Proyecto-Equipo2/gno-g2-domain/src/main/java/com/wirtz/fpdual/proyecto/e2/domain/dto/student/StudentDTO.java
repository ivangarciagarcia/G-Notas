package com.wirtz.fpdual.proyecto.e2.domain.dto.student;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentDTO {
    private Integer studentId;
    private String studentName;
    private String studentLastName;
    private LocalDate studentBirthdate;
    private String studentEmail;
    private String studentDni;
    private String studentAddress;
}
