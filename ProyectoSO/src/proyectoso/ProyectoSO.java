/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.util.Pair;

/**
 *
 * @author nico
 */
public class ProyectoSO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Pair<Integer, Vehiculo>> lista = leer();
        
        Reloj reloj = new Reloj(5, 3);
        Casilla casilla0 = new Casilla(0, reloj);
        Casilla casilla1 = new Casilla(1, reloj);
        Casilla casilla2 = new Casilla(2, reloj);
        
        reloj.start();
        casilla0.start();
        casilla1.start();
        casilla2.start();
    }
    
    static public List<Pair<Integer, Vehiculo>> leer(){
        Scanner in = new Scanner(System.in);
        List<Pair<Integer, Vehiculo>> list = new ArrayList<>();
        boolean endInput = false;
        do{
          String line = in.nextLine();
          line = line.trim();
          if(line.equals("*")){
            endInput = true;
          }else{
            String[] datos = line.split("-");
            list.add(
                new Pair<>(Integer.parseInt(datos[0]), Vehiculo.parsear(datos[1])));
          }           
        }while(!endInput);
    return list;
  }
}
