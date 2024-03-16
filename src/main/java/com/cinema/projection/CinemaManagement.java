package com.cinema.projection;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import com.cinema.projection.equipment.*;

public class CinemaManagement {

  private HashMap<String, Complex> cinemas = new HashMap<String, Complex>();
  private ArrayList<Failure> failureHistory = new ArrayList<Failure>();

  // Complex palmar = new Complex("El palmar", "Coatzacoalcos", "Veracruz",CinemaConcept.TRADICIONAL, 8);
  // Complex minatitlan = new Complex("Minatitlan", "Minatitlan", "Veracruz",CinemaConcept.TRADICIONAL, 7);
  // Complex forumcoatza = new Complex("Forum Coatzacoalcos", "Coatzacoalcos", "Veracruz",CinemaConcept.TRADICIONAL, 9);
  // Complex forumcoatzap = new Complex("Forum Coatzacoalcos Platino", "Coatzacoalcos", "Veracruz",CinemaConcept.PLATINO, 4);
  // Complex galerias = new Complex("Galerías Tuxtla", "Tuxtla Gutiérrez", "Chiapas",CinemaConcept.TRADICIONAL, 8);
  // Complex ambar = new Complex("Ambar Capital", "Tuxtla Gutiérrez", "Chiapas",CinemaConcept.PREMIUM, 9);
  // Complex ambarplatino = new Complex("Ambar Capital Platino", "Tuxtla Gutiérrez", "Chiapas",CinemaConcept.PLATINO, 4);

  // public CinemaManagement (){
  //   cinemas.put("palmar", palmar);
  //   cinemas.put("minatitlan", minatitlan);
  //   cinemas.put("forum", forumcoatza);
  //   cinemas.put("forumplatino", forumcoatzap);
  //   cinemas.put("galerias", galerias);
  //   cinemas.put("ambar", ambar);
  //   cinemas.put("ambarplatino", ambarplatino);
  // }


  public Complex getComplex(String key){
    return cinemas.get(key);
  }

  public ArrayList<Failure> getFailureHistory() {
    return failureHistory;
  }
  
  public boolean isCinemasEmpty () {
    if(cinemas.size()<=0) return true;
    return false;
  }

  public boolean isFailureHistoryEmpty () {
    if(failureHistory.size()<=0) return true;
    return false;
  }

  //IMPLEMENTAR OBTENER FALLAS POR MES-AÑO, POR AÑO COMPLETO
  

  public void addComplex (Complex complex) {
    String original = "ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖØÙÚÛÜÝßàáâãäåæçèéêëìíîïðñòóôõöøùúûüýÿ";
    String ascii = "AAAAAAACEEEEIIIIDNOOOOOOUUUUYBaaaaaaaceeeeiiiionoooooouuuuyy";
    String key = complex.getName().toLowerCase().replace(" ", "");
    for (int i=0; i<original.length(); i++) {
      key = key.replace(original.charAt(i), ascii.charAt(i));
    }
    this.cinemas.put(key, complex);
  }


  public void registerFailure ( Object o, String description, int mac ){
    if(o instanceof GenericEquipment || o instanceof Piece){
      Failure failure = new Failure (o, description, mac);
      failureHistory.add(failure);
    }
  }

  public void registerFailure ( Object o, String description, int mac, int day, int month, int year ){
    Failure failure = new Failure (o, description, LocalDate.of(year,month,day), mac);
    failureHistory.add(failure);
  }


  //devuelve todos los equipos de un mismo tipo por complejo
  public ArrayList<GenericEquipment> getEquipment (EquipmentType equipmentQuery, Complex cinema) {
    int totalRooms = cinema.getRooms().size();
    ArrayList<GenericEquipment> requestedEquipment = new ArrayList<GenericEquipment>();
    for(int i=0; i<totalRooms; i++){
      Equipment temp = cinema.getRoom(i+1).getEquipment(equipmentQuery);
      if(temp!=null){
        requestedEquipment.add(temp);
      }
    }
    return requestedEquipment;
  }

}
