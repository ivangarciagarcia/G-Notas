package com.wirtz.fpdual.proyecto.e2.application.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class MathService {

  public double obtenerMedia(List<Float> notas) {
    Float notaMedia = 0F;
    for (Float nota : notas){
      notaMedia += nota;
    }
    return ( notaMedia / notas.size()) ;
  }

  public List<Double> aplicarPorcentaje(List<Float> medias, Double porcentaje) {
    Double media = obtenerMedia(medias);
    return Collections.singletonList(media + (media * (porcentaje / 100)));
  }
}

