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
public class RoturaCasilla extends Evento{
    private int id;
    
    public RoturaCasilla (String id){
        this.id = Integer.parseInt(id);
    }
    
    public int getId(){
        return this.id;
    }
}
