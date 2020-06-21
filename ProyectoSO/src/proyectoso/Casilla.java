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
    private boolean bloqueada;
    
    public Casilla(int numeroCasilla, Reloj reloj) {
        this.casilla = numeroCasilla;
        this.reloj = reloj;
        this.fila = new Fila();
        this.bloqueada = false;
    }
    
    public int getIdCasilla(){
        return this.casilla;
    }
    
    public boolean estaBloqueada(){
        return this.bloqueada;
    }
    
    public boolean estaReservada(){
        return this.fila.hayEmergencia();
    }
    
    public int getTiempoRestante(){
        return this.fila.getTiempo() + this.ciclosRestantesVehiculoActual;
    }
    
    //Este código se ejecuta en el hilo del reloj
    public void agregarAuto(Vehiculo auto) throws Exception{
        if(this.estaBloqueada()){
            throw new Exception("Se intenta agregar un vehiculo a casilla bloqueada");
        }
        this.fila.recibirAuto(auto);
    }
    
    //Este código se ejecuta en el hilo del reloj
    public Fila teRompiste(){
        this.bloqueada = true;
        return this.getFila();
    }
    
    public Fila getFila(){
        return this.fila;
    }
    
    //Este código se ejecuta en el hilo del reloj
    public void teArreglaron(){
        this.bloqueada = false;
    }
    
    //Este código se ejecuta en el hilo del reloj
    public void adoptarFila(Fila fila){
        Fila filaNueva = new Fila();
        int cantMaxima = Math.max(fila.getCantidadVehiculos(), this.fila.getCantidadVehiculos());
        for (int i = 0; i < cantMaxima; i++) {
            Vehiculo auto; 
            auto = this.fila.quitarAuto();
            if(auto != null){
                filaNueva.recibirAuto(auto);
            }
            auto = fila.quitarAuto();
            if(auto != null){
                filaNueva.recibirAuto(auto);
            }
        }
        this.fila = filaNueva;
    }
    
    public boolean ocupada(){
        return vehiculoActual != null;
    }
    
    @Override
    public void run () {
        while(!reloj.finDelTiempo()) {
            try {                            
                reloj.empiezaCasilla(casilla);
                
                if (ciclosRestantesVehiculoActual == 0){
                    if (vehiculoActual != null){
                        vehiculoActual.setHoraEgreso(reloj.getContador());
                    }
                    vehiculoActual = this.fila.quitarAuto();
                    if(vehiculoActual != null){
                        ciclosRestantesVehiculoActual = vehiculoActual.ciclos();
                        System.out.println("\u001b[33m" +"Casilla " + casilla + ":     " + vehiculoActual.info() + 
                                " recibido (" + ciclosRestantesVehiculoActual+ "/" +vehiculoActual.ciclos()+") (" + this.fila.getCantidadVehiculos() + " restantes) (Tiempo restante casilla: " + this.getTiempoRestante() + ")");
                        
                        ciclosRestantesVehiculoActual --;
                    }else{
                        if (this.estaBloqueada()){
                            System.out.println("\u001B[31m" + "Casilla " + casilla + ":     Casilla rota");
                        }else{
                            System.out.println("\u001b[33m" + "Casilla " + casilla + ":     Nada para procesar");
                        }
                    }  
                }else{
                    System.out.println("\u001b[33m" + "Casilla " + casilla + ":     procesando a " + vehiculoActual.info() + 
                            "(" + ciclosRestantesVehiculoActual+ "/" +vehiculoActual.ciclos()+") (" + this.fila.getCantidadVehiculos() + " restantes) (Tiempo restante casilla: " + this.getTiempoRestante() + ")");
                    ciclosRestantesVehiculoActual --;
                }
                
                reloj.terminaCasilla(casilla);
            } catch (InterruptedException ex) {
                
            }
        }
    }
}
