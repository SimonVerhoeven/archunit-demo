package dev.simonverhoeven.archunitdemo.layerviolationmodule.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/layerviolation/abused")
public class AbusedController {

    public int answertToLife() {
        return 42;
    }
}
