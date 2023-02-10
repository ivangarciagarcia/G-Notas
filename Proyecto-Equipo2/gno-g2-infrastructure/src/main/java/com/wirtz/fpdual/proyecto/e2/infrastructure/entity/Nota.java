package com.wirtz.fpdual.proyecto.e2.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Nota {

    String name;
    String tipo;
    int numero;
}
