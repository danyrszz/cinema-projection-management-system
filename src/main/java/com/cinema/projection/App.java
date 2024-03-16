package com.cinema.projection;

import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.plaf.basic.BasicSliderUI.ActionScroller;

import com.cinema.projection.equipment.EquipmentType;

import controller.AddMakeController;
import controller.AddModelController;
import model.EquipmentModel;
import view.dialogs.AddMakeDialog;
import view.dialogs.AddModelDialog;

public class App
{
    public static void main( String[] args )
    {
        // AddMakeDialog dialog = new AddMakeDialog();
        // AddMakeController c = new AddMakeController(dialog);
        // c.renderDialog();  


        EquipmentModel e = new EquipmentModel();
        // ArrayList<EquipmentType> arr = e.selectEquipmentRelatedToMake("Barco");
        //System.out.println(SystemLogs.getLastLine());
        AddModelDialog frame = new AddModelDialog(e);
        AddModelController c = new AddModelController(frame, e);
        c.renderDialog();
    }
}
