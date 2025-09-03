package com.example.practicafrontend03092025.contraladores;
import com.example.practicafrontend03092025.dtos.ClienteRequestDto;
import com.example.practicafrontend03092025.dtos.ClienteResponseDto;

import reactor.core.publisher.Mono;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;
@Controller
@RequestMapping("/cliente")
public class ClienteController {
  
private final WebClient webClient;
 public ClienteController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://8080-firebase-apinodejs03092025-1756915625788.cluster-dwvm25yncracsxpd26rcd5ja3m.cloudworkstations.dev").build();
    } 
     @GetMapping()
    public String index(Model model) {
        model.addAttribute("clienteRequest", new ClienteRequestDto());
        return "clienteForm";
    }

    @PostMapping("/save")
    public Mono<String> procesarFormulario(@ModelAttribute ClienteRequestDto clienteRequest, Model model) {

       return this.webClient.post()
                         .uri("/cliente")
                         .bodyValue(clienteRequest)
                         .retrieve()
                         .bodyToMono(ClienteResponseDto.class)
                         .map(response -> {
                             // This code will execute only after the API call is successful
                             model.addAttribute("clienteResponse", response);
                             return "clienteForm"; // Return the view name
                         });     
    }
}