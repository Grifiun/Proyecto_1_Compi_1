/*Primera seccion, librerias */
package gramatica_importacion_formularios;

import java_cup.runtime.*;
import clasesDAO.Token;
import clasesDAO.TokenError;
import static gramatica_importacion_formularios.ParserImportacionDatosSym.*;
import java.util.ArrayList;


/*Segunda seccion, config*/
%%
%class LexerImportacionDatos
%cup
%cupdebug
%unicode
%line
%public
%column

%{
    //Creamos un listado de los operadores invocados
    //ArrayList<Token> listadoOperadoresInvocados = new ArrayList();
    ArrayList<TokenError> listadoErroresLexicos = new ArrayList();
%}

%{  
    //retorna un simbolo despues de crear un nuevo token y agregarlo al listado
    private Symbol retornarSimbolo(int tipo, String tipoToken, String lexema, int fila, int columna){
        //creamos un  token auxiliar
        Token tokenAux = new Token(tipoToken, lexema, fila, columna);
        System.out.println("\nFila : "+fila +" Columna: "+columna +" Token de tipo: "+tipoToken+" Lexema: "+lexema);
        //Agregamos al listado
        //listadoOperadoresInvocados.add(tokenAux);
        //retornamos el token aux como simbolo
        return new Symbol(tipo, tokenAux);
    }

    //Agregamos un token al array list de errores lexicos
    
    private void addErrorLexico(String tipoToken, String lexema, String msgError, int fila, int columna){
        //creamos un  token auxiliar
        TokenError tokenErrorAux = new TokenError(tipoToken, lexema, msgError, fila , columna);
        //Agregamos al listado
        listadoErroresLexicos.add(tokenErrorAux);
    }

    //Obtenemos el arrLust de los errores lexicos
    public ArrayList<TokenError> obtenerListadoErroresLexicos(){
        return listadoErroresLexicos;
    }

%}
//SEPARADORES
SEPARADORES = [ \r\t\b\f\n]

//SIMBOLOS RESERVADAS
LLAVES_INICIO = "{"
LLAVES_FIN = "}"
PARENTESIS_INICIO = "("
PARENTESIS_FIN = ")"
DOS_PUNTOS = ":"
COMA = ","

//PALABRAS RESERVADAS
NEWFORMULARIO = new \. formulario

//PARAMETROS FORMULARIOS
ID_FORMULARIO = \" [\0-\40]* ID_FORMULARIO [\0-\40]* \"
TITULO = \" [\0-\40]* TITULO [\0-\40]* \"
NOMBRE = \" [\0-\40]* NOMBRE [\0-\40]* \"
TEMA = \" [\0-\40]* TEMA [\0-\40]* \"
USUARIO_CREACION = \" [\0-\40]* USUARIO_CREACION [\0-\40]* \"
FECHA_CREACION = \" [\0-\40]* FECHA_CREACION [\0-\40]* \"
PUBLICO = \" [\0-\40]* PUBLICO [\0-\40]* \"

//ESTRUCTURA
ESTRUCTURA = \" [\0-\40]* ESTRUCTURA [\0-\40]* \"

//PARAMETROS TEMA
DARK = \" [\0-\40]* (DARK | dark | Dark) [\0-\40]* \"
BLUE = \" [\0-\40]* (BLUE | blue | Blue) [\0-\40]* \"
WHITE = \" [\0-\40]* (WHITE | white | White) [\0-\40]* \" 

//PARAMETROS COMPONENTES
ID_COMPONENTE = \" [\0-\40]* ID_COMPONENTE [\0-\40]* \"
NOMBRE_CAMPO = \" [\0-\40]* NOMBRE_CAMPO [\0-\40]* \"
CLASE = \" [\0-\40]* CLASE [\0-\40]* \"
INDICE = \" [\0-\40]* INDICE [\0-\40]* \"
TEXTO_VISIBLE = \" [\0-\40]* TEXTO_VISIBLE [\0-\40]* \"
ALINEACION = \" [\0-\40]* ALINEACION [\0-\40]* \"
REQUERIDO = \" [\0-\40]* REQUERIDO [\0-\40]* \"
OPCIONES = \" [\0-\40]* OPCIONES [\0-\40]* \"
FILAS = \" [\0-\40]* FILAS [\0-\40]* \"
COLUMNAS = \" [\0-\40]* COLUMNAS [\0-\40]*\"
URL = \" [\0-\40]* URL [\0-\40]* \"

