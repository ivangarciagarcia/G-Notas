package com.wirtz.fpdual.proyecto.e2.infrastructure.util;

public enum EvaluationType {

  PRIMERA("Primera Evaluacion"),
  SEGUNDA("Segunda Evaluacion"),
  TERCERA("Tercera Evaluacion"),
  FINAL("Evaluacion Final"),
  EXTRAORDINARIA("Evaluacion Extraordinaria");

  private final String text;

  EvaluationType(String text) {
    this.text = text;
  }
  public String getText(){
    return text;
  }
}
