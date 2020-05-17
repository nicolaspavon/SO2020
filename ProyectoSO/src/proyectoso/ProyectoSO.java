/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso;

import java.util.ArrayList;
import java.util.LinkedList;
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
        LinkedList<Pair<Integer, Vehiculo>> lista = leer();
        Planificador planificador = new Planificador();
        System.out.println(lista.size());
        
        Reloj reloj = new Reloj(5, 3, lista, planificador);
        Casilla casilla0 = new Casilla(0, reloj, planificador);
        Casilla casilla1 = new Casilla(1, reloj, planificador);
        Casilla casilla2 = new Casilla(2, reloj, planificador);
        
        reloj.start();
        casilla0.start();
        casilla1.start();
        casilla2.start();
    }
    
    static public LinkedList<Pair<Integer, Vehiculo>> leer(){
        Scanner in = new Scanner(System.in);
        LinkedList<Pair<Integer, Vehiculo>> list = new LinkedList<>();
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
