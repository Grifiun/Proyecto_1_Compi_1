/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author grifiun
 */
public class GuardarDatos {
 
    /**
     * Funcion dedicada a guardar archivos
     * @param contenido
     * @param tipoGuardado 
     */
    public void guardarDatos(String contenido, String tipoGuardado){
    
         String path;        
        
        try {
            File directorio = new File("archivos_compi_1_Prouecto_1");
            if (!directorio.exists()) {
                if (directorio.mkdirs()) {
                    System.out.println("Directorio creado");                    
                } else {
                    System.out.println("Error al crear directorio");
                }
            }
            
            if(directorio.exists()){//si existe el directorio
                String ruta = "archivos_compi_1_Prouecto_1/"+tipoGuardado+".txt";
                File file = new File(ruta);
                // Si el archivo no existe es creado
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);

                bw.write(contenido);
                bw.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se pudo guardar");
        }
    
    }
}
