/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funciones;

import clasesDAO.Token;
import clasesDAOFormularios.Componente;
import clasesDAOFormularios.Formulario;
import clasesDAOFormularios.Registro;
import java.util.ArrayList;

/**
 *
 * @author grifiun
 */
public class FuncionesReporteria {
    private int cantidadRegistros = 0;
    private Formulario formularioSQForm;
    
    public FuncionesReporteria(Formulario formularioSQForm){        
        this.formularioSQForm = formularioSQForm;
        calcularCantidadRegistros();        
        
        
    }
    
    public void calcularCantidadRegistros(){
        if(formularioSQForm != null){
            if(formularioSQForm.getListadoComponentes() != null && formularioSQForm.getListadoComponentes().size() > 0){//tiene mas de algun componente, entonces
                for(Componente compo: formularioSQForm.getListadoComponentes()){//recorremos todos los componentes
                        int cantidadRegistrosAux = compo.getListadoRegistros().size();
                        if(cantidadRegistrosAux > cantidadRegistros){
                                cantidadRegistros = cantidadRegistrosAux;
                        }
                }
            }
        }
        
    }
    
    public ArrayList<Boolean> generarListadoBooleanos(boolean estado){
            ArrayList<Boolean> resultado = new ArrayList();
            for(int i = 0; i < cantidadRegistros; i++){
                    resultado.add(estado);
            }
            return resultado;

    }
    
    public ArrayList<Integer> generarListadoEnteros(int estado){
            ArrayList<Integer> resultado = new ArrayList();
            for(int i = 0; i < cantidadRegistros; i++){
                    resultado.add(estado);
            }
            
            return resultado;

    }
    public ArrayList<String> generarListadoString(String estado){
            ArrayList<String> resultado = new ArrayList();
            for(int i = 0; i < cantidadRegistros; i++){
                    resultado.add(estado.replaceAll("\"", "").replaceAll("\'", "").replaceAll("\\’", "").trim());
            }
            
            return resultado;

    }

    public ArrayList<Integer> agregarValoresRegistros(String idNombre){
        FuncionesComponentes funcionesComponentes = new FuncionesComponentes();
        Componente componenteAuxA = funcionesComponentes.obtenerComponentePorIdNombre(formularioSQForm, idNombre);
        ArrayList<Integer> valores1 = new ArrayList();
        if(componenteAuxA != null){
            ArrayList<Registro> listadoRegistros1 = componenteAuxA.getListadoRegistros();
            for(Registro reg: listadoRegistros1){
                    int auxInt;
                    try {
                            auxInt = Integer.parseInt(reg.getRegistroDato());
                    } catch (NumberFormatException e) {
                            auxInt = 0;//0 indica que no se pueden comparar strings
                    }
                    valores1.add(auxInt);
            }	
            return valores1;
        }else{
                return null;
        }

    }
    public  ArrayList<String> agregarValoresRegistrosString(String idNombre){
        System.out.println("SE QUIERE AGREGAR VALORES DE REGISTROS");
        FuncionesComponentes funcionesComponentes = new FuncionesComponentes();
        Componente componenteAuxA = funcionesComponentes.obtenerComponentePorIdNombre(formularioSQForm, idNombre);
        ArrayList<String> valores1 = new ArrayList();
        if(componenteAuxA != null){
            ArrayList<Registro> listadoRegistros1 = componenteAuxA.getListadoRegistros();
            for(Registro reg: listadoRegistros1){
                    String aux = reg.getRegistroDato().replaceAll("\"", "").replaceAll("\'", "").trim();                    
                    valores1.add(aux);
            }	
            return valores1;
        }else{
                return null;
        }

    }
       
