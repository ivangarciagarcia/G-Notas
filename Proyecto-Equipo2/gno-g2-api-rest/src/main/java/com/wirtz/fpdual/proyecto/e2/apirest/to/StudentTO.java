package com.wirtz.fpdual.proyecto.e2.apirest.to;

import lombok.Data;
import org.mapstruct.Mapper;

import java.time.LocalDate;

@Data
public class StudentTO {
    private int studentId;
    private String studentName;
    private String studentLastName;
    private LocalDate studentBirthdate;
    private String studentEmail;
    private String studentDni;
    private String studentAddress;
}
