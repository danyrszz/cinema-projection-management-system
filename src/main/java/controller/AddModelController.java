package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.cinema.projection.equipment.EquipmentType;

import model.EquipmentModel;
import view.dialogs.AddModelDialog;

public class AddModelController {

  AddModelDialog view;
  EquipmentModel model;

  public AddModelController(AddModelDialog view, EquipmentModel model){
    this.view = view;
    this.model = model;
    view.addListeners(this);
  }

  public void renderDialog (){
    view.setVisible(true);
  }

  private String[] splitModels (String input) {
    String[] arr = input.split(",");
    String[] copy = new String[arr.length];
    for (int i=0 ; i<arr.length; i++) {
      copy[i] = arr[i].trim();
    }
    return copy;
  }

  public ListSelectionListener handleSelection(){
    return new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e){
        if (!e.getValueIsAdjusting()) {//This line prevents double events
          view.updateEquipmentList(view.getBrands().getSelectedValue());
        }
      }
    };
  }

  public ActionListener handleSave(){
    return new ActionListener() {
      public void actionPerformed(ActionEvent e){
        if(view.getModelTxt().getText().isEmpty()){
          JOptionPane.showMessageDialog(null,"El campo de modelos está vacio.");
          return;
        }
        String [] models = splitModels(view.getModelTxt().getText());
        String make = view.getBrands().getSelectedValue();
        String equipment = (String)view.getEquipment().getSelectedItem();
        EquipmentType type = EquipmentType.getEquipmentFromLabel(equipment);
        if(model.insertModel(models,make,type)){
          JOptionPane.showMessageDialog(view, "Todos los modelos guardados correctamente.");
        }else{
          JOptionPane.showMessageDialog(view, "Revisa la información, puede que no se haya guardado todo. Revisa el log de información para más detalles.");
        }
        view.dispose();
      }
    };
  }

}
