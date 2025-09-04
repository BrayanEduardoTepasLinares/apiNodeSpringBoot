package com.example.practicafrontend03092025.contraladores;

import com.example.practicafrontend03092025.dtos.DireccionRequestDto;
import com.example.practicafrontend03092025.dtos.DireccionResponseDto;

import reactor.core.publisher.Mono;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
@RequestMapping("/direccion")
public class DireccionController {
    private final WebClient webClient;

    public DireccionController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://8080-firebase-apinodejs03092025-1756915625788.cluster-dwvm25yncracsxpd26rcd5ja3m.cloudworkstations.dev").build();
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("direccionRequest", new DireccionRequestDto());
        return "direccionForm";
    }

    @PostMapping("/save")
    public Mono<String> procesarFormulario(@ModelAttribute DireccionRequestDto direccionRequest, Model model) {
        return this.webClient.post()
                .uri("/direccion")
                .bodyValue(direccionRequest)
                .retrieve()
                .bodyToMono(DireccionResponseDto.class)
                .map(response -> {
                    model.addAttribute("direccionResponse", response);
                    return "direccionForm";
                });
    }
}