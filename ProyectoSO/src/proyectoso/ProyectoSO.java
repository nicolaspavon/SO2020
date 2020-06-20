/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

/**
 *
 * @author nico
 */
public class ProyectoSO {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        LinkedList<Pair<Integer, Evento>> lista = leer();
        Planificador planificador = new Planificador();
        LinkedList<Casilla> listaCasillas = new LinkedList();
        
        int cantidadCasillas = 5;
        Reloj reloj = new Reloj(400, cantidadCasillas, lista, planificador);
        
        for (int i = 0; i < cantidadCasillas; i++) {
            Casilla casilla = new Casilla(i, reloj);
            listaCasillas.add(casilla);
        }
        
        planificador.tomaCasillas(listaCasillas);
        
        reloj.start();
        listaCasillas.forEach((casilla) -> { casilla.start(); });
        
        
        reloj.join();
        listaCasillas.forEach((casilla) -> { 
            try {
                casilla.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(ProyectoSO.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        LinkedList<Vehiculo> listaVehiculosProc = reloj.getListaVehiculosProcesados();
        for(Vehiculo v : listaVehiculosProc){
            System.out.println(v.info() + " Ingreso en " + v.getHoraIngreso() + " y salio en " + v.getHoraEgreso());
        }
        
        Estadistica est = new Estadistica(listaVehiculosProc);
        est.printEstadisticas();
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
