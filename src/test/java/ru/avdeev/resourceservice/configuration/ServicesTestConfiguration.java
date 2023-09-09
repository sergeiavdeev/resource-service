package ru.avdeev.resourceservice.configuration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.avdeev.resourceservice.repository.ContactTypeRepository;

@TestConfiguration
public class ServicesTestConfiguration {

    @MockBean
    public ContactTypeRepository contactTypeRepository;
}
