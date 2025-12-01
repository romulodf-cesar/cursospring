package br.com.senailab.netseries.service;

import br.com.senailab.netseries.model.DadosSerie;
import br.com.senailab.netseries.model.DadosTemporada;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SerieService {

    private final ConsumoAPI consumoAPI;
    private final IConverteDados conversor;

    // Configurações da API
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";

    // O Spring injeta as dependências (@Service)
    public SerieService(ConsumoAPI consumoAPI, IConverteDados conversor) {
        this.consumoAPI = consumoAPI;
        this.conversor = conversor;
    }

    public DadosSerie buscarDadosSeriePorTitulo(String titulo) {
        String url = ENDERECO + titulo.replace(" ", "+") + API_KEY;
        String json = consumoAPI.obterDados(url);
        return conversor.obterDados(json, DadosSerie.class);
    }

    // MLOGICA QUE ESTAVA NO SEU 'run()' para buscar todas as temporadas
    public List<DadosTemporada> buscarTodasTemporadas(String titulo, int totalTemporadas) {
        List<DadosTemporada> temporadas = new ArrayList<>();

        for(int i = 1; i <= totalTemporadas; i++) {
            String url = ENDERECO + titulo.replace(" ", "+") + "&season=" + i + API_KEY;
            String json = consumoAPI.obterDados(url);

            // Mapeia para DadosTemporada (se o JSON retornar dados válidos)
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        return temporadas;
    }
}