package controller;

import com.cinema.projection.Complex;
import model.ComplexModel;

public class ComplexController {

  private ComplexModel model = new ComplexModel();
  private Complex complex;
  //declarar vista

  public ComplexController(Complex complex){
    this.complex = complex;
  }

}
