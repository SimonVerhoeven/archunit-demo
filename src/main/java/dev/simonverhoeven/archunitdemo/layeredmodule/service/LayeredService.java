package dev.simonverhoeven.archunitdemo.layeredmodule.service;

import dev.simonverhoeven.archunitdemo.layeredmodule.persistence.LayeredRepository;
import org.springframework.stereotype.Service;

@Service
public class LayeredService {

    private final LayeredRepository layeredRepository;

    public LayeredService(LayeredRepository layeredRepository) {
        this.layeredRepository = layeredRepository;
    }

    public void importantCall() {
        // do some important things
    }
}
