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
public class ProyectoSO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Reloj reloj = new Reloj(5, 3);
        Casilla casilla0 = new Casilla(0, reloj);
        Casilla casilla1 = new Casilla(1, reloj);
        Casilla casilla2 = new Casilla(2, reloj);
        
        reloj.start();
        casilla0.start();
        casilla1.start();
        casilla2.start();
    }
    
}
