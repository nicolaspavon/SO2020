/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso;

import java.util.concurrent.Semaphore;

/**
 *
 * @author useloom
 */
public class Reloj extends Thread {
    private Semaphore s1[];
    private Semaphore s2[];
    
    private int topeCiclos;
    private int contador = 1;

    public Reloj(int topeCiclos, int casillas) {
        this.topeCiclos = topeCiclos;
        s1 = new Semaphore[casillas];
        s2 = new Semaphore[casillas];
        for(int i=0; i<casillas; i++){
            s1[i] = new Semaphore(1);
            s2[i] = new Semaphore(0);
        }
    }
    
    public synchronized boolean finDelTiempo() {
        return contador > topeCiclos;
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
              System.out.println("Cycle " + contador + " ended");        
              contador++;
              for (Semaphore s : s1) {
                  s.release();
              }
            }catch(InterruptedException ex){
        
            }
        }
    }
    
}
