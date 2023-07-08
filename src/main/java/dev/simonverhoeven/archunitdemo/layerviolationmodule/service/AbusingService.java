package dev.simonverhoeven.archunitdemo.layerviolationmodule.service;

import dev.simonverhoeven.archunitdemo.layerviolationmodule.controller.AbusedController;
import org.springframework.stereotype.Service;

@Service
public class AbusingService {
    public AbusingService(AbusedController abusedController) {
        abusedController.answertToLife();
    }
}
