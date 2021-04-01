/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablas;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author grifiun
 */
public class InformacionTabla {
    private List<ArrayList<String>> informacionTabla = new ArrayList();    
    private String tituloGeneral = "";
    
    public void agregarFila(ArrayList<String> filaAux){
        informacionTabla.add(filaAux);
    }

    public void setInformacionTabla(List<ArrayList<String>> informacionTabla) {
        this.informacionTabla = informacionTabla;
    }    
       
    
    public void setTituloGeneral(String tituloGeneral){
        this.tituloGeneral = tituloGeneral;
    };

    public List<ArrayList<String>> getInformacionTabla() {
        return informacionTabla;
    }

    public String getTituloGeneral() {
        return tituloGeneral;
    }
    
    
}
