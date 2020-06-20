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
public class Planificador {
    private LinkedList<Casilla> listaCasillas = new LinkedList();    
    private LinkedList<Vehiculo> listaVehiculosActuales = new LinkedList();    
    
    
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
    
    private void ordenarVehiculosActuales(){
        this.listaVehiculosActuales.sort((vehiculo1, vehiculo2) -> {
            return vehiculo1.getPrioridad() - vehiculo2.getPrioridad();
        });
    }
            
    public void aniadirVehiculoActual(Vehiculo vehiculo){
        this.listaVehiculosActuales.add(vehiculo);
    }

    public Casilla buscarCasillaActiva(int id){
        for(Casilla c : this.listaCasillas){
            if(id == c.getIdCasilla() && !c.estaBloqueada()){
                return c;
            }
        }
        return null;
    }
    
    //TODO: QUE PASA SI LAS CASILLAS DE AL LADO ESTAN BLOQUEADAS
    public void romperCasilla(RoturaCasilla casilla){
        Casilla casillaRota = this.buscarCasillaActiva(casilla.getId());
        if (casillaRota != null){
            Fila filaHuerfana = casillaRota.teRompiste();
            Casilla casillaArriba = this.buscarCasillaActiva(casilla.getId() + 1);
            Casilla casillaAbajo = this.buscarCasillaActiva(casilla.getId() - 1);
            if (casillaArriba != null && casillaAbajo != null){
                if (casillaAbajo.getTiempoRestante() > casillaArriba.getTiempoRestante()){
                    casillaArriba.adoptarFila(filaHuerfana);
                }else{
                    casillaAbajo.adoptarFila(filaHuerfana);
                }           
            } else if(casillaArriba != null) {
                casillaArriba.adoptarFila(filaHuerfana);
            } else if(casillaAbajo != null) {
                casillaAbajo.adoptarFila(filaHuerfana);
            } else{
                System.out.println("\u001B[31m" + "Caso borde no hay casilla adoptadora, se envian los vehiculos huerfanos al ether");
                int cantAutos = filaHuerfana.getCantidadVehiculos();
                for (int i = 0; i < cantAutos; i++) {
                    Vehiculo auto = filaHuerfana.quitarAuto();
                    listaVehiculosActuales.add(auto);
                }
            }
        }else{
            System.out.println("\u001B[31m" + "Se rompio una casilla inexistente:  Id " + casilla.getId());
        }
    }
    
    //TODO: Agregar casilla reservada
    public Casilla getPrimerCasillaDisponible(){
        for(Casilla casilla : listaCasillas){
            if (!casilla.estaBloqueada()){
                return casilla;
            }
        }
        return null;
    }
            
    public void asignarVehiculosACasillas() throws Exception{
        this.ordenarVehiculosActuales();             
        while (!this.listaVehiculosActuales.isEmpty()) {
            this.ordenarCasillas();
            Casilla primerCasilla = this.getPrimerCasillaDisponible();
            if (primerCasilla != null){
                Vehiculo auto = listaVehiculosActuales.pollFirst();
                primerCasilla.agregarAuto(auto);
                System.out.println("\u001b[32m" + "Planificador:  Vehiculo " + auto.info() + " recibido e insertado en casilla: " + primerCasilla.getIdCasilla());
            }else{
                System.out.println("\u001B[31m" + "Todas las casillas están rotas, vehiculos trancados en el ether");
                break;
            }
        }
    }
}
