/*Primera seccion, librerias */
package gramatica_respuestas;
import java_cup.runtime.*;
import clasesDAO.Token;
import static gramatica_respuestas.ParserRespuestasSym.*;
/*Segunda seccion, config*/
%%
%class LexerIndigoRespuestas
%cup
%cupdebug
%unicode
%line
%public
%column

%{
    //Creamos un listado de los operadores invocados
    //ArrayList<Token> listadoOperadoresInvocados = new ArrayList();
    //ArrayList<TokenError> listadoErroresLexicos = new ArrayList();

%}

%{  
     //retorna un simbolo despues de crear un nuevo token y agregarlo al listado
     private Symbol retornarSimbolo(int tipo, String tipoToken, String lexema, int fila, int columna){
          //creamos un  token auxiliar
          Token tokenAux = new Token(tipoToken, lexema, fila, columna);
          System.out.println("\nFila : "+fila+" Columna: "+columna+" Token de tipo: "+tipoToken+" Lexema: "+lexema);
          //Agregamos al listado
          //listadoOperadoresInvocados.add(tokenAux);
          //retornamos el token aux como simbolo
          return new Symbol(tipo, tokenAux);
     }

     //Agregamos un token al array list de errores lexicos
     /*
     private void addErrorLexico(String tipoToken, String lexema, String msgError, int fila, int columna){
          //creamos un  token auxiliar
          TokenError tokenErrorAux = new TokenError(tipoToken, lexema, msgError, fila, columna);
          //Agregamos al listado
          listadoErroresLexicos.add(tokenErrorAux);
     }

     //Obtenemos el arrLust de los errores lexicos
     public ArrayList<TokenError> obtenerListadoErroresLexicos(){
          return listadoErroresLexicos;
     }

     //Obtenemos el lstado de los tokens
     public ArrayList<Token> obtenerListadoTokens(){
          return listadoOperadoresInvocados;
     }*/
%}

//SEPARADORES
SEPARADORES = [ \r\t\b\f\n]

//SIMBOLOS RESERVADAS
LLAVES_INICIO = "{"
LLAVES_FIN = "}"
CORCHETES_INICIO = "["
CORCHETES_FIN = "]"
MENOR = "<"
MAYOR = ">"
EXCLAMACION = "!"
DOS_PUNTOS = ":"
COMA = ","

//PALABRAS RESERVADAS
INICIO_RESPUESTAS = ini_respuestas
INICIO_RESPUESTA = ini_respuesta
FIN_RESPUESTA = fin_respuesta
FIN_RESPUESTAS = fin_respuestas

//SOLICITUDES
CREAR_USUARIO = \" [\0-\40]* CREAR_USUARIO [\0-\40]* \"
MODIFICAR_USUARIO = \" [\0-\40]* MODIFICAR_USUARIO [\0-\40]* \"
ELIMINAR_USUARIO = \" [\0-\40]* ELIMINAR_USUARIO [\0-\40]* \"
LOGIN_USUARIO = \" [\0-\40]* LOGIN_USUARIO [\0-\40]* \"
CREDENCIALES_USUARIO = \" [\0-\40]* CREDENCIALES_USUARIO [\0-\40]* \"

//FORMULARIOS
NUEVO_FORMULARIO = \" [\0-\40]* NUEVO_FORMULARIO [\0-\40]* \"
ELIMINAR_FORMULARIO = \" [\0-\40]* ELIMINAR_FORMULARIO [\0-\40]* \"
MODIFICAR_FORMULARIO = \" [\0-\40]* MODIFICAR_FORMULARIO [\0-\40]* \"
PARAMETROS_FORMULARIO = \" [\0-\40]* PARAMETROS_FORMULARIO [\0-\40]* \"

//SOLICITUDES PARA COMPONENTES
AGREGAR_COMPONENTE = \" [\0-\40]* AGREGAR_COMPONENTE [\0-\40]* \"
MODIFICAR_COMPONENTE = \" [\0-\40]* MODIFICAR_COMPONENTE [\0-\40]* \"
ELIMINAR_COMPONENTE = \" [\0-\40]* ELIMINAR_COMPONENTE [\0-\40]* \"
PARAMETROS_COMPONENTE = \" [\0-\40]* PARAMETROS_COMPONENTE [\0-\40]* \"

