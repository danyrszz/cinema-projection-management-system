package view.dialogs;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;

import com.cinema.projection.equipment.EquipmentType;
import controller.AddMakeController;
import view.Sizes;

public class AddMakeDialog extends JDialog {

  private JScrollPane scrollpane = new JScrollPane();
  private JPanel wrapper = new JPanel();
  private JPanel elementsContainer = new JPanel();
  private JPanel buttonsContainer = new JPanel();
  private JTextField makeTxt = new JTextField();
  private JButton save = new JButton("Guardar");
  private JButton add = new JButton("Otro equipo...");

  private ArrayList<JComboBox<String>> menus = new ArrayList<>();

  public AddMakeDialog(){

    setTitle("Registrar marca");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setMinimumSize(new Dimension(380,0));
    setResizable(false);
    wrapper.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
    elementsContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    elementsContainer.setLayout(new GridLayout(0,2, 5,5));
    scrollpane.setPreferredSize(new Dimension(0, 100));

    scrollpane.setViewportView(elementsContainer);
    add(wrapper);
    wrapper.add(scrollpane);
    wrapper.add(buttonsContainer);

    makeTxt.setPreferredSize(Sizes.TEXT_FIELD_SIZE);
    elementsContainer.add(new JLabel("Nombre de la marca"));
    elementsContainer.add(makeTxt);

    buttonsContainer.add(save);
    buttonsContainer.add(add);
    pack();
    setLocationRelativeTo(null);
  }

  public void setListeners (AddMakeController c) {
    save.addActionListener(c.save());
    add.addActionListener(c.drawList());
  }

  public void appendNewList(ArrayList<EquipmentType> options){
    JComboBox<String> menu = new JComboBox<>();
    for (EquipmentType element : options) {
      menu.addItem(element.getLabel());
    }
    elementsContainer.add(new JLabel("Equipo"));
    elementsContainer.add(menu);
    menus.add(menu);
    elementsContainer.updateUI();
  }

  public String getMakeText () {
    return makeTxt.getText();
  }

  public EquipmentType[] getEquipment(){
    EquipmentType[] equipment = new EquipmentType[menus.size()];
    for (int i=0; i<equipment.length; i++){
      equipment[i] = EquipmentType.getEquipmentFromLabel((String)menus.get(i).getSelectedItem());
    }
    return equipment;
  }

}