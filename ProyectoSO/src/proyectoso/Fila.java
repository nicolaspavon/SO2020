/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso;

import java.util.LinkedList;

/**
 *
 * @author nico
 */
public class Fila {
    private int tiempoDeEspera;
    private LinkedList<Vehiculo> vehiculos;
    
    public Fila() {
        this.tiempoDeEspera = 0;
        this.vehiculos = new LinkedList();
    }
    
    public int getTiempo(){
        return this.tiempoDeEspera;
    }
    
    public void recibirAuto(Vehiculo auto){
        vehiculos.add(auto);
        this.tiempoDeEspera += auto.ciclos();
    }
    
    public int getCantidadVehiculos(){
        return this.vehiculos.size();
    }
    
    public boolean hayEmergencia(){
        for(Vehiculo v : vehiculos){
            if (v.esEmergencia()){
                return true;
            }
        }
        return false;
    }
    
    public Vehiculo quitarAuto(){
        Vehiculo auto = null;
        auto = vehiculos.poll();
        if (auto != null){
            tiempoDeEspera -= auto.ciclos();
        }
        return auto;
    }
    
    
}
