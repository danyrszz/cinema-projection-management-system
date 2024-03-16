package com.cinema.projection;
import com.cinema.projection.equipment.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Room {

  private int id, number;
  private boolean isWorking;
  private boolean isComplete;
  private Complex cinema;
  //equipos de la sala
  private HashMap<EquipmentType, Equipment> equipment = new HashMap<EquipmentType, Equipment>();
  private ArrayList<Amplifier> amplifierSet = new ArrayList<Amplifier>();

  public Room(int id, int number, Complex cinema, boolean isWorking, boolean isComplete){
    this.id = id;
    this.number = number;
    this.cinema = cinema;
    this.isComplete = isComplete;
    this.isWorking = isWorking;
  }

  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }

  public String getCinemaName(){
    return cinema.getName();
  }
  public int getRoomNumber() {
    return number;
  }
  public boolean getIsComplete(){
    return isComplete;
  }
  public boolean getIsWorking(){
    return isWorking;
  }
  public void changeComplete(){
    isComplete = !isComplete;
  }
  public void changeIsWorking(){
    isWorking = !isWorking;
  }


  //la sala es responsable de añadir el equipamiento que va a contener

  public void addEquipment (EquipmentType equipment, String make, String model, String sn) {
    Equipment other = new Equipment (equipment,sn,this,make,model);
    this.equipment.put(equipment, other);
  }

  public void addExistingEquipment ( Equipment equipment ) {
    EquipmentType type = equipment.getType();
    this.equipment.remove(type);
    this.equipment.put(type, equipment);
  }
  //-------------

  public void addAmplifier ( String make, String model, String sn, Amplifier.Channel channel, int channelNumber ) {
    Amplifier amplifier = new Amplifier (sn, this, make, model, channel, channelNumber);
    amplifierSet.add(amplifier);
  }

  //obtienes todo el resto del equipamiento de la sala
  public HashMap<EquipmentType, Equipment> getEquipment() {
    return equipment;
  }

  //obtienes un solo equipo en especifico (no valido para amplificadores)
  public Equipment getEquipment(EquipmentType searchParam){
    return equipment.get(searchParam);
  }

  //obtienes todo el set de amplificadores correspondientes a la sala
  public ArrayList<Amplifier> getAmplifiers () {
    return amplifierSet;
  }

  //obtienes una lista con los amplificadores del canal buscado
  //(generalmente cada sala tiene un amp por canal, pero en las salas
  //triamplificadas suele haber 2, y en las atmos pueden ser mas).
  public ArrayList<Amplifier> getAmplifiers (Amplifier.Channel channel){
    ArrayList<Amplifier> temp = new ArrayList <Amplifier>();
    for(Amplifier amp : amplifierSet){
      if(amp.getScreenChannel() == channel){ 
        temp.add(amp);
      }
    }
    return temp;
  }

  @Override
  public String toString() {
    return "Sala " + number + " del complejo " + cinema.getName() + " en " + cinema.getState();
  }

}
