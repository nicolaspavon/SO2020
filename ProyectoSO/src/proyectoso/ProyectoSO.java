/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso;

import java.util.LinkedList;
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
        LinkedList<Pair<Integer, Evento>> lista = leer();
        Planificador planificador = new Planificador();
        LinkedList<Casilla> listaCasillas = new LinkedList();
        
        int cantidadCasillas = 2;
        Reloj reloj = new Reloj(50, cantidadCasillas, lista, planificador);
        
        for (int i = 0; i < cantidadCasillas; i++) {
            Casilla casilla = new Casilla(i, reloj);
            listaCasillas.add(casilla);
        }
        
        planificador.tomaCasillas(listaCasillas);
        
        reloj.start();
        listaCasillas.forEach((casilla) -> { casilla.start(); });
    }
    
    static public LinkedList<Pair<Integer, Evento>> leer(){
        Scanner in = new Scanner(System.in);
        LinkedList<Pair<Integer, Evento>> list = new LinkedList<>();
        boolean endInput = false;
        do{
          String line = in.nextLine();
          line = line.trim();
          if(line.equals("*")){
            endInput = true;
          }else{
            String[] datos = line.split(";");
            list.add(
                new Pair<>(Integer.parseInt(datos[0]), Evento.parsear(datos[1],datos[2])));
          }           
        }while(!endInput);
        return list;
    }
}
