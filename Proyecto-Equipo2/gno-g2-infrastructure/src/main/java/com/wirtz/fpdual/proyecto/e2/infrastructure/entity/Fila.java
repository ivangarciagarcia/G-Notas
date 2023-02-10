package com.wirtz.fpdual.proyecto.e2.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Fila {

    String nombreAlumno;
    String apellidosAlumno;
    int numId;
    String institucion;
    String departamento;
    String email;
    ArrayList<Nota> notas;
}
