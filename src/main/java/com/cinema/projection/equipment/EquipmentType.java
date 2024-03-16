package com.cinema.projection.equipment;

import java.util.HashMap;

public enum EquipmentType {

  PROJECTOR("Proyector"),
  SERVER("Servidor"),
  AMPLIFIER("Amplificador"),
  CROSSOVER("Crossover"),
  AUDIO_CONVERTER("Escalador de audio"),
  NETWORK_SWITCH("Switch de red"),
  AUTOMATION("Automatizacion"),
  UPS("UPS"),
  DIMMER("Dimmer"),
  PROCESSOR("Procesador de audio"),
  SCREEN("Pantalla");

  private String label;
  private static final HashMap<String, EquipmentType> MAP = new HashMap<>();

  private EquipmentType (String label){
    this.label = label;
  }

  static{
    for(EquipmentType element : values()){
      MAP.put(element.getLabel(), element);
    }
  }

  public static EquipmentType getEquipmentFromLabel (String label) {
    return MAP.get(label);
  }

  public String getLabel() {
    return label;
  }

}
