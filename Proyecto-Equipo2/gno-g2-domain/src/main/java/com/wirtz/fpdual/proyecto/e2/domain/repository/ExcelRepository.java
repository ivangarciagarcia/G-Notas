package com.wirtz.fpdual.proyecto.e2.domain.repository;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface ExcelRepository {
    String postExcel(MultipartFile file);


}
