/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

/**
 *
 * @author nico
 */
public class ProyectoSO {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        System.out.println("*********** BIENVENIDO AL SIMULADOR PEAJE DE PANDO ***********");
        System.out.println("");
        System.out.print("Opciones del simulador:  \n 1) Ingresar al simulador \n 2) Salir \n");
        System.out.print("\nIngrese opción: ");
        int op = scan.nextInt();
        if (op == 1) {
            System.out.print("Ingrese la cantidad de casillas a ser simuladas: ");
            int cantidadCasillas = scan.nextInt();
            System.out.print("Ingrese el nombre del archivo ubicado en Test/Traficos sin la extensión:" );
            String nombreArch = scan.next();
            LinkedList<Pair<Integer, Evento>> lista = leer("../Tests/Traficos/" + nombreArch + ".csv");
            Planificador planificador = new Planificador();
            LinkedList<Casilla> listaCasillas = new LinkedList();
            Reloj reloj = new Reloj(cantidadCasillas, lista, planificador);

            for (int i = 0; i < cantidadCasillas; i++) {
                Casilla casilla = new Casilla(i, reloj);
                listaCasillas.add(casilla);
            }

            planificador.tomaCasillas(listaCasillas);

            reloj.start();
            listaCasillas.forEach((casilla) -> {
                casilla.start();
            });

            reloj.join();
            listaCasillas.forEach((casilla) -> {
                try {
                    casilla.join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ProyectoSO.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            LinkedList<Vehiculo> listaVehiculosProc = reloj.getListaVehiculosProcesados();

            Estadistica est = new Estadistica(listaVehiculosProc, listaCasillas, reloj.getContador());
            est.printEstadisticas();


        } else {
            System.out.println("Hasta luego!");
        }
    }

    static public LinkedList<Pair<Integer, Evento>> leer(String nombreCompletoArchivo) {
        String[] lista = ManejadorArchivosGenerico.leerArchivo(nombreCompletoArchivo);
        int i = 0;
        LinkedList<Pair<Integer, Evento>> list = new LinkedList<>();
        boolean endInput = false;
        do {
            String line = lista[i];
            line = line.trim();
            if (line.equals("*")) {
                endInput = true;
            } else {
                String[] datos = line.split(";");
                list.add(
                        new Pair<>(Integer.parseInt(datos[0]), Evento.parsear(datos[1], datos[2])));
            }
            i++;
        } while (!endInput);
        return list;
    }
}