//REPORTERIA
CONSULTAR_DATOS = \" [\0-\40]* CONSULTAR_DATOS [\0-\40]* \"
TABLA_CONSULTA = \" [\0-\40]* TABLA_CONSULTA [\0-\40]* \"
CONSULTAS = \" [\0-\40]* CONSULTAS [\0-\40]* \"

//IMPORTACION
IMPORTAR_FORMULARIO = \" [\0-\40]* IMPORTAR_FORMULARIO [\0-\40]* \"

//PALABRAS RESERVADAS GENERALES
PARAMETRO = PARAMETRO
VALOR = VALOR
RESPUESTA = RESPUESTA

//VALORES 
//caracteres ASCII imprimibles desde octal40 (espacio) hasta octal 176 (tilde  ̃ )
//siempre que no inicien con $, -, _

//VALOR_TEXTO = \" [\0-\40]*  ([\41] | [\43-\176]) ([\40-\41] | [\43-\176])*  [\0-\40]* \"

VALOR_TEXTO = \"  [\0-\40]* ([\41] | [\43-\176]) ([\0-\41] | [\43-\176])+ \"

%%

/*Tercera accion, expresiones*/
<YYINITIAL>{
     //SIMBOLOS RESERVADAS
     {LLAVES_INICIO} { return retornarSimbolo(LLAVES_INICIO, "LLAVES_INICIO", yytext(), yyline + 1, yycolumn + 1); }
     {LLAVES_FIN} { return retornarSimbolo(LLAVES_FIN, "LLAVES_FIN", yytext(), yyline + 1, yycolumn + 1); }
     {CORCHETES_INICIO} { return retornarSimbolo(CORCHETES_INICIO, "CORCHETES_INICIO", yytext(), yyline + 1, yycolumn + 1); }
     {CORCHETES_FIN} { return retornarSimbolo(CORCHETES_FIN, "CORCHETES_FIN", yytext(), yyline + 1, yycolumn + 1); }     
     {MENOR} { return retornarSimbolo(MENOR, "MENOR", yytext(), yyline + 1, yycolumn + 1); }
     {MAYOR} { return retornarSimbolo(MAYOR, "MAYOR", yytext(), yyline + 1, yycolumn + 1); }
     {EXCLAMACION} { return retornarSimbolo(EXCLAMACION, "EXCLAMACION", yytext(), yyline + 1, yycolumn + 1); }
     {DOS_PUNTOS} { return retornarSimbolo(DOS_PUNTOS, "DOS_PUNTOS", yytext(), yyline + 1, yycolumn + 1); }
     {COMA} { return retornarSimbolo(COMA, "COMA", yytext(), yyline + 1, yycolumn + 1); }

     //PALABRAS RESERVADAS
     {INICIO_RESPUESTAS} { return retornarSimbolo(INICIO_RESPUESTAS, "INICIO_RESPUESTAS", yytext(), yyline + 1, yycolumn + 1); }
     {INICIO_RESPUESTA} { return retornarSimbolo(INICIO_RESPUESTA, "INICIO_RESPUESTA", yytext(), yyline + 1, yycolumn + 1); }
     {FIN_RESPUESTA} { return retornarSimbolo(FIN_RESPUESTA, "FIN_RESPUESTA", yytext(), yyline + 1, yycolumn + 1); }
     {FIN_RESPUESTAS} { return retornarSimbolo(FIN_RESPUESTAS, "FIN_RESPUESTAS", yytext(), yyline + 1, yycolumn + 1); }

     //SOLICITUDES
     {CREAR_USUARIO} { return retornarSimbolo(CREAR_USUARIO, "CREAR_USUARIO", yytext(), yyline + 1, yycolumn + 1); }
     {MODIFICAR_USUARIO} { return retornarSimbolo(MODIFICAR_USUARIO, "MODIFICAR_USUARIO", yytext(), yyline + 1, yycolumn + 1); }
     {ELIMINAR_USUARIO} { return retornarSimbolo(ELIMINAR_USUARIO, "ELIMINAR_USUARIO", yytext(), yyline + 1, yycolumn + 1); }
     {LOGIN_USUARIO} { return retornarSimbolo(LOGIN_USUARIO, "LOGIN_USUARIO", yytext(), yyline + 1, yycolumn + 1); }
     {CREDENCIALES_USUARIO} { return retornarSimbolo(CREDENCIALES_USUARIO, "CREDENCIALES_USUARIO", yytext(), yyline + 1, yycolumn + 1); }

     //FORMULARIOS
     {NUEVO_FORMULARIO} { return retornarSimbolo(NUEVO_FORMULARIO, "NUEVO_FORMULARIO", yytext(), yyline + 1, yycolumn + 1); }
     {ELIMINAR_FORMULARIO} { return retornarSimbolo(ELIMINAR_FORMULARIO, "ELIMINAR_FORMULARIO", yytext(), yyline + 1, yycolumn + 1); }
     {MODIFICAR_FORMULARIO} { return retornarSimbolo(MODIFICAR_FORMULARIO, "MODIFICAR_FORMULARIO", yytext(), yyline + 1, yycolumn + 1); }
     {PARAMETROS_FORMULARIO} { return retornarSimbolo(PARAMETROS_FORMULARIO, "PARAMETROS_FORMULARIO", yytext(), yyline + 1, yycolumn + 1); }

     //SOLICITUDES PARA COMPONENTES
     {AGREGAR_COMPONENTE} { return retornarSimbolo(AGREGAR_COMPONENTE, "AGREGAR_COMPONENTE", yytext(), yyline + 1, yycolumn + 1); }
     {MODIFICAR_COMPONENTE} { return retornarSimbolo(MODIFICAR_COMPONENTE, "MODIFICAR_COMPONENTE", yytext(), yyline + 1, yycolumn + 1); }
     {ELIMINAR_COMPONENTE} { return retornarSimbolo(ELIMINAR_COMPONENTE, "ELIMINAR_COMPONENTE", yytext(), yyline + 1, yycolumn + 1); }
     {PARAMETROS_COMPONENTE} { return retornarSimbolo(PARAMETROS_COMPONENTE, "PARAMETROS_COMPONENTE", yytext(), yyline + 1, yycolumn + 1); }

     //REPORTERIA
     {CONSULTAR_DATOS} { return retornarSimbolo(CONSULTAR_DATOS, "CONSULTAR_DATOS", yytext(), yyline + 1, yycolumn + 1); }
     {TABLA_CONSULTA} { return retornarSimbolo(TABLA_CONSULTA, "TABLA_CONSULTA", yytext(), yyline + 1, yycolumn + 1); }
     {CONSULTAS} { return retornarSimbolo(CONSULTAS, "CONSULTAS", yytext(), yyline + 1, yycolumn + 1); }

     //IMPORTAR FORMULARIP
     {IMPORTAR_FORMULARIO} { return retornarSimbolo(IMPORTAR_FORMULARIO, "IMPORTAR_FORMULARIO", yytext(), yyline + 1, yycolumn + 1); }

     //PALABRAS RESERVADAS GENERALES
     {PARAMETRO} { return retornarSimbolo(PARAMETRO, "PARAMETRO", yytext(), yyline + 1, yycolumn + 1); }
     {VALOR} { return retornarSimbolo(VALOR, "VALOR", yytext(), yyline + 1, yycolumn + 1); }
     {RESPUESTA} { return retornarSimbolo(RESPUESTA, "RESPUESTA", yytext(), yyline + 1, yycolumn + 1); }

     //VALORES 
     {VALOR_TEXTO} { return retornarSimbolo(VALOR_TEXTO, "VALOR_TEXTO", yytext(), yyline + 1, yycolumn + 1); }  

     //SEPARADORES
     {SEPARADORES} { }     

     //{} { return retornarSimbolo(, "", yytext(), yyline + 1, yycolumn + 1); }
}

[^] { System.out.println("Error lexico: "+yytext()); }
