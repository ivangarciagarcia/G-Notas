package com.wirtz.fpdual.proyecto.e2.apirest.mapper;

import com.wirtz.fpdual.proyecto.e2.apirest.to.StudentTO;
import com.wirtz.fpdual.proyecto.e2.domain.dto.StudentDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface StudentMapperTO {
    StudentDTO toStudentDTO(StudentTO studentTO);

    StudentTO toStudentTO(StudentDTO studentDTO);

    List<StudentTO> toStudentTOList(List<StudentDTO> studentDTOList);

    List<StudentDTO> toStudentDTOList(List<StudentTO> studentTOList);

}