//CLASE PARAMETROS
CAMPO_TEXTO = \" [\0-\40]* CAMPO_TEXTO [\0-\40]* \"
AREA_TEXTO = \" [\0-\40]* AREA_TEXTO [\0-\40]* \"
CHECKBOX = \" [\0-\40]* CHECKBOX [\0-\40]* \"
RADIO = \" [\0-\40]* RADIO [\0-\40]* \"
FICHERO = \" [\0-\40]* FICHERO [\0-\40]* \"
IMAGEN = \" [\0-\40]* IMAGEN [\0-\40]* \"
COMBO = \" [\0-\40]* COMBO [\0-\40]* \"
BOTON = \" [\0-\40]* BOTON [\0-\40]* \"

//ALINEACION PARAMETROS
CENTRO = \" [\0-\40]* CENTRO [\0-\40]* \"
IZQUIERDA = \" [\0-\40]* IZQUIERDA [\0-\40]* \"
DERECHA = \" [\0-\40]* DERECHA [\0-\40]* \"
JUSTIFICAR = \" [\0-\40]* JUSTIFICAR [\0-\40]* \"

//REQUERIDO PARAMETROS
SI = \" [\0-\40]* SI [\0-\40]* \"
NO = \" [\0-\40]* NO [\0-\40]* \"

//VALORES
VALOR_FECHA = \" [\0-\40]* [[:digit:]] [[:digit:]] [[:digit:]] [[:digit:]] "-" [[:digit:]] [[:digit:]] "-" [[:digit:]] [[:digit:]]  [\0-\40]* \"//YYYY-MM-DD
VALOR_ID = \" [\0-\40]* (\$ | - | _) (\$ | - | _ | [a-zA-Z0-9])+  [\0-\40]* \"
DIGITO = \"  [\0-\40]* [[:digit:]]+  [\0-\40]* \"
VALOR_PASSWORD = \" [\0-\40]* ([\41] | [\43-\176] | "’")+  [\0-\40]* \"
VALOR_TEXTO_VISIBLE_CON_ESPACIO = \" ([\0-\41] | [\43-\176] | [\241-\254] | [\256-\377] | "’")+ \"

%%

