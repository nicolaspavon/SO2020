/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso;

/**
 *
 * @author useloom
 */
public class Casilla extends Thread {
    private int casilla;
    private Reloj reloj;
    private Planificador planif;
    private Vehiculo vehiculoActual = null;
    private int ciclosRestantesVehiculoActual = 0;
    
    public Casilla(int numeroCasilla, Reloj reloj, Planificador planif) {
        this.planif = planif;
        this.casilla = numeroCasilla;
        this.reloj = reloj;
    }
    
    @Override
    public void run () {
        while(!reloj.finDelTiempo()) {
            try {                            
                reloj.empiezaCasilla(casilla);
                
                if (ciclosRestantesVehiculoActual == 0){
                    vehiculoActual = this.planif.quitarAuto();
                    if(vehiculoActual != null){
                        ciclosRestantesVehiculoActual = vehiculoActual.ciclos();
                        System.out.println("\u001b[33m" +"Casilla " + casilla + ":     " + vehiculoActual.info() + 
                                " recibido (" + ciclosRestantesVehiculoActual+ "/" +vehiculoActual.ciclos()+")");
                        
                        ciclosRestantesVehiculoActual --;
                    }else{
                        System.out.println("\u001b[33m" + "Casilla " + casilla + ":     Nada para procesar");
                    }  
                }else{
                    System.out.println("\u001b[33m" + "Casilla " + casilla + ":     procesando a " + vehiculoActual.info() + 
                            "(" + ciclosRestantesVehiculoActual+ "/" +vehiculoActual.ciclos()+")");
                    ciclosRestantesVehiculoActual --;
                }
                


                reloj.terminaCasilla(casilla);
            } catch (InterruptedException ex) {
                
            }
        }
    }
}
