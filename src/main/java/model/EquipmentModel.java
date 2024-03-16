package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.cinema.projection.SystemLogs;
import com.cinema.projection.Complex;
import com.cinema.projection.equipment.Amplifier;
import com.cinema.projection.equipment.EquipmentType;
import com.cinema.projection.equipment.GenericEquipment;
import com.cinema.projection.equipment.Piece;

public class EquipmentModel extends DBConnection{

  Connection connection = getConnection();

  public boolean insertEquipment (Complex complex, GenericEquipment equipment){
    return false;
  }

  public boolean insertAmplifier (Complex complex, Amplifier amplifier){
    return false;
  }

  //agrega una pieza a un equipo en especifico
  public boolean insertPiece (Complex complex, Piece piece, GenericEquipment equipment){
    return false;
  }


  //agrega nueva pieza a la lista general
  public boolean insertNewPiece ( int equipmentType, int pieceType ){

    return false;
  }

  public boolean insertModel ( String[] model, String make, EquipmentType equipment ) {
    //devolvera true si TODOS los modelos escritos se han insertado.
    boolean status = false;
    int makeID, equipmentID;
    String queryMk = "select makeid from make where name=?";
    String queryEq = "select typeid from equipmenttype where name=?";
    String queryModel = "insert into model (name, makeid, equipmenttypeid) values (?,?,?)";

    for (String currentModel : model) {
      if(modelExists(currentModel, make)){
        SystemLogs.addToLog("El modelo " + currentModel + " ya existe en la base de datos.");
        status = false;
        continue;
      }
      try (
        PreparedStatement stMake = connection.prepareStatement(queryMk);
        PreparedStatement stEquipment = connection.prepareStatement(queryEq);
        PreparedStatement stInsert = connection.prepareStatement(queryModel);)
        {
          connection.setAutoCommit(false);
          //-----sacar los ids necesarios
          stMake.setString(1, make);
          stEquipment.setString(1, equipment.toString());
          // recupera id de make
          ResultSet makeRs = stMake.executeQuery(); makeRs.next();
          makeID = makeRs.getInt("makeid");
          // recupera id de equipment
          ResultSet equipmentRs = stEquipment.executeQuery(); makeRs.next();
          equipmentID = equipmentRs.getInt("typeid");
          // inserta modelo
          stInsert.setString(1, currentModel);
          stInsert.setInt(2, makeID);
          stInsert.setInt(3, equipmentID);
          stInsert.executeUpdate();
          connection.commit();
          SystemLogs.addToLog("El modelo " + currentModel + " ha sido guardado correctamente.");
      } catch (Exception e) {
        e.printStackTrace();
        SystemLogs.addToLog("Ha habido un error insertando el modelo "+currentModel);
        status = false;
      }
      status = true;
    }
    return status;
  }

  public ArrayList<String> selectMakeList (){
    String query = "select * from make";
    ArrayList<String> list = new ArrayList<>();
    try (Statement st = connection.createStatement(); ) {
      ResultSet result = st.executeQuery(query);
      while(result.next()){
        list.add(result.getString("name"));
      }
      return list;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  public ArrayList<String> selectModelList (EquipmentType equipment){
    return null;
  }

  public ArrayList<EquipmentType> selectEquipmentType () {
    String query = "SELECT name FROM EquipmentType order by name asc";
    ArrayList<EquipmentType> elements = new ArrayList<>();
    try(Statement st = connection.createStatement()){
      ResultSet results = st.executeQuery(query);
      while(results.next()){
        EquipmentType equipment = EquipmentType.valueOf(results.getString("name"));
        elements.add(equipment);
      }
      return elements;
    }catch(SQLException e){
      e.printStackTrace();
      return null;
    }
  }

  public ArrayList<EquipmentType> selectEquipmentRelatedToMake ( String make ){
    String query = "select make.name as make, equipmenttype.name as equipment\r\n" +
    "from equipmenttype_make\r\n" +
    "inner join make on make.makeID = equipmenttype_make.MakeID\r\n" +
    "inner join equipmenttype on equipmenttype.typeID = equipmenttype_make.EquipmentTypeID\r\n" +
    "where make = ? ";
    ArrayList<EquipmentType> equipment = new ArrayList<>();
    try(PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setString(1, make);
      ResultSet results = statement.executeQuery();
      while (results.next()) {
        EquipmentType e = EquipmentType.valueOf( results.getString("equipment") );
        equipment.add(e);
      }
      return equipment;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  public void selectPieceType () {}

  private boolean modelExists (String model, String make) {
    String query = "select model.name as model, make.name as make from\r\n" +
    "model\r\n" +
    "inner join make on model.MakeID = make.makeID\r\n" +
    "where model = ? and make=?";
    try (PreparedStatement st = connection.prepareStatement(query);) {
      st.setString(1, model);
      st.setString(2, make);
      ResultSet result = st.executeQuery();
      if (!result.isBeforeFirst() ) {
        return false;
      }
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

}
