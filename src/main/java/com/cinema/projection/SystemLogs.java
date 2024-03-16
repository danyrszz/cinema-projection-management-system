package com.cinema.projection;

import java.util.ArrayList;
import java.util.List;

public class SystemLogs {

  private static ArrayList<String> log = new ArrayList<>();

  public static void addToLog(String text){
    log.add(text);
  }

  public static String getLastLine(){
    return log.get(log.size()-1);
  }

  //devuelve las x ultimas filas del log
  public static List<String> getLines (int x){
    int end = log.size()-1;
    int start = end - x;
    return log.subList(start, end);
  }

  public static ArrayList<String> getLog() {
    return log;
  }

  public static void printLog (){
    for (String string : log) {
      System.out.println(string + "\n");
    }
  }

  public static void clearLog(){
    log.clear();
  }

}
