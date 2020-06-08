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
    private Vehiculo vehiculoActual = null;
    private int ciclosRestantesVehiculoActual = 0;
    private Fila fila;
    
    public Casilla(int numeroCasilla, Reloj reloj) {
        this.casilla = numeroCasilla;
        this.reloj = reloj;
        this.fila = new Fila();
    }
    
    public int getIdCasilla(){
        return this.casilla;
    }
    
    public int getTiempoRestante(){
        return this.fila.getTiempo() + this.ciclosRestantesVehiculoActual;
    }
    
    //Este código se ejecuta en el hilo del reloj
    public void agregarAuto(Vehiculo auto){
        this.fila.recibirAuto(auto);
    }
    
    @Override
    public void run () {
        while(!reloj.finDelTiempo()) {
            try {                            
                reloj.empiezaCasilla(casilla);
                
                if (ciclosRestantesVehiculoActual == 0){
                    vehiculoActual = this.fila.quitarAuto();
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
