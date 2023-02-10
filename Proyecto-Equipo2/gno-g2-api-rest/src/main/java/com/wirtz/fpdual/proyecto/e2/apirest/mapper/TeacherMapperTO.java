package com.wirtz.fpdual.proyecto.e2.apirest.mapper;

import com.wirtz.fpdual.proyecto.e2.apirest.to.TeacherTO;
import com.wirtz.fpdual.proyecto.e2.domain.dto.TeacherDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TeacherMapperTO {
    TeacherTO toTeacherTO(TeacherDTO teacherDTO);

    TeacherDTO toTeacherDTO(TeacherTO teacherTO);

    List<TeacherTO> toTeacherListTO(List<TeacherDTO> teacherDTOList);

    List<TeacherDTO> toTeacherListDTO(List<TeacherTO> teacherTOList);
}
