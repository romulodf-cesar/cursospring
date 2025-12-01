package br.com.senailab.netseries.controller;

import br.com.senailab.netseries.model.DadosSerie;
import br.com.senailab.netseries.model.DadosTemporada;
import br.com.senailab.netseries.service.SerieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class SerieController {

    private final SerieService serieService;

    public SerieController(SerieService serieService) {
        this.serieService = serieService;
    }

    // Mapeia para http://localhost:8080/ (Página Inicial)
    @GetMapping("/")
    public String index() {
        return "pesquisa"; // Renderiza src/main/resources/templates/pesquisa.html
    }

    // Processa o formulário de pesquisa
    @PostMapping("/detalhes")
    public String buscarDetalhes(@RequestParam("titulo") String titulo, Model model) {

        try {
            // 1. Busca dados gerais
            DadosSerie serie = serieService.buscarDadosSeriePorTitulo(titulo);

            // 2. Busca todas as temporadas
            List<DadosTemporada> temporadas = serieService.buscarTodasTemporadas(
                    titulo,
                    serie.totalTemporadas()
            );

            // 3. Adiciona ao Model (dados que o Thymeleaf vai usar)
            model.addAttribute("serie", serie);
            model.addAttribute("temporadas", temporadas);

            return "detalhesSerie"; // Renderiza src/main/resources/templates/detalhesSerie.html

        } catch (Exception e) {
            // Trata erro de série não encontrada (volta para a pesquisa)
            model.addAttribute("erro", "Série não encontrada ou erro na API.");
            return "pesquisa";
        }
    }
}