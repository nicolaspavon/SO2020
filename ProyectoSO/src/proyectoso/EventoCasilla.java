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
public class EventoCasilla extends Evento{
    private int id;
    private String tipo;
    
    public EventoCasilla (String id, String tipo){
        this.id = Integer.parseInt(id);
        this.tipo = tipo;
    }
    
    public int getId(){
        return this.id;
    }
    
    public String getTipo(){
        return this.tipo;
    }
}
