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
public class Evento {
    
    public static Evento parsear(String name,String tipoV) {
        switch(tipoV) {
            case "rotura": 
                RoturaCasilla casilla = new RoturaCasilla(name);
                return casilla;
            default: 
                return Vehiculo.parsear(name, tipoV);
                
        }     
        
    }
    
}
