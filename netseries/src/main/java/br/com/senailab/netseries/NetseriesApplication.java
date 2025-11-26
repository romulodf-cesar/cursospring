package br.com.senailab.netseries;

import br.com.senailab.netseries.service.ConsumoAPI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@SpringBootApplication
public class NetseriesApplication implements CommandLineRunner {

	public static void main(String[] args) {

        SpringApplication.run(NetseriesApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Olá Mundo Spring Console!");
        var consumoAPI = new ConsumoAPI();
        var json= consumoAPI.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=6585022c");
        System.out.println(json);
        //Dever de casa: Próximo passo - Transformar esse JSON em objetos Java com Jackson.
    }

}
