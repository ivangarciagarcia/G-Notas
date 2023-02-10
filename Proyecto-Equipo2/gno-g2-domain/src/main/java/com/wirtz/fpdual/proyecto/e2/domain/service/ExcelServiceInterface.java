package com.wirtz.fpdual.proyecto.e2.domain.service;


import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public interface ExcelServiceInterface {
    String postExcel(MultipartFile file) throws Exception;

}
