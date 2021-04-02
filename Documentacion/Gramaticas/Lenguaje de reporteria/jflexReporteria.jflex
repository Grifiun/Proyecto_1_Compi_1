/*Primera seccion, librerias */
package gramatica_reporteria;
import java_cup.runtime.*;
import clasesDAO.Token;
import static gramatica_reporteria.ParserReporteriaSym.*;
import clasesDAO.TokenError;
import java.util.ArrayList;

/*Segunda seccion, config*/
%%
%class LexerReporteria
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
    private int filaInicio = 0;
    private int columnaInicio = 0;

%}

%{  
     //retorna un simbolo despues de crear un nuevo token y agregarlo al listado
     private Symbol retornarSimbolo(int tipo, String tipoToken, String lexema, int fila, int columna){
          //creamos un  token auxiliar
          Token tokenAux = new Token(tipoToken, lexema, fila, columna);
          System.out.println("\nFila : "+fila + filaInicio +" Columna: "+columna + columnaInicio +" Token de tipo: "+tipoToken+" Lexema: "+lexema);
          //Agregamos al listado
          //listadoOperadoresInvocados.add(tokenAux);
          //retornamos el token aux como simbolo
          return new Symbol(tipo, tokenAux);
     }

     //Agregamos un token al array list de errores lexicos     
     private void addErrorLexico(String tipoToken, String lexema, String msgError, int fila, int columna){
          //creamos un  token auxiliar
          TokenError tokenErrorAux = new TokenError(tipoToken, lexema, msgError, fila + filaInicio, columna + columnaInicio);
          //Agregamos al listado
          listadoErroresLexicos.add(tokenErrorAux);
     }

     //Obtenemos el arrLust de los errores lexicos
     public ArrayList<TokenError> obtenerListadoErroresLexicos(){
          return listadoErroresLexicos;
     }

    public void setFilaInicio(int fila){
        filaInicio = fila;
    }
    public void setColumna(int columna){
        columnaInicio = columna;
    }
%}

//SEPARADORES
SEPARADORES = [ \r\t\b\f\n]

//SIMBOLOS RESERVADAS
CORCHETES_INICIO = "["
CORCHETES_FIN = "]"
MENOR = "<"
MAYOR = ">"
COMA = ","
IGUAL = "="
MENOS = "-"
//MENOR_MAYOR = MENOR [\0-\40]* MAYOR
//MENOR_IGUAL = MENOR [\0-\40]* IGUAL
//MAYOR_IGUAL = MAYOR [\0-\40]* IGUAL
MENOR_MAYOR = "<" [\0-\40]* ">"
MENOR_IGUAL = "<" [\0-\40]* "="
MAYOR_IGUAL = ">" [\0-\40]* "="

//OPERADOR LOGICO
AND = AND
OR = OR
NOT = NOT

//SQForm
SELECT = SELECT
TO = TO
FORM = FORM
WHERE = WHERE

//VALORES SQForm
SQFORM_VALOR_NUMERO = (0 | ([1-9] [0-9]*) ) (\. (0 [1-9]| [1-9])+)?
SQFORM_VALOR_TEXTO =  ("'" | "’") ([\0-\41] | [\43-\46] |[\50-\176])+ ("'" | "’")
//SQFORM_VALOR_TEXTO = ( ("'" | "’") [\40]*  ([\41] | [\43-\46] |[\50-\176]) ([\40-\41] | [\43-\46] |[\50-\176])*  [\40]* ("'" | "’") )
SQFORM_VALOR_NOMRE_CAMPOS = ([\41] | [\43-\46] | [\50-\53] | [\56-\73] | [\77-\132] |  [\134] | [\136-\176])+

//Excluimos el caracter con codigo octal 47 porque pertenece a la glotal, y el 42 por las comillas, 54 por la coma
//55 es el signo menos -
//74 a 76, > < y =
//133 ] y 135 [
%%

/*Tercera accion, expresiones*/
<YYINITIAL>{ 

    //SIMBOLOS RESERVADAS
    {CORCHETES_INICIO} { return retornarSimbolo(CORCHETES_INICIO, "CORCHETES_INICIO", yytext(), yyline + 1, yycolumn + 1); }
    {CORCHETES_FIN} { return retornarSimbolo(CORCHETES_FIN, "CORCHETES_FIN", yytext(), yyline + 1, yycolumn + 1); }     
    {MENOR} { return retornarSimbolo(MENOR, "MENOR", yytext(), yyline + 1, yycolumn + 1); }
    {MAYOR} { return retornarSimbolo(MAYOR, "MAYOR", yytext(), yyline + 1, yycolumn + 1); }
    {COMA} { return retornarSimbolo(COMA, "COMA", yytext(), yyline + 1, yycolumn + 1); }   
    {IGUAL} { return retornarSimbolo(IGUAL, "IGUAL", yytext(), yyline + 1, yycolumn + 1); }
    {MENOS} { return retornarSimbolo(MENOS, "MENOS", yytext(), yyline + 1, yycolumn + 1); }
    {MENOR_MAYOR} { return retornarSimbolo(MENOR_MAYOR, "MENOR_MAYOR", yytext(), yyline + 1, yycolumn + 1); }
    {MENOR_IGUAL} { return retornarSimbolo(MENOR_IGUAL, "MENOR_IGUAL", yytext(), yyline + 1, yycolumn + 1); }
    {MAYOR_IGUAL} { return retornarSimbolo(MAYOR_IGUAL, "MAYOR_IGUAL", yytext(), yyline + 1, yycolumn + 1); }

    //OPERADOR LOGICO
    {AND} { return retornarSimbolo(AND, "AND", yytext(), yyline + 1, yycolumn + 1); }
    {OR} { return retornarSimbolo(OR, "OR", yytext(), yyline + 1, yycolumn + 1); }
    {NOT} { return retornarSimbolo(NOT, "NOT", yytext(), yyline + 1, yycolumn + 1); }

    //SQForm
    {SELECT} { return retornarSimbolo(SELECT, "SELECT", yytext(), yyline + 1, yycolumn + 1); }
    {TO} { return retornarSimbolo(TO, "TO", yytext(), yyline + 1, yycolumn + 1); }
    {FORM} { return retornarSimbolo(FORM, "FORM", yytext(), yyline + 1, yycolumn + 1); }
    {WHERE} { return retornarSimbolo(WHERE, "WHERE", yytext(), yyline + 1, yycolumn + 1); }

    //VALORES SQForm
    {SQFORM_VALOR_NUMERO} { return retornarSimbolo(SQFORM_VALOR_NUMERO, "SQFORM_VALOR_NUMERO", yytext(), yyline + 1, yycolumn + 1); }
    {SQFORM_VALOR_TEXTO} { return retornarSimbolo(SQFORM_VALOR_TEXTO, "SQFORM_VALOR_TEXTO", yytext(), yyline + 1, yycolumn + 1); }
    {SQFORM_VALOR_NOMRE_CAMPOS} { return retornarSimbolo(SQFORM_VALOR_NOMRE_CAMPOS, "SQFORM_VALOR_NOMRE_CAMPOS", yytext(), yyline + 1, yycolumn + 1); }

    //SEPARADORES
    {SEPARADORES} { }    
     //{} { return retornarSimbolo(, "", yytext(), yyline + 1, yycolumn + 1); }
}

[^] { addErrorLexico ("LEXICO", yytext(), "Token no valido",yyline + 1, yycolumn + 1);}
