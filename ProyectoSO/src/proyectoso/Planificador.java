/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 *
 * @author nico
 */
public class Planificador {
    private LinkedList<Vehiculo> fila = new LinkedList();
    private Semaphore semaforo = new Semaphore(1);
    
    public Planificador() {
        
    }
    
    public void recibirAuto(Vehiculo auto){
        try{
            semaforo.acquire();
            fila.add(auto);
            System.out.println("\u001b[32m" + "Planificador:  Vehiculo " + auto.info() + " recibido");
            semaforo.release();
        }catch(InterruptedException ex){
            
        }
    }
    
    public Vehiculo quitarAuto(){
        Vehiculo auto = null;
        
        try{
            semaforo.acquire();
            auto = fila.poll();
            System.out.println("\u001B[34m" + "Planificador: Vehiculo " + auto.info() + " despachado");
            semaforo.release();
        }catch(InterruptedException ex){
            
        }
        
        return auto;
    }
    
}
