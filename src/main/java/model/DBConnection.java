package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

  private Connection con;
  private String path = "jdbc:sqlite:cinema.db";

  //conectar a la base de datos local
  protected Connection getConnection () {
    try {
      con = DriverManager.getConnection(path);
      return con;
    }catch(SQLException e ){
      e.printStackTrace();
      return null;
    }
  }

  protected void closeConnection () {
    try{
      if(con!=null) con.close();
      System.out.println("Conexion cerrada");
    }catch(SQLException e){
      e.printStackTrace();
    }
  }

}
