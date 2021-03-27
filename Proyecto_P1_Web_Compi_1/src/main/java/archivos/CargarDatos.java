/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivos;

import clasesDAOFormularios.Formulario;
import clasesDAOUsuario.Usuario;
import gramatica_guardado_datos.LexerGuardadoDatos;
import gramatica_guardado_datos.ParserGuardadoDatos;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.util.ArrayList;

/**
 *
 * @author grifiun
 */
public class CargarDatos {
    
    ArrayList<Usuario> listadoUsuariosCargados = new ArrayList();
    ArrayList<Formulario> listadoFormulariosCargados = new ArrayList();
    
    /**
     * Funcion dedicada a leer archivos
     * @param contenido
     * @param tipoGuardado 
     */
    public void leerDatos(String nombreArchivo){
    
         String path = "archivos_compi_1_Prouecto_1/"+nombreArchivo+".txt";        
        
        try {
           
            File file = new File(path);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                System.out.println("No existe el archivo requerido");
            }else{
                BufferedReader br = new BufferedReader(new FileReader(path));
                Reader reader = br;
                LexerGuardadoDatos lexer = new LexerGuardadoDatos(reader);
                
                ParserGuardadoDatos parser = new ParserGuardadoDatos(lexer);
                parser.parse();
                
                System.out.println("EL ARCHIVO DE GUARDADO SE PROCESO SIN ERROR \n");
                
                if(nombreArchivo.equals("formularios")){
                    ArrayList<Formulario> listadoFormularios = parser.getListadoFormularioLeidos();
                    System.out.println("FORMULARIOS:----------------------\n");
                    
                    if(listadoFormularios != null && listadoFormularios.size() > 0){
                        this.listadoFormulariosCargados = listadoFormularios;
                        /*
                        for(Formulario formAux: listadoFormularios){
                            System.out.println("\n\n");
                            System.out.println(formAux.generarCodigoHTMLFormulario());
                        }*/
                    }                   
                }else if(nombreArchivo.equals("usuarios")){
                    ArrayList<Usuario> listadoUsuarios = parser.getListadoUsuario();
                    System.out.println("USUARIOS:----------------------\n");
                    
                    if(listadoUsuarios != null && listadoUsuarios.size() > 0){
                        this.listadoUsuariosCargados = listadoUsuarios;
                    } 
                }
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se pudo leer: "+e);
        }
    
    }

    public ArrayList<Usuario> getListadoUsuariosCargados() {
        return listadoUsuariosCargados;
    }

    public ArrayList<Formulario> getListadoFormulariosCargados() {
        return listadoFormulariosCargados;
    }
    
    
    
}
