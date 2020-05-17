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
    
    public Casilla(int numeroCasilla, Reloj reloj) {
        this.casilla = numeroCasilla;
        this.reloj = reloj;
    }
    
    @Override
    public void run () {
        while(!reloj.finDelTiempo()) {
            try {                            
                reloj.empiezaCasilla(casilla);

                System.out.println(
                    "La casilla " + casilla + " trabajo en ciclo " + reloj.getContador());

                reloj.terminaCasilla(casilla);
            } catch (InterruptedException ex) {
                
            }
        }
    }
}
