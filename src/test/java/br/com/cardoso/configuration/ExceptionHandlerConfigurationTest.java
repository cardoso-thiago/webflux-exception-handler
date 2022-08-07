package br.com.cardoso.configuration;

import br.com.cardoso.controller.TestController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@WebFluxTest(TestController.class)
@Import(ExceptionHandlerConfiguration.class)
public class ExceptionHandlerConfigurationTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    public void sucessfulRequisition() {
        webClient.get().uri("/test/10").exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(String.class).isEqualTo("Hello 10");
    }

    @Test
    public void badRequestMin() {
        webClient.get().uri("/test/1").exchange()
                .expectStatus().is4xxClientError()
                .expectBody(String.class).isEqualTo("[\"Não pode ser menor do que 10\"]");
    }

    @Test
    public void badRequestMax() {
        webClient.get().uri("/test/101").exchange()
                .expectStatus().is4xxClientError()
                .expectBody(String.class).isEqualTo("[\"Não pode ser maior do que 100\"]");
    }

    @Test
    public void badRequestMinAndNegative() {
        webClient.get().uri("/test/-1").exchange()
                .expectStatus().is4xxClientError()
                .expectBody(String.class).isEqualTo("[\"Não pode ser menor do que 10\",\"Deve ser um número positivo\"]");
    }
}
