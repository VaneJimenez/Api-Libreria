package com.apireste2.backende2.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@ControllerAdvice
@OpenAPIDefinition(
    info = @Info(
        title = "Api Libreria",
        version = "1.0",
        description = "Api para el manejo de una libreria"
    ),
    tags = {
        @Tag(
            name = "Base Controller",
            description = "Controlador base para la api, este controlador se extender a todos los endpoints"
        )
    }
)

public class ApiBaseController {

}
