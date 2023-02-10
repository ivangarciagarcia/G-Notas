package com.wirtz.fpdual.proyecto.e2.domain.dto;

import com.wirtz.fpdual.proyecto.e2.domain.dto.ScoreDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Setter
public class StudentDTO {
    private Integer studentId;
    private String studentName;
    private String studentLastName;
    private LocalDate studentBirthdate;
    private String studentEmail;
    private String studentDni;
    private String studentAddress;
    private List<ScoreDTO> studentListScore;
}
