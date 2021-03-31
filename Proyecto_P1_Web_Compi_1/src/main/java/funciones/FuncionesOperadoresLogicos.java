/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funciones;

import java.util.ArrayList;

/**
 *
 * @author grifiun
 */
public class FuncionesOperadoresLogicos {
    
    
    public ArrayList<Boolean> invertirBoleanos(ArrayList<Boolean> estados){
            if(estados != null){
                ArrayList<Boolean> resultado = new ArrayList();
                for(int i = 0; i < estados.size(); i++){
                        boolean estado = estados.get(i);
                        resultado.add(!estado);
                }
                return resultado;
            }else{
                System.out.println("Error en el intento de comparacion NOT, revise los datos de entrada");
                return null;
            }
            
    }
    
     public ArrayList<Boolean> comparacionOr(ArrayList<Boolean> estados1, ArrayList<Boolean> estados2){
            
            if(estados1 == null){
                System.out.println("Estados 1 nulo");
            }
            if(estados1 == null){
                System.out.println("Estados 2 nulo");
            }
         
            if(estados1 != null && estados2 != null && estados1.size() == estados2.size()){
                ArrayList<Boolean> resultado = new ArrayList();
                for(int i = 0; i < estados1.size(); i++){
                        boolean estado = estados1.get(i) || estados2.get(i);
                        resultado.add(estado);
                }
                return resultado;
            }else{
                System.out.println("Error en el intento de OR, no tienen la misma longitud");
                return null;
            }            
    }
     
    public ArrayList<Boolean> comparacionAnd(ArrayList<Boolean> estados1, ArrayList<Boolean> estados2){
            if(estados1 == null){
                System.out.println("Estados 1 nulo");
            }
            if(estados1 == null){
                System.out.println("Estados 2 nulo");
            }
            if(estados1 != null && estados2 != null && estados1.size() == estados2.size()){
                ArrayList<Boolean> resultado = new ArrayList();
                for(int i = 0; i < estados1.size(); i++){
                        boolean estado = estados1.get(i) && estados2.get(i);
                        resultado.add(estado);
                }
                return resultado;
            }else{
                System.out.println("Error en el intento de AND, no tienen la misma longitud");
                return null;
            }            
    }

}
