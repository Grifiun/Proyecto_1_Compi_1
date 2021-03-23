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

/*
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
*/


/**
 *
 * @author grifiun
 */
public class Cliente {
    /*
    private HttpClient httpCliente;
    private HttpGet get;
    private HttpResponse response;
    private String resource;//respuesta del server
    
    public Cliente(){
        this.httpCliente = HttpClients.createDefault();
        this.get = null;
        this.resource = "";
    }
    
    public String GET(String url){
        this.get = new HttpGet(url);
               
        try {
            this.response = this.httpCliente.execute(this.get);//realizamos la peticion
            this.resource = EntityUtils.toString(this.response.getEntity());
        } catch (Exception ex) {
            System.out.println("Error en la conexion con el servidor: "+ex.getMessage());
        }        
        
        //respuesta
        return this.resource;
    }
    */
    private HttpClient httpCliente;
    private HttpRequest request;
    private String resource = "";//respuesta del server
    
        
    public String POST(String url, String texto) throws Exception{
        httpCliente = HttpClient.newHttpClient();
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
