package com.wirtz.fpdual.proyecto.e2.infrastructure.util;

public enum ScoreType {
  PRACTICA("Practica"), TEORIA("Teoria");

  private final String text;

  ScoreType(String text) {
    this.text = text;
  }
  public String getText(){
    return text;
  }
}
