package br.com.cardoso.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@RestController
@Validated
public class HelloController {

    @GetMapping("/{number}")
    public Mono<String> hello(@PathVariable("number")
                              @Valid
                              @Min(message = "Não pode ser menor do que 10", value = 10)
                              @Max(message = "Não pode ser maior do que 100", value = 100)
                              @Positive(message = "Deve ser um número positivo")
                              int number) {
        return Mono.just("Hello " + number);
    }
}
