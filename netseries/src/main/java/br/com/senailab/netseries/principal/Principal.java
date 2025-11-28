package br.com.senailab.netseries.principal;
import br.com.senailab.netseries.model.DadosSerie;
import br.com.senailab.netseries.service.ConsumoAPI;
import br.com.senailab.netseries.service.ConverteDados;

import java.util.Scanner;
public class Principal {
    private Scanner leitura =new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";

    public void exibeMenu(){
        System.out.println("Digite o nome da série que deseja buscar");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ","+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);
    }
}
