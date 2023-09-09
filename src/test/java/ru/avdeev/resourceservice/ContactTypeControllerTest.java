package ru.avdeev.resourceservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.avdeev.resourceservice.configuration.ServicesTestConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
public class ContactTypeControllerTest {

    @Autowired
    WebTestClient client;

    @Test
    void getContactTypeAllTest() {

        client.get().uri("/v1/contact-type")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().value(status -> {
                    assertThat(status).isEqualTo(200);
                });
    }

}
