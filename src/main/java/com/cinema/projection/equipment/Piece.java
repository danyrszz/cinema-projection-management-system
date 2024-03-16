package com.cinema.projection.equipment;

public class Piece {

  private int id, equipmentID;
  String name, serial, partNo, description;
  boolean isWorking;
  PieceType type;

  public Piece (PieceType type, String name, String serial){
    this.type=type;
    this.name = name;
    this.serial = serial;
    isWorking = true;
  }

  public int getId() {
    return id;
  }

  public PieceType getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getSerial() {
    return serial;
  }
  
  public boolean getIsWorking(){
    return isWorking;
  }

  public int getEquipmentID() {
    return equipmentID;
  }
  
  public void changeIsWorking(){
    isWorking = !isWorking;
  }
  
  public void setId(int id) {
    this.id = id;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setEquipmentID(int equipmentID) {
    this.equipmentID = equipmentID;
  }
  
  public void setPartNo(String partNo) {
    this.partNo = partNo;
  }
}
