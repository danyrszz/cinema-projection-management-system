package com.cinema.projection;

public enum CinemaConcept {

  TRADICIONAL("Tradicional" ),
  PREMIUM("Premium"),
  PLATINO("Platino"),
  POP("Pop");

  private String label;
  //private int id;
  private CinemaConcept (String label){
    this.label = label;
    //this.id = id;
  }
  public String getLabel() {
    return label;
  }

  public CinemaConcept getConcept (String id) {
    return CinemaConcept.valueOf(id);
  }
}
