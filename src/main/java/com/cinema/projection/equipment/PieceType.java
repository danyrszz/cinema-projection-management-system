package com.cinema.projection.equipment;

public enum PieceType {

  TARJETA("Tarjeta"),
  VENTILADOR("Ventilador"),
  PODER("Fuente de poder"),
  ALMACENAMIENTO("Almacenamiento"),
  ESPECIALIZADA("Especializada");

  private String label;

  private PieceType (String label){
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

}
