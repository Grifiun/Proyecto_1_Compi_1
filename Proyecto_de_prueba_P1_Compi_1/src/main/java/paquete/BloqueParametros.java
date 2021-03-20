/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import java.util.ArrayList;

/**
 *
 * @author grifiun
 */
public class BloqueParametros extends Token{
    ArrayList<TokenParametro> listadoParametros = new ArrayList();
    ArrayList<String> listadoTipoParametros = new ArrayList();
    int lineaFin;
    int columnaFin;
    
    public BloqueParametros(String tipoBloque, String lexema) {
        super(tipoBloque, lexema, 0, 0);
        this.lineaFin = lineaFin;
        this.columnaFin = columnaFin;
    }


    public String tipoBloque() {
        return getTipoToken();
    }

    public void setTipoBloque(String tipoBloque) {
        setTipoToken(tipoBloque);
    }

    public ArrayList<TokenParametro> getListadoParametros() {
        return listadoParametros;
    }

    public void insertarParametro(TokenParametro parametro) {
        this.listadoParametros.add(parametro);
    }

    public ArrayList<String> getListadoTipoParametros() {
        return listadoTipoParametros;
    }

    public void insertarTipoParametros(String tipoParametro) {
        this.listadoTipoParametros.add(tipoParametro);
    }

    public int getLineaFin() {
        return lineaFin;
    }

    public void setLineaFin(int lineaFin) {
        this.lineaFin = lineaFin;
    }

    public int getColumnaFin() {
        return columnaFin;
    }

    public void setColumnaFin(int columnaFin) {
        this.columnaFin = columnaFin;
    }
    
    
    
    
    
}
