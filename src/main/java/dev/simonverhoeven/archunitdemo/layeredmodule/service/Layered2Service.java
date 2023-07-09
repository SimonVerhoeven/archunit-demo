package dev.simonverhoeven.archunitdemo.layeredmodule.service;

import dev.simonverhoeven.archunitdemo.layeredmodule.persistence.Layered2Repository;
import org.springframework.stereotype.Service;

@Service
public class Layered2Service {

    private final Layered2Repository layered2Repository;

    public Layered2Service(Layered2Repository layered2Repository) {
        this.layered2Repository = layered2Repository;
    }

    public void importantCall() {
        // do some important things
    }
}
