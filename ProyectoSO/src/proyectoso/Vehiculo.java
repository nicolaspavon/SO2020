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
        Auto, Omnibus 
    };
    
    private String matricula;
    private TipoVehiculo tipo;
    
    public Vehiculo (String matricula, TipoVehiculo tipo) {
        this.matricula = matricula;
        this.tipo = tipo;
    }
    
    public static Vehiculo parsear(String raw) {
        String[] datos = raw.split(",");
        TipoVehiculo tipo;
        switch(datos[1]) {
            case "auto": tipo = TipoVehiculo.Auto; break;
            case "omnibus": tipo = TipoVehiculo.Omnibus; break;
            default: throw new Error("Tipo vehiculo desconocido " + raw);
        }
                
        Vehiculo v = new Vehiculo(datos[0], tipo);
        return v;
    }
    
    public String info(){
        return this.matricula;
    }
}
