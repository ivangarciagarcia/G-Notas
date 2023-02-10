package com.wirtz.fpdual.proyecto.e2.apirest.controller;


import com.github.miachm.sods.Range;
import com.github.miachm.sods.Sheet;
import com.github.miachm.sods.SpreadSheet;
import com.wirtz.fpdual.proyecto.e2.domain.service.ExcelServiceInterface;
import com.wirtz.fpdual.proyecto.e2.domain.service.GetExcelService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    ExcelServiceInterface service;

    @Autowired
    GetExcelService getExcelService;


    @GetMapping(value = "/postman", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public XSSFWorkbook getExcelPostman(HttpServletResponse response, @RequestParam Integer courseId, @RequestParam Integer moduleId, @RequestParam Integer schoolYear)  {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=notas_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        XSSFWorkbook excel = null;
        try {
            excel = getExcelService.getExcelPostman(courseId, moduleId, schoolYear, response);
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Error al generar el Excel");
        }
        return excel;
    }

    @GetMapping()
    public ResponseEntity<XSSFWorkbook> getExcel(HttpServletResponse response, @RequestParam String course, @RequestParam String module, @RequestParam Integer schoolYear)  {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=notas_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        XSSFWorkbook excel = null;
        try {
            excel = getExcelService.getExcel(course, module, schoolYear, response);
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Error al generar el Excel");
        }
        return new ResponseEntity<XSSFWorkbook>(excel, HttpStatus.OK);
    }

    @PostMapping(value = "/read")
    public ResponseEntity<String> readExcel(@RequestParam("file") MultipartFile file) throws IOException {

        try {
            InputStream inputStream = file.getInputStream();
            SpreadSheet spread = new SpreadSheet(inputStream);
            List<Sheet> sheets = spread.getSheets();

            for (Sheet sheet : sheets) {
                // System.out.println("In sheet " + sheet.getName());

                Range range = sheet.getDataRange();
                //System.out.println(range.getCell(1,1).getValue().toString());
                service.postExcel(file);


            }
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("He metido todo en base de datos", HttpStatus.ACCEPTED);
    }
}
