/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesDAOUsuario;

/**
 *
 * @author grifiun
 */
public class Usuario {
    private String usuario;
    private String password;
    private String fechaCreacion;
    private String fechaModificacion;
    
    /**
     * Inicalizmoas en el constructor
     */
    public Usuario(){
        this.usuario = "";
        this.password = "";
        this.fechaCreacion = "";
        this.fechaModificacion = "";    
    }
    
    public String generarCodigoAlmacenamientoUsuario(){
        String codigoAlmacenamiento = "{\n";
        
        if(usuario.equals("") == false){
            codigoAlmacenamiento += "\"USUARIO\" : "+usuario + ",\n";
        }
        if(password.equals("") == false){
            codigoAlmacenamiento += "\"PASSWORD\" : "+password + ",\n";
        }
        if(fechaCreacion.equals("") == false){
            codigoAlmacenamiento += "\"FECHA_CREACION\" : "+fechaCreacion + ",\n";
        }
        if(fechaModificacion.equals("") == false){
            codigoAlmacenamiento += "\"FECHA_MODIFICACION\" : "+fechaModificacion + ",\n";
        }
        
        /*{
		 "usuario",
		"PASSWORD": "aosdacontrasena323",
		"FECHA_CREACION": "2020-02-01"
		
	}*/
        
        if(codigoAlmacenamiento.equals("{\n") == false){//si tiene datos
            int longitud = codigoAlmacenamiento.length() - 2;
            codigoAlmacenamiento = codigoAlmacenamiento.substring(0, longitud); //removemos la ultima coma y el salto de linea
        }
        
        codigoAlmacenamiento += "\n}\n";       
        
        return codigoAlmacenamiento;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
    
    
    
}
