package com.cinema.projection.equipment;

public class Exchange {

    //cambiar proyector de un cine a otro
  // public void exchangeProjector (Complex complexOrigin, Complex complexDestiny, int roomOrigin, int roomDestiny) {
  //   try{
  //     //obtener ambos proyectores
  //     Projector originalProjector = complexOrigin.getRoom(roomOrigin).getProjector();
  //     Projector targetProjector = complexDestiny.getRoom(roomDestiny).getProjector();

  //     //eliminar proyectores de sus salas
  //     complexOrigin.getRoom(roomOrigin).removeProjector();
  //     complexDestiny.getRoom(roomDestiny).removeProjector();

  //     //agregar cada proyector a sus nuevos complejos
  //     complexDestiny.getRoom(roomDestiny).addExistingProjector(originalProjector);
  //     originalProjector.setCurrentRoom(complexDestiny.getRoom(roomDestiny));
      
  //     complexOrigin.getRoom(roomOrigin).addExistingProjector(targetProjector);
  //     targetProjector.setCurrentRoom(complexOrigin.getRoom(roomOrigin));
  //   }catch(Exception e){
  //     e.printStackTrace();
  //   }
  // }

  // //cambiar proyector de una sala a otra del mismo cine
  // public void exchangeProjector(Complex complex, int origin, int destiny){
  //   try{
  //     Projector originalProjector = complex.getRoom(origin).getProjector();
  //     Projector targetProjector = complex.getRoom(destiny).getProjector();
  //     originalProjector.setCurrentRoom(complex.getRoom(destiny));
  //     targetProjector.setCurrentRoom(complex.getRoom(origin));
  //   }catch(Exception e){
  //     System.err.println(e);
  //   }
  // }

  // //intercambiar servidor a otro complejo
  // public void exchangeServer (Complex complexOrigin, Complex complexDestiny, int roomOrigin, int roomDestiny) {
  //   try{
  //     Server originalServer = complexOrigin.getRoom(roomOrigin).getServer();
  //     Server targetServer = complexOrigin.getRoom(roomDestiny).getServer();

  //     complexOrigin.getRoom(roomOrigin).removeServer();
  //     complexDestiny.getRoom(roomDestiny).removeServer();

  //     complexDestiny.getRoom(roomDestiny).addExistingServer(originalServer);
  //     originalServer.setCurrentRoom(complexDestiny.getRoom(roomDestiny));

  //     complexOrigin.getRoom(roomOrigin).addExistingServer(targetServer);
  //     targetServer.setCurrentRoom(complexOrigin.getRoom(roomOrigin));
  //   }catch(Exception e){
  //     System.err.println(e);
  //   }
  // }

  // //intercambiar servidor a diferente sala del mismo complejo
  // public void exchangeServer (Complex complex, int origin, int destiny) {
  //   try{
  //     Server originalServer = complex.getRoom(origin).getServer();
  //     Server targetServer = complex.getRoom(destiny).getServer();
  //     originalServer.setCurrentRoom(complex.getRoom(destiny));
  //     targetServer.setCurrentRoom(complex.getRoom(origin));
  //   }catch(Exception e){
  //     System.err.println(e);
  //   }
  // }

}
