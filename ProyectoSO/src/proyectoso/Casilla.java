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
    private Planificador planif;
    
    public Casilla(int numeroCasilla, Reloj reloj, Planificador planif) {
        this.planif = planif;
        this.casilla = numeroCasilla;
        this.reloj = reloj;
    }
    
    @Override
    public void run () {
        while(!reloj.finDelTiempo()) {
            try {                            
                reloj.empiezaCasilla(casilla);
                
                Vehiculo auto = this.planif.quitarAuto();      
                String info = "nadie";
                if(auto != null){
                    info = auto.info();
                }                

                System.out.println(
                    "La casilla " + casilla + " trabajo en ciclo " + reloj.getContador() + " y procesó a " + info);

                reloj.terminaCasilla(casilla);
            } catch (InterruptedException ex) {
                
            }
        }
    }
}
