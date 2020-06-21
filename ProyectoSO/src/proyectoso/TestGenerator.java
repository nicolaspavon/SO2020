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
public class TestGenerator {
    
    public void generateTest(){
        int instante = 0;
        String tipo = "camion";
        String matricula = "asdasd";
        String[] archivo = new String[3];
        archivo[0] = instante + ";" + tipo + ";" + matricula;
        archivo[1] = ";";
        archivo[2] = " ";
        
        
        
        
        
        
        ManejadorArchivosGenerico maneja = new ManejadorArchivosGenerico();
        maneja.escribirArchivo("./tests/nico.csv", archivo);
    }
    
}
