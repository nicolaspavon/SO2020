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

public class Vehiculo {
    public enum TipoVehiculo { 
        Auto, Omnibus, Camion, Emergencia, Camionsito
    };
    
    private String matricula;
    private TipoVehiculo tipo;
    private int tiempo;
    
    public Vehiculo (String matricula, TipoVehiculo tipo, int tiempo) {
        this.tiempo = tiempo;
        this.matricula = matricula;
        this.tipo = tipo;
    }
    
    public static Vehiculo parsear(String raw) {
        String[] datos = raw.split(",");
        TipoVehiculo tipo;
        int tiempo;
        switch(datos[1]) {
            case "auto": 
                tipo = TipoVehiculo.Auto; 
                tiempo = 4;
            break;
            case "omnibus": 
                tipo = TipoVehiculo.Omnibus; 
                tiempo = 6;
            break;
            case "camion": 
                tipo = TipoVehiculo.Camion;
                tiempo = 7;
            break;
            case "camionsito": 
                tipo = TipoVehiculo.Camionsito; 
                tiempo = 5;
            break;
            case "emergencia": 
                tipo = TipoVehiculo.Emergencia; 
                tiempo = 2;
            break;
            default: throw new Error("Tipo vehiculo desconocido " + raw);
        }
                
        Vehiculo v = new Vehiculo(datos[0], tipo, tiempo);
        return v;
    }
    
    public String info(){
        return this.matricula;
    }
    
    public int ciclos(){
        return this.tiempo;
    }
}
