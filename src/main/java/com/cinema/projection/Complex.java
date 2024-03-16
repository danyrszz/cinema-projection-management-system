package com.cinema.projection;

import java.util.ArrayList;

import com.cinema.projection.equipment.Equipment;
import com.cinema.projection.equipment.EquipmentType;

public class Complex {

  private int id, totalRooms;
  private String name, city, state;
  private CinemaConcept concept;

  //agregar coleccion de salas
  private ArrayList<Room> rooms = new ArrayList<Room>();

  public Complex (String name, String city, String state, CinemaConcept concept, int totalRooms){
    this.name = name;
    this.city = city;
    this.state = state;
    this.concept = concept;
    this.totalRooms = totalRooms;
  }

  public int getTotalRooms() {
    return totalRooms;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  public String getState() {
    return state;
  }
  
  public String getCity() {
    return city;
  }

  public ArrayList<Room> getRooms() {
    return rooms;
  }

  public CinemaConcept getConcept() {
    return concept;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void addRoom (Room room) {
    rooms.add(room);
  }

  //devuelve todos los proyectores de todas las salas del cine
  public ArrayList<Equipment> getProjectors () {
    ArrayList<Equipment> projectors = new ArrayList<Equipment>();
    for(int i=0; i<rooms.size(); i++){
      Equipment temp = this.getRoom(i+1).getEquipment(EquipmentType.PROJECTOR);
      if (temp!=null) projectors.add(temp);
    }
    return projectors;
  }

  //devuelve todos los servidores de todas las salas del cine
  public ArrayList<Equipment> getServers () {
    ArrayList<Equipment> servers = new ArrayList<Equipment>();
    for(int i=0; i<rooms.size(); i++){
      Equipment temp = this.getRoom(i+1).getEquipment(EquipmentType.SERVER);
      if (temp!=null) servers.add(temp);
    }
    return servers;
  }
  
  public Room getRoom(int room){
    if(room<=rooms.size() && room>0){
      return rooms.get(room-1);
    }
    return null;
  }

  @Override
  public String toString() {
    return name+" esta en la ciudad de "+city+", "
    +state+" y cuenta con "+rooms.size()+" salas en concepto "
    +concept.getLabel();
  }
}
