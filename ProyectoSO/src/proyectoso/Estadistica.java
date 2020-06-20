/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso;

import java.util.LinkedList;

/**
 *
 * @author nico
 */
public class Estadistica {
    
    LinkedList<Vehiculo> vehiculos;
    private int totalEmergencias = 0;
    private int totalAutos = 0;
    private int totalOmnibus = 0;
    private int totalCamionsitos = 0;
    private int totalCamiones = 0;
    
    private int tiempoTotalEmergencias = 0;
    private int tiempoTotalAutos = 0;
    private int tiempoTotalOmnibus = 0;
    private int tiempoTotalCamionsitos = 0;
    private int tiempoTotalCamiones = 0;
    
    private double promedioEmergencias = 0;
    private double promedioAutos = 0;
    private double promedioOmnibus = 0;
    private double promedioCamionsitos = 0;
    private double promedioCamiones = 0;
            
    public Estadistica(LinkedList<Vehiculo> vehiculos){
        this.vehiculos = vehiculos;
        for(Vehiculo v : this.vehiculos){
            switch(v.getTipo()) {
                case Auto: 
                    this.tiempoTotalAutos += v.getHoraEgreso() - v.getHoraIngreso();
                    this.totalAutos ++;
                break;
                case Omnibus: 
                    this.tiempoTotalOmnibus += v.getHoraEgreso() - v.getHoraIngreso();
                    this.totalOmnibus ++;
                break;
                case Camion: 
                    this.tiempoTotalCamiones += v.getHoraEgreso() - v.getHoraIngreso();
                    this.totalCamiones ++;
                break;
                case Camionsito: 
                    this.tiempoTotalCamionsitos += v.getHoraEgreso() - v.getHoraIngreso();
                    this.totalCamionsitos ++;
                break;
                case Emergencia: 
                    this.tiempoTotalEmergencias += v.getHoraEgreso() - v.getHoraIngreso();
                    this.totalEmergencias ++;
                break;
                default: throw new Error("Tipo vehiculo desconocido: " + v.getTipo());
            }
        }
        
        this.promedioAutos = (this.totalAutos != 0 ? this.tiempoTotalAutos / this.totalAutos : 0);
        this.promedioCamiones = (this.totalCamiones != 0 ? this.tiempoTotalCamiones / this.totalCamiones : 0);
        this.promedioCamionsitos = (this.totalCamionsitos != 0 ? this.tiempoTotalCamionsitos / this.totalCamionsitos : 0);
        this.promedioEmergencias = (this.totalEmergencias != 0 ? this.tiempoTotalEmergencias / this.totalEmergencias : 0);
        this.promedioOmnibus = (this.totalOmnibus != 0 ? this.tiempoTotalOmnibus / this.totalOmnibus : 0);
        
    }
    
    public void printEstadisticas(){
        System.out.println("\u001b[32m" + "------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("");
        System.out.println("\u001b[32m" + "TEST FINALIZADO");
        System.out.println("");
        System.out.println("\u001b[32m" + "Promedio EMERGENCIAS: " + this.promedioEmergencias);
        System.out.println("");
        System.out.println("\u001b[32m" + "Promedio AUTOS: " + this.promedioAutos);
        System.out.println("");
        System.out.println("\u001b[32m" + "Promedio OMNIBUS: " + this.promedioOmnibus);
        System.out.println("");
        System.out.println("\u001b[32m" + "Promedio CAMIONSITOS: " + this.promedioCamionsitos);
        System.out.println("");
        System.out.println("\u001b[32m" + "Promedio CAMIONES: " + this.promedioCamiones);
    }

    
}
