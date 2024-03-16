package com.cinema.projection;

import java.time.LocalDate;

public class Failure {
  private int id, mac;
  private LocalDate date;
  private Object element;
  private String description;
  
  //constructor para falla registrada HOY
  public Failure (Object element, String description, int mac) {
    this.element = element;
    this.description = description;
    this.mac = mac;
    date = LocalDate.now();
  }

  //constructor para falla que sucedio con anterioridad
  public Failure (Object element, String description, LocalDate date, int mac) {
    this.element = element;
    this.description = description;
    this.date = date;
    this.mac=mac;
  }

  public int getId() {
    return id;
  }

  public int getMac() {
    return mac;
  }

  
  public String getDescription() {
    return description;
  }
  
  public Object getElement() {
    return element;
  }
  
  public LocalDate getDate() {
    return date;
  }
  
  public void setId(int id) {
    this.id = id;
  }

  public void setMac(int mac) {
    this.mac = mac;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "Falló: " + element + " " +
    "En: " + date + ". " +
    "Falla: " + description;
  }
}

