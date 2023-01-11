package com.wirtz.fpdual.proyecto.e2.infrastructure.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentEntity {

    private Integer studentId;

    private String studentName;

    private String studentLastName;

    private LocalDate studentBirthdate;

    private String studentEmail;

    private String studentDni;

    private String studentAddress;


}
