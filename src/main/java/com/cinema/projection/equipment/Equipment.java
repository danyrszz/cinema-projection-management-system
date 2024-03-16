package com.cinema.projection.equipment;

import com.cinema.projection.Room;

public class Equipment extends GenericEquipment {


  public Equipment(EquipmentType type, String serial, Room room, String make, String model) {
    super(type, serial, room, make, model);
  }
  
  @Override
  public String toString() {
    return "Equipo:"+ getType().getLabel()+
    " Marca:" +super.getMake()+
    " Modelo:"+super.getModel()+ 
    " Serial:" + super.getSerialNumber() + "\n";
  }

}
