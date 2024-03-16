package com.cinema.projection.equipment;

import com.cinema.projection.Room;

public class Amplifier extends GenericEquipment{

  int channels;
  Channel screenChannel;

  public Amplifier( String serial, Room room, String make, String model, Channel screenChannel, int channels) {
    super( EquipmentType.AMPLIFIER, serial, room, make, model);
    this.channels = channels;
    this.screenChannel = screenChannel;
  }

  public int getChannels() {
    return channels;
  }
  public Channel getScreenChannel() {
    return screenChannel;
  }

  @Override
  public String toString() {
    return "Marca: " +super.getMake()+
    " Modelo: " +super.getModel()+
    " Número de canales: " +channels+
    " Canal de pantalla: " +screenChannel.getLabel() +"\n";
  }

  public enum Channel {
    LEFT("Izquierdo"),
    CENTER("Centro"),
    RIGHT("Derecho"),
    LS("Surround Izquierdo"),
    RS("Surround Derecho"),
    SUB("Subwoofer"),
    ATMOS("Surround Atmos");

    private String label;

    Channel(String label){
      this.label = label;
    }
    public String getLabel() {
      return label;
    }
  }

}
