/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import javafx.util.Pair;

/**
 *
 * @author useloom
 */
public class Reloj extends Thread {
    private Semaphore s1[];
    private Semaphore s2[];
    LinkedList<Pair<Integer, Evento>> listaEventos;
    LinkedList<Vehiculo> listaVehiculosProcesados = new LinkedList<Vehiculo>();
    private Planificador planificador;
    
    private int topeCiclos;
    private int contador = 0;

    public Reloj(int casillas, LinkedList lista, Planificador planificador) {
        this.planificador = planificador;
        this.listaEventos = lista;
        this.topeCiclos = topeCiclos;
        s1 = new Semaphore[casillas];
        s2 = new Semaphore[casillas];
        for(int i=0; i<casillas; i++){
            s1[i] = new Semaphore(0);
            s2[i] = new Semaphore(1);
        }
    }
   
    public synchronized boolean finDelTiempo() {
        if (listaEventos.isEmpty() && planificador.casillasFinalizadas()){
            return true;
        }
        return false;
    }
    
    public LinkedList<Vehiculo> getListaVehiculosProcesados(){
        return this.listaVehiculosProcesados;
    }
    
    public synchronized int getContador() {
        return contador;
    }
    
    public void empiezaCasilla(int casilla) throws InterruptedException {
        s1[casilla].acquire();
    }
    
    public void terminaCasilla(int casilla) throws InterruptedException {
        s2[casilla].release();
    }
    
    @Override
    public void run() {
        while(!finDelTiempo()){
            try{
              for(Semaphore s : s2) {
                  s.acquire();
              }    
              
              contador++;
              System.out.println("");
              System.out.println("\u001b[37m" + "Reloj:         CICLO " + contador + " INICIADO");        
              
              while (!listaEventos.isEmpty() && listaEventos.peek().getKey() == this.contador){
                Evento evento = listaEventos.pop().getValue();
                if (evento instanceof Vehiculo){
                    Vehiculo auto = (Vehiculo)evento;
                    auto.setHoraIngreso(contador);
                    this.planificador.aniadirVehiculoActual(auto);
                    this.listaVehiculosProcesados.add(auto);
                } else {
                    this.planificador.eventoCasilla((EventoCasilla)evento);
                }
                
              }
              
              this.planificador.asignarVehiculosACasillas();
              for (Semaphore s : s1) {
                  s.release();
              }
            }catch(InterruptedException ex){
                System.out.println(ex.toString());
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        
        
    }
    
}
