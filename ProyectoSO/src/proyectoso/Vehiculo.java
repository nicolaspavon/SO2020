/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso;

/**
 *
 * @author nico
 */

public class Vehiculo extends Evento{
    public enum TipoVehiculo { 
        Auto, Omnibus, Camion, Emergencia, Camioncito
    };
    
    private String matricula;
    private TipoVehiculo tipo;
    private int tiempo;
    private int prioridad;
    private int horaIngresoPeaje;
    private int horaEgresoPeaje;
    
    public Vehiculo (String matricula, TipoVehiculo tipo, int tiempo, int prioridad){
        this.tiempo = tiempo;
        this.matricula = matricula;
        this.tipo = tipo;
        this.prioridad = prioridad;
    }
    
    public static Vehiculo parsear(String name,String tipoV) {
        TipoVehiculo tipo;
        int tiempo;
        int prioridad;
        switch(tipoV) {
            case "auto": 
                tipo = TipoVehiculo.Auto; 
                tiempo = 4;
                prioridad = 2;
            break;
            case "omnibus": 
                tipo = TipoVehiculo.Omnibus; 
                tiempo = 6;
                prioridad = 1;
            break;
            case "camion": 
                tipo = TipoVehiculo.Camion;
                tiempo = 7;
                prioridad = 4;
            break;
            case "camioncito": 
                tipo = TipoVehiculo.Camioncito; 
                tiempo = 5;
                prioridad = 3;
            break;
            case "emergencia": 
                tipo = TipoVehiculo.Emergencia; 
                tiempo = 2;
                prioridad = 0;
            break;
            default: throw new Error("Tipo vehiculo desconocido: " + tipoV);
        }
                
        Vehiculo v = new Vehiculo(name, tipo, tiempo, prioridad);
        return v;
    }
    
    public String info(){
        return this.matricula;
    }
    
    public void setHoraIngreso(int hora){
        this.horaIngresoPeaje = hora;
    }
    
    public void setHoraEgreso(int hora){
        this.horaEgresoPeaje = hora;
    }
    
    public int getHoraEgreso(){
        return this.horaEgresoPeaje;
    }
    
    public int getHoraIngreso(){
        return this.horaIngresoPeaje;
    }
    
    public TipoVehiculo getTipo(){
        return this.tipo;
    }
    
    public int ciclos(){
        return this.tiempo;
    }
    
    public int getPrioridad(){
        return this.prioridad;
    }
    
    public boolean esEmergencia(){
        return this.tipo == TipoVehiculo.Emergencia;
    }
}
