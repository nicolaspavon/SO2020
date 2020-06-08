/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 *
 * @author nico
 */
public class Planificador {
    private LinkedList<Casilla> listaCasillas = new LinkedList();    
    public Planificador() {
    }
    
    public void tomaCasillas(LinkedList<Casilla> casillas){
        this.listaCasillas = casillas;
    }
    
    private void ordenarCasillas(){
        this.listaCasillas.sort((casilla1, casilla2) -> {
            return casilla1.getTiempoRestante() - casilla2.getTiempoRestante();
        });
    }
    
    public void recibirAuto(Vehiculo auto){
        this.ordenarCasillas();
        Casilla primerCasilla = listaCasillas.getFirst();
        primerCasilla.agregarAuto(auto);
        System.out.println("\u001b[32m" + "Planificador:  Vehiculo " + auto.info() + " recibido e insertado en casilla: " + primerCasilla.getIdCasilla());
    }
}
