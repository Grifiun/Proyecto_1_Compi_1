/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

/**
 *
 * @author grifiun
 */
public class Cliente {
    
    private HttpClient httpCliente;
    private HttpRequest request;
    private String resource;//respuesta del server
    
    
    public Cliente(){
        this.httpCliente = HttpClient.newHttpClient();
        this.request = null;
        this.resource = "";
    }
            
    public String POST(String url, String texto) throws Exception{    
        //httpCliente = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
          .POST(HttpRequest.BodyPublishers.ofString(texto))
          .uri(URI.create(url))
          .build();
        
        try {
            
            HttpResponse<String> response =
            httpCliente.send(request, BodyHandlers.ofString());
            
            resource = response.body();
        } catch (Exception ex) {
            System.out.println("Error en la conexion con el servidor: "+ex.getMessage());
        }        
        
        
        //respuesta
        return this.resource;
    }
    
}
