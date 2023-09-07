package ru.avdeev.resourceservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.avdeev.resourceservice.dto.ContactTypeDto;
import ru.avdeev.resourceservice.service.ContactTypeService;

@RestController
@RequestMapping("v1/contact-type")
@RequiredArgsConstructor
public class ContactTypeController {

    private final ContactTypeService service;
    @GetMapping("")
    public Flux<ContactTypeDto> getAll() {
        return service.getAll();
    }
}
