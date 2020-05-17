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
        
        int cantidadCasillas = 5;
        Reloj reloj = new Reloj(20, cantidadCasillas, lista, planificador);
        
        reloj.start();
        for (int i = 0; i < cantidadCasillas; i++) {
            Casilla casilla = new Casilla(i, reloj, planificador);
            casilla.start();
        }
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
