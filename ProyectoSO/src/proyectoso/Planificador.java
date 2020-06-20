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

    // Buscar casilla ACTIVA
    private Casilla buscarCasillaActiva(int id){
        for(Casilla c : this.listaCasillas){
            if(id == c.getIdCasilla() && !c.estaBloqueada()){
                return c;
            }
        }
        return null;
    }
    
    // Buscar casilla INACTIVA
    private Casilla buscarCasillaInActiva(int id){
        for(Casilla c : this.listaCasillas){
            if(id == c.getIdCasilla() && c.estaBloqueada()){
                return c;
            }
        }
        return null;
    }
    
    public void eventoCasilla(EventoCasilla casilla) {
        if(casilla.getTipo().equals("roto")){
            this.romperCasilla(casilla);
        }else{
            this.arreglarCasilla(casilla);
        }
    }
    
    private void romperCasilla(EventoCasilla casilla){
        Casilla casillaRota = this.buscarCasillaActiva(casilla.getId());
        if (casillaRota != null){
            Fila filaHuerfana = casillaRota.teRompiste();
            this.adoptarFila(casilla.getId(), filaHuerfana);
        }else{
            System.out.println("\u001B[31m" + "Se rompio una casilla inexistente:  Id " + casilla.getId());
        }
    }
    
    private void arreglarCasilla(EventoCasilla casilla){
        Casilla casillaRota = this.buscarCasillaInActiva(casilla.getId());
        casillaRota.teArreglaron();
    }
    
    private Casilla casillaCercanaDisponibleArriba(int id){
        Casilla casillaArriba = null;
        for (int i = id +1; i < this.listaCasillas.size(); i++) {
            casillaArriba = this.buscarCasillaActiva(i);
            if (casillaArriba != null){
                return casillaArriba;
            }
        }
        return casillaArriba;
    }
    
    private Casilla casillaCercanaDisponibleAbajo(int id){
        Casilla casillaAbajo = null;
        for (int i = id -1; i >= 0; i--) {
            casillaAbajo = this.buscarCasillaActiva(i);
            if (casillaAbajo != null){
                return casillaAbajo;
            }
        }
        return casillaAbajo;
    }
    
    private Casilla casillaConMenosEspera(int id){
        Casilla casillaArriba = casillaCercanaDisponibleArriba(id);
        Casilla casillaAbajo = casillaCercanaDisponibleAbajo(id);
        
        if (casillaArriba != null && casillaAbajo != null){
            if (casillaAbajo.getTiempoRestante() > casillaArriba.getTiempoRestante()){
                return casillaArriba;
            }else{
                return casillaAbajo;
            }           
        } else if(casillaArriba != null) {
            return casillaArriba;
        } else if(casillaAbajo != null) {
            return casillaAbajo;
        } else{
            return null;
        }
    }
    
    private void adoptarFila(int id, Fila filaHuerfana){
        Casilla casillaAdoptadora = this.casillaConMenosEspera(id);
        if (casillaAdoptadora != null){
            casillaAdoptadora.adoptarFila(filaHuerfana);
        }else{
            this.enviarVehiculosAlEther(filaHuerfana);
        }
    }
    
    private void enviarVehiculosAlEther(Fila filaHuerfana){
        System.out.println("\u001B[31m" + "Caso borde no hay casilla adoptadora, se envian los vehiculos huerfanos al ether");
        int cantAutos = filaHuerfana.getCantidadVehiculos();
        for (int i = 0; i < cantAutos; i++) {
            Vehiculo auto = filaHuerfana.quitarAuto();
            listaVehiculosActuales.add(auto);
        }
    }
    
    private Casilla getPrimerCasillaDisponible(){
        for(Casilla casilla : listaCasillas){
            if (!casilla.estaBloqueada() && ! casilla.estaReservada()){
                return casilla;
            }
        }
        return null;
    }
    
    private Casilla buscarCasillaReservada(){
        for (Casilla c : listaCasillas){
            if (c.estaReservada()){
                return c;
            }
        }
        return null;
    }
    
    private void atenderEmergencias() throws Exception{
        
        while (!this.listaVehiculosActuales.isEmpty()) {
            if (this.listaVehiculosActuales.peek().esEmergencia()){
                Casilla yaReservada = buscarCasillaReservada();
                if (yaReservada != null){
                    Vehiculo emergencia = listaVehiculosActuales.pollFirst();
                    yaReservada.agregarAuto(emergencia);
                    System.out.println("\u001b[32m" + "Planificador:  Vehiculo " + emergencia.info() + " recibido e insertado en casilla: " + yaReservada.getIdCasilla());
                }else{
                    this.ordenarCasillas();
                    Casilla casillaMenosOcupada = this.getPrimerCasillaDisponible();
                    if (casillaMenosOcupada != null){
                        Fila filaHuerfana = casillaMenosOcupada.getFila();
                        this.adoptarFila(casillaMenosOcupada.getIdCasilla(), filaHuerfana);
                        Vehiculo emergencia = listaVehiculosActuales.pollFirst();
                        casillaMenosOcupada.agregarAuto(emergencia);
                        System.out.println("\u001b[32m" + "Planificador:  Vehiculo " + emergencia.info() + " recibido e insertado en casilla: " + casillaMenosOcupada.getIdCasilla());
                    }else{
                        System.out.println("\u001B[31m" + "No hay casillas libres para migrar los vehiculos, vehiculos trancados en el ether (" + this.listaVehiculosActuales.size() + ")");
                        break;
                    }
                }
            }else{
                //Caso no hay emergencia
                break;
            }
        }
    }
            
    public void asignarVehiculosACasillas() throws Exception{
        this.ordenarVehiculosActuales();    
        this.atenderEmergencias();
        while (!this.listaVehiculosActuales.isEmpty()) {
            this.ordenarCasillas();
            Casilla primerCasilla = this.getPrimerCasillaDisponible();
            if (primerCasilla != null){
                Vehiculo auto = listaVehiculosActuales.pollFirst();
                primerCasilla.agregarAuto(auto);
                System.out.println("\u001b[32m" + "Planificador:  Vehiculo " + auto.info() + " recibido e insertado en casilla: " + primerCasilla.getIdCasilla());
            }else{
                System.out.println("\u001B[31m" + "Todas las casillas están rotas, vehiculos trancados en el ether (" + this.listaVehiculosActuales.size() + ")");
                break;
            }
        }
    }
}
