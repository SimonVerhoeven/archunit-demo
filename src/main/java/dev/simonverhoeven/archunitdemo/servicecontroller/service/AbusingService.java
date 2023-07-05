package dev.simonverhoeven.archunitdemo.servicecontroller.service;

import dev.simonverhoeven.archunitdemo.servicecontroller.controller.AbusedController;
import org.springframework.stereotype.Service;

@Service
public class AbusingService {
    public AbusingService(AbusedController abusedController) {
        abusedController.answertToLife();
    }
}
