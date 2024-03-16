package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import com.cinema.projection.equipment.EquipmentType;
import model.ComplexModel;
import model.EquipmentModel;
import view.dialogs.AddMakeDialog;

public class AddMakeController {

  EquipmentModel eqModel = new EquipmentModel();
  ComplexModel complexModel = new ComplexModel();
  AddMakeDialog makeDialog;

  public AddMakeController (AddMakeDialog makeDialog){
    this.makeDialog = makeDialog;
    makeDialog.setListeners(this);
    makeDialog.appendNewList(getEquipmentType());
  }

  public void renderDialog () {
    makeDialog.setVisible(true);
  }

  public ArrayList<EquipmentType> getEquipmentType () {
    return eqModel.selectEquipmentType();
  }

  public ActionListener save() {
    return new ActionListener() {
      public void actionPerformed (ActionEvent e){
        String make = makeDialog.getMakeText();
        if(make.isEmpty()){
          JOptionPane.showMessageDialog(null,"Escribe un nombre.");
          return;
        }
        EquipmentType[] equipment = makeDialog.getEquipment();
        boolean isSaved = complexModel.insertMake(make, equipment);
        if(isSaved){
          JOptionPane.showMessageDialog(makeDialog,"Información guardada correctamente.");
          makeDialog.dispose();
        }else{
          JOptionPane.showMessageDialog(null, "No se ha guardado la información");
        }
      }
    };
  }

  public ActionListener drawList () {
    return new ActionListener() {
      public void actionPerformed (ActionEvent e){
        makeDialog.appendNewList(getEquipmentType());
      }
    };
  }

}
