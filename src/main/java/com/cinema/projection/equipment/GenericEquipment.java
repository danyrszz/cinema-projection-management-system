package com.cinema.projection.equipment;

import java.util.ArrayList;

import com.cinema.projection.Room;

public abstract class GenericEquipment {

  private int id;
  private Room originalRoom, currentRoom;
  private boolean isWorking;
  private String make, model;
  private EquipmentType type;
  public ArrayList<Piece> pieces = new ArrayList<Piece>();
  private String serialNumber;

  public GenericEquipment (EquipmentType type, String serial, Room room, String make, String model){
    this.type = type;
    serialNumber = serial;
    currentRoom = originalRoom = room;
    this.make= make;
    this.model = model;
    isWorking = true;
  }
  
  public int getId() { return id; }
  public Room getCurrentRoom() { return currentRoom; }
  public Room getOriginalRoom() { return originalRoom; }
  public boolean getIsWorking(){ return isWorking; }
  public String getMake() { return make; }
  public String getModel() { return model; }
  public EquipmentType getType() { return type; }
  public ArrayList<Piece> getPieces() { return pieces; }
  public String getSerialNumber() { return serialNumber; }

  public void setSerial (String serial) { this.serialNumber = serial; }
  public void setId(int id) { this.id = id; }
  public void setCurrentRoom ( Room room ) { this.currentRoom = room; }
  public void setMake(String make) { this.make = make; }
  public void setModel(String model) { this.model = model; }
  public void changeIsWorking(){ isWorking = !isWorking; }

  public void addPiece (Piece p){
    pieces.add(p);
  }

  public void addPiece (PieceType type, String serial, String name) {
    Piece p = new Piece(type, name, serial);
    pieces.add(p);
  }
  
  public ArrayList<Piece> getPiece (PieceType t){
    //devoler un arraylist con todas las piezas encontradas del mismo tipo...
    ArrayList<Piece> temp = new ArrayList<Piece>();
    for (Piece p : pieces){
      if(p.type==t){
        temp.add(p);
      }
    }
    return temp;
  }

  public Piece getPiece (PieceType t, String name){
    //devolver una sola pieza buscada por su tipo y su nombre...
    return null;
  }

  public Piece getPiece (String serial){
    //devolver pieza que coincida con el numero de serie
    for (Piece p : pieces){
      if(p.serial==serial){
        return p;
      }
    }
    return null;
  }
  
}