    public ArrayList<Boolean> getValorComparacion(Token a, Token b, String tipoComparacion){
        ArrayList<Boolean> resultado = new ArrayList();
        ArrayList<Integer> valores1 = new ArrayList();
        ArrayList<Integer> valores2 = new ArrayList();
        boolean error = false;

        boolean isVar1 = a.getTipoToken().equals("SQFORM_VALOR_NUMERO");		
        boolean isVar2 = b.getTipoToken().equals("SQFORM_VALOR_NUMERO");	
        boolean isVarString1 = a.getTipoToken().equals("SQFORM_VALOR_TEXTO");
        boolean isVarString2 = b.getTipoToken().equals("SQFORM_VALOR_TEXTO");
        
        System.out.println("TIPOS DE DATOS A: "+a.getTipoToken());
        System.out.println("TIPOS DE DATOS B: "+b.getTipoToken());

        //0: num
        //1: String
        //2. Identify

        if(isVar1 == false && isVarString1 == false){
                //La variable 1, a, es un identificador
                //agregamos los valores
                valores1 = agregarValoresRegistros(a.getLexema());
                if(valores1 == null){
                        error = true;
                }
        }else if(isVar1 == false && isVarString1 == true){
                //es una palabra
                valores1 = generarListadoEnteros(0);//le asignamos 0 por ser una palabra
        }else{
                valores1 = generarListadoEnteros(Integer.parseInt(a.getLexema()));
        }

        if(isVar2 == false && isVarString2 == false){
                //La variable 2, a, es un identificador
                valores2 = agregarValoresRegistros(b.getLexema());
                if(valores2 == null){
                        error = true;
                }
        }else if(isVar2 == false && isVarString2 == true){
                //es una palabra
                valores2 = generarListadoEnteros(0);//le asignamos 0 por ser una palabra
        }else{
                valores2 = generarListadoEnteros(Integer.parseInt(b.getLexema()));
        }

        if(error == false){
                for(int i = 0; i < cantidadRegistros; i++){
                    boolean valor = realizarOperacionComparacion (valores1.get(i), valores2.get(i), tipoComparacion);
                    resultado.add(valor);
                    //System.out.println (a.getLexema()+" "+tipoComparacion+"  "+b.getLexema()+" : "+ valor);
                    System.out.println (valores1.get(i)+" "+tipoComparacion+"  "+valores2.get(i)+" : "+ valor);
                }

        }else{
                System.out.println("hay error en la entrada");
        }
        
        return resultado;
    }
    
    public ArrayList<Boolean> getValorComparacionIgualDiferente(Token a, Token b, String tipoComparacion){
        ArrayList<Boolean> resultado = new ArrayList();
        ArrayList<String> valores1 = new ArrayList();
        ArrayList<String> valores2 = new ArrayList();
        boolean error = false;

        boolean isVar1 = a.getTipoToken().equals("SQFORM_VALOR_NUMERO");		
        boolean isVar2 = b.getTipoToken().equals("SQFORM_VALOR_NUMERO");	
        boolean isVarString1 = a.getTipoToken().equals("SQFORM_VALOR_TEXTO");
        boolean isVarString2 = b.getTipoToken().equals("SQFORM_VALOR_TEXTO");

        if(isVar1 == false && isVarString1 == false){
                //La variable 1, a, es un identificador
                //agregamos los valores
                valores1 = agregarValoresRegistrosString(a.getLexema());
                if(valores1 == null){
                        error = true;
                }
        }else{
                valores1 = generarListadoString(a.getLexema());
        }

        if(isVar2 == false && isVarString2 == false){
                //La variable 2, a, es un identificador
                valores2 = agregarValoresRegistrosString(b.getLexema());
                if(valores2 == null){
                        error = true;
                }
        }else{
                valores2 = generarListadoString(b.getLexema());
        }

        if(error == false){
                for(int i = 0; i < cantidadRegistros; i++){
                    boolean valor = realizarOperacionComparacion (valores1.get(i), valores2.get(i), tipoComparacion);
                    resultado.add(valor);
                    //System.out.println (a.getLexema()+" "+tipoComparacion+"  "+b.getLexema()+" : "+ valor);
                    System.out.println (valores1.get(i)+" "+tipoComparacion+"  "+valores2.get(i)+" : "+ valor);
                }

        }else{
                System.out.println("hay error en la entrada");
        }
        
        return resultado;
    }

    public boolean realizarOperacionComparacion (int valor1, int valor2, String tipoComparacion){
        boolean resultadoComparacion = false;        
            switch(tipoComparacion){
                case "MAYOR":
                    resultadoComparacion = valor1 > valor2;
                    break;
                case "MENOR":
                    resultadoComparacion = valor1 < valor2;
                    break;
                case "MAYOR_IGUAL":
                    resultadoComparacion = valor1 >= valor2;
                    break;
                case "MENOR_IGUAL":
                    resultadoComparacion = valor1 <= valor2;
                    break;
            }
        
        return resultadoComparacion;
        
    }
    
     public boolean realizarOperacionComparacion (String valor1, String valor2, String tipoComparacion){
        boolean resultadoComparacion = false;        
            switch(tipoComparacion){
                case "IGUAL":
                    resultadoComparacion = valor1.equals(valor2);
                    break;
                case "MENOR_MAYOR":
                    resultadoComparacion = !( valor1.equals(valor2) );
                    break;
            }
        
        return resultadoComparacion;
        
    }
    
    public int getCantidadRegistros() {
        return cantidadRegistros;
    }
    
    
    
}
