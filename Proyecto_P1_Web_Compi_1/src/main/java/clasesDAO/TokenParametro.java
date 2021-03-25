/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesDAO;

/**
 *
 * @author grifiun
 */
public class TokenParametro extends Token{
    
    public TokenParametro(String tipoParametro, String lexema,  int linea, int columna) {
        super(tipoParametro, lexema, linea, columna);
    }


    public String getTipoParametro() {
        return getTipoToken();
    }

    public void setTipoParametro(String tipoParametro) {
        setTipoToken(tipoParametro);
    }
}