/*Tercera accion, expresiones*/
<YYINITIAL>{
    //SIMBOLOS RESERVADAS
    {LLAVES_INICIO} { return retornarSimbolo(LLAVES_INICIO, "LLAVES_INICIO", yytext(), yyline + 1, yycolumn + 1); }
    {LLAVES_FIN} { return retornarSimbolo(LLAVES_FIN, "LLAVES_FIN", yytext(), yyline + 1, yycolumn + 1); }
    {PARENTESIS_INICIO} { return retornarSimbolo(PARENTESIS_INICIO, "PARENTESIS_INICIO", yytext(), yyline + 1, yycolumn + 1); }
    {PARENTESIS_FIN} { return retornarSimbolo(PARENTESIS_FIN, "PARENTESIS_FIN", yytext(), yyline + 1, yycolumn + 1); }     
    {DOS_PUNTOS} { return retornarSimbolo(DOS_PUNTOS, "DOS_PUNTOS", yytext(), yyline + 1, yycolumn + 1); }
    {COMA} { return retornarSimbolo(COMA, "COMA", yytext(), yyline + 1, yycolumn + 1); }

    //IDENTIFCADORES DE GUARDADO
    {NEWFORMULARIO} { return retornarSimbolo(NEWFORMULARIO, "NEWFORMULARIO", yytext(), yyline + 1, yycolumn + 1); }

    //PARAMETROS FORMULARIOS
    {ID_FORMULARIO} { return retornarSimbolo(ID_FORMULARIO, "ID_FORMULARIO", yytext(), yyline + 1, yycolumn + 1); }
    {TITULO} { return retornarSimbolo(TITULO, "TITULO", yytext(), yyline + 1, yycolumn + 1); }
    {NOMBRE} { return retornarSimbolo(NOMBRE, "NOMBRE", yytext(), yyline + 1, yycolumn + 1); }
    {TEMA} { return retornarSimbolo(TEMA, "TEMA", yytext(), yyline + 1, yycolumn + 1); }
    {USUARIO_CREACION} { return retornarSimbolo(USUARIO_CREACION, "USUARIO_CREACION", yytext(), yyline + 1, yycolumn + 1); }
    {FECHA_CREACION} { return retornarSimbolo(FECHA_CREACION, "FECHA_CREACION", yytext(), yyline + 1, yycolumn + 1); }
    {PUBLICO} { return retornarSimbolo(PUBLICO, "PUBLICO", yytext(), yyline + 1, yycolumn + 1); }

    //PARAMETROS TEMA
    {DARK} { return retornarSimbolo(DARK, "DARK", yytext(), yyline + 1, yycolumn + 1); }
    {BLUE} { return retornarSimbolo(BLUE, "BLUE", yytext(), yyline + 1, yycolumn + 1); }
    {WHITE} { return retornarSimbolo(WHITE, "WHITE", yytext(), yyline + 1, yycolumn + 1); }

    //PARAMETROS COMPONENTES
    {ID_COMPONENTE} { return retornarSimbolo(ID_COMPONENTE, "ID_COMPONENTE", yytext(), yyline + 1, yycolumn + 1); }
    {NOMBRE_CAMPO} { return retornarSimbolo(NOMBRE_CAMPO, "NOMBRE_CAMPO", yytext(), yyline + 1, yycolumn + 1); }
    {CLASE} { return retornarSimbolo(CLASE, "CLASE", yytext(), yyline + 1, yycolumn + 1); }
    {INDICE} { return retornarSimbolo(INDICE, "INDICE", yytext(), yyline + 1, yycolumn + 1); }
    {ALINEACION} { return retornarSimbolo(ALINEACION, "ALINEACION", yytext(), yyline + 1, yycolumn + 1); }
    {TEXTO_VISIBLE} { return retornarSimbolo(TEXTO_VISIBLE, "TEXTO_VISIBLE", yytext(), yyline + 1, yycolumn + 1); }
    {REQUERIDO} { return retornarSimbolo(REQUERIDO, "REQUERIDO", yytext(), yyline + 1, yycolumn + 1); }
    {OPCIONES} { return retornarSimbolo(OPCIONES, "OPCIONES", yytext(), yyline + 1, yycolumn + 1); }
    {FILAS} { return retornarSimbolo(FILAS, "FILAS", yytext(), yyline + 1, yycolumn + 1); }
    {COLUMNAS} { return retornarSimbolo(COLUMNAS, "COLUMNAS", yytext(), yyline + 1, yycolumn + 1); }
    {URL} { return retornarSimbolo(URL, "URL", yytext(), yyline + 1, yycolumn + 1); }

    //CLASE PARAMETROS
    {CAMPO_TEXTO} { return retornarSimbolo(CAMPO_TEXTO, "CAMPO_TEXTO", yytext(), yyline + 1, yycolumn + 1); }
    {AREA_TEXTO} { return retornarSimbolo(AREA_TEXTO, "AREA_TEXTO", yytext(), yyline + 1, yycolumn + 1); }
    {CHECKBOX} { return retornarSimbolo(CHECKBOX, "CHECKBOX", yytext(), yyline + 1, yycolumn + 1); }
    {RADIO} { return retornarSimbolo(RADIO, "RADIO", yytext(), yyline + 1, yycolumn + 1); }
    {FICHERO} { return retornarSimbolo(FICHERO, "FICHERO", yytext(), yyline + 1, yycolumn + 1); }
    {IMAGEN} { return retornarSimbolo(IMAGEN, "IMAGEN", yytext(), yyline + 1, yycolumn + 1); }
    {COMBO} { return retornarSimbolo(COMBO, "COMBO", yytext(), yyline + 1, yycolumn + 1); }
    {BOTON} { return retornarSimbolo(BOTON, "BOTON", yytext(), yyline + 1, yycolumn + 1); }

    //ALINEACION PARAMETROS
    {CENTRO} { return retornarSimbolo(CENTRO, "CENTRO", yytext(), yyline + 1, yycolumn + 1); }
    {IZQUIERDA} { return retornarSimbolo(IZQUIERDA, "IZQUIERDA", yytext(), yyline + 1, yycolumn + 1); }
    {DERECHA} { return retornarSimbolo(DERECHA, "DERECHA", yytext(), yyline + 1, yycolumn + 1); }
    {JUSTIFICAR} { return retornarSimbolo(JUSTIFICAR, "JUSTIFICAR", yytext(), yyline + 1, yycolumn + 1); }

    //REQUERIDO PARAMETROS
    {SI} { return retornarSimbolo(SI, "SI", yytext(), yyline + 1, yycolumn + 1); }
    {NO} { return retornarSimbolo(NO, "NO", yytext(), yyline + 1, yycolumn + 1); }

    //VALORES EXTRAS
    {ESTRUCTURA} { return retornarSimbolo(ESTRUCTURA, "ESTRUCTURA", yytext(), yyline + 1, yycolumn + 1); }

    //VALORES 
    {VALOR_ID} { return retornarSimbolo(VALOR_ID, "VALOR_ID", yytext(), yyline + 1, yycolumn + 1); }
    {VALOR_FECHA} { return retornarSimbolo(VALOR_FECHA, "VALOR_FECHA", yytext(), yyline + 1, yycolumn + 1); }
    {DIGITO} { return retornarSimbolo(DIGITO, "DIGITO", yytext(), yyline + 1, yycolumn + 1); }
    {VALOR_PASSWORD} {                               
            if(yytext().replaceAll("\"", "").trim().startsWith("|") || yytext().replaceAll("\"", "").trim().endsWith("|") ){
                return retornarSimbolo(VALOR_PASSWORD, "VALOR_PASSWORD", yytext(), yyline + 1, yycolumn + 1); 
            }else{
                return retornarSimbolo(VALOR_ITEMS_SIN_ESPACIO, "VALOR_ITEMS_SIN_ESPACIO", yytext(), yyline + 1, yycolumn + 1);
            }
        } 
    {VALOR_TEXTO_VISIBLE_CON_ESPACIO} { 
            if(yytext().replaceAll("\"", "").trim().endsWith("|") || yytext().replaceAll("\"", "").trim().startsWith("|") ){
                return retornarSimbolo(VALOR_TEXTO_VISIBLE_CON_ESPACIO, "VALOR_TEXTO_VISIBLE_CON_ESPACIO", yytext(), yyline + 1, yycolumn + 1); 
            }else if(yytext().trim().contains(" ")){//si tiene espacios
                return retornarSimbolo(VALOR_ITEMS_CON_ESPACIO, "VALOR_ITEMS_CON_ESPACIO", yytext(), yyline + 1, yycolumn + 1);
            }else{
                return retornarSimbolo(VALOR_ITEMS_SIN_ESPACIO, "VALOR_ITEMS_SIN_ESPACIO", yytext(), yyline + 1, yycolumn + 1);
            }
        
        } 

    
    //SEPARADORES
    {SEPARADORES} { }  

     //{} { return retornarSimbolo(, "", yytext(), yyline + 1, yycolumn + 1); }
}

[^] { addErrorLexico ("LEXICO", yytext(), "Token no valido",yyline + 1, yycolumn + 1); }
