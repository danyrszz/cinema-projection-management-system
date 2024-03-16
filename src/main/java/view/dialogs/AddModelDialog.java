package view.dialogs;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;

import com.cinema.projection.equipment.EquipmentType;
import controller.AddModelController;
import model.EquipmentModel;
import net.miginfocom.swing.MigLayout;

public class AddModelDialog extends JDialog {
  private JPanel wrapper = new JPanel(new MigLayout(
    "wrap 2",
    "[15][250]",
    ""
    ));
  private JTextField modelTxt = new JTextField();
  private JList<String> brands= new JList<>();
  private JComboBox<String> equipment = new JComboBox<>();
  private JButton save = new JButton("Guardar");
  private EquipmentModel model;

  public AddModelDialog(EquipmentModel model){
    this.model = model;
    setTitle("Registrar modelo");
    setResizable(false);
    populateBrands();
    brands.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    brands.setSelectedIndex(0);
    updateEquipmentList(brands.getSelectedValue());
    wrapper.add(new JLabel("Modelo(s):"));
    wrapper.add(modelTxt, "grow");
    wrapper.add(new JLabel("Marca:"));
    wrapper.add(new JScrollPane(brands), "grow");
    wrapper.add(new JLabel("Equipo:"));
    wrapper.add(equipment, "grow");
    wrapper.add(save, "span, align center");
    //añadir contenedor
    add(wrapper);
    pack();
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }

  public void addListeners (AddModelController c) {
    brands.addListSelectionListener(c.handleSelection());
    save.addActionListener(c.handleSave());
  }

  private void populateBrands (){
    DefaultListModel<String> lm = new DefaultListModel<>();
    ArrayList<String> data = model.selectMakeList();
    for (String element : data) {
      lm.addElement(element);
    }
    brands.setModel(lm);
  }

  public void updateEquipmentList (String brand){
    equipment.removeAllItems();
    ArrayList<EquipmentType> eq = model.selectEquipmentRelatedToMake(brand);
    for (EquipmentType element : eq) {
      equipment.addItem(element.getLabel());
    }
    equipment.updateUI();
  }

  public JList<String> getBrands() {
    return brands;
  }
  public JButton getSave() {
    return save;
  }
  public JComboBox<String> getEquipment() {
    return equipment;
  }
  public JTextField getModelTxt() {
    return modelTxt;
  }
}
