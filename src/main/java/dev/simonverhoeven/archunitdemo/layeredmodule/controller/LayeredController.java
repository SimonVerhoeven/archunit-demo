package dev.simonverhoeven.archunitdemo.layeredmodule.controller;

import dev.simonverhoeven.archunitdemo.layeredmodule.service.Layered2Service;
import dev.simonverhoeven.archunitdemo.layeredmodule.service.LayeredService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/layered/layered")
public class LayeredController {

    public LayeredController(LayeredService layeredService, Layered2Service layered2Service) {
        layeredService.importantCall();
        layered2Service.importantCall();
    }
}
