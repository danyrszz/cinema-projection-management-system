package model;

import com.cinema.projection.CinemaConcept;
import com.cinema.projection.CinemaManagement;
import com.cinema.projection.Complex;
import com.cinema.projection.Room;
import com.cinema.projection.SystemLogs;
import com.cinema.projection.equipment.EquipmentType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ComplexModel extends DBConnection {

  Connection connection = getConnection();

  public boolean insertComplex (Complex complex) {
    String insertComplexQuery =
      "INSERT INTO Complex (name, state, city, concept) values (?,?,?,?)";
    String insertRoomsQuery =
      "INSERT INTO Room (number, isWorking, isComplete, complexID) values (?,?,?,?)";
    try(
      PreparedStatement insertComplex = connection.prepareStatement(insertComplexQuery, Statement.RETURN_GENERATED_KEYS);
      PreparedStatement insertRooms = connection.prepareStatement(insertRoomsQuery))
      {
      insertComplex.setString(1, complex.getName());
      insertComplex.setString(2, complex.getState());
      insertComplex.setString(3, complex.getCity());
      insertComplex.setString(4, complex.getConcept().toString());
      insertComplex.executeUpdate();

      ResultSet keys = insertComplex.getGeneratedKeys();
      keys.next();
      int key = keys.getInt(1);

      for(int i=0; i<complex.getTotalRooms(); i++){
        insertRooms.setInt(1, i+1);
        insertRooms.setBoolean(2, true);
        insertRooms.setBoolean(3, false);
        insertRooms.setInt(4, key);
        insertRooms.executeUpdate();
      }
      return true;
    }catch(SQLException e){
      e.printStackTrace();
      return false;
    }
  }

  public boolean selectAllComplexes (CinemaManagement cm) {
    String queryComplex =
    "select complex.complexid, complex.name, complex.city, complex.state, concept, count(room.number) as totalRooms\r\n" + //
    "from complex\r\n" +
    "inner join room on complex.complexID = room.complexID\r\n" +
    "group by complex.complexid";

    String queryRooms =
    "select roomID, number, isworking, iscomplete\r\n" +
    "from room\r\n" +
    "where complexid = ?";

    try(
      Statement selectComplexes = connection.createStatement();
      PreparedStatement selectRooms = connection.prepareStatement(queryRooms)){
      connection.setAutoCommit(false);
      ResultSet complexResults = selectComplexes.executeQuery(queryComplex);

      while (complexResults.next()) {
        //añadir el complejo
        CinemaConcept concept = CinemaConcept.valueOf(complexResults.getString("concept"));
        Complex complex = new Complex(
          complexResults.getString("name"),
          complexResults.getString("city"),
          complexResults.getString("state"),
          concept,
          complexResults.getInt("totalRooms"));
        complex.setId(complexResults.getInt("complexID"));
        cm.addComplex(complex);

        //añadir las salas de ese complejo
        selectRooms.setInt(1, complexResults.getInt("complexID"));
        selectRooms.execute();
        ResultSet roomsResult = selectRooms.getResultSet();
        while(roomsResult.next()){
          Room room = new Room(
            roomsResult.getInt("roomID"),
            roomsResult.getInt("number"),
            complex,
            roomsResult.getBoolean("isComplete"),
            roomsResult.getBoolean("isWorking"));
          complex.addRoom(room);
        }
      }
      connection.commit();
      return true;

    }catch(SQLException e){
      e.printStackTrace();
      return false;
    }
  }

  public boolean insertMake (String make, EquipmentType... type) {
    String query = "insert into equipmenttype_make (equipmenttypeid, makeid) values (?,?)";
    int makeID = insertMake(connection, make);
    if( makeID<=0 ) return false;
    for (EquipmentType element : type){
      int typeID = getEquipmentTypeID(connection, element);
      System.out.println(typeID);
      try (PreparedStatement st = connection.prepareStatement(query)) {
        st.setInt(1, typeID);
        st.setInt(2, makeID);
        st.executeUpdate();
      } catch (Exception e) {
        e.printStackTrace();
        SystemLogs.addToLog("Ha habido un error dando de alta los equipos asociados a la marca.");
        return false;
      }
    }
    return true;
  }

  private int insertMake (Connection con, String make) {
    //inserta marca en la bd y devuelve el id de esa marca.
    String query = "insert into make (name) values (?)";
    try(PreparedStatement st = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
      st.setString(1, make);
      st.executeUpdate();
      ResultSet keys = st.getGeneratedKeys();
      keys.next();
      SystemLogs.addToLog("Marca "+make+" guardada correctamente.");
      return keys.getInt(1);
    } catch (Exception e) {
      e.printStackTrace();
      SystemLogs.addToLog("Revisa que la marca "+make+" no se encuentre ya registrada.");
      return -1;
    }
  }

  private int getEquipmentTypeID (Connection con, EquipmentType type) {
    String query = "select typeid from equipmenttype where name=?";
    try(PreparedStatement typeStmn = con.prepareStatement(query)){
      typeStmn.setString(1, type.toString());
      typeStmn.execute();
      ResultSet result = typeStmn.getResultSet();
      result.next();
      return result.getInt("typeid");
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  public int getEquipmentTypeID (String equipment){
    EquipmentType selection = EquipmentType.valueOf(equipment);
    return getEquipmentTypeID(connection, selection);
  }



}
