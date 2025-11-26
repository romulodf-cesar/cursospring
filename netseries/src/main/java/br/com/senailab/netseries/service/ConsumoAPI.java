package br.com.senailab.netseries.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {
    //separar responsabilidades
    //código limpo, testável e reutilizável
    //assinatura do metodo - endereco (pegar o endereço da API)
    public  String obterDados(String endereco){
        // objeto que representar o cliente
        HttpClient client = HttpClient.newHttpClient(); //Cliente
        HttpRequest request = HttpRequest.newBuilder()  //Requisição
                .uri(URI.create(endereco))
                .build();
        HttpResponse<String> response = null; //Resposta
        try{
            response = client.send(request,HttpResponse.BodyHandlers.ofString());
        }catch(IOException e){
            throw new RuntimeException();
        }catch(InterruptedException e){
            throw new RuntimeException();
        }
        String json = response.body();
        return json;
    }
}
