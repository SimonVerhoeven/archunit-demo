package dev.simonverhoeven.archunitdemo.servicecontroller.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("abused")
public class AbusedController {

    public int answertToLife() {
        return 42;
    }
}
