package com.wirtz.fpdual.proyecto.e2.domain.service;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface GetExcelService {
    XSSFWorkbook getExcel(String course, String module, Integer schoolYear, HttpServletResponse response) throws IOException;

    XSSFWorkbook getExcelPostman(Integer courseId, Integer moduleId, Integer schoolYear, HttpServletResponse response) throws IOException;
}
