package dev.simonverhoeven.archunitdemo.layeredmodule.persistence;

import dev.simonverhoeven.archunitdemo.layeredmodule.service.Layered2Service;
import org.springframework.stereotype.Repository;

@Repository
public class Layered2Repository {

    public Layered2Repository(Layered2Service layered2Service) {
        // Oops, layering violation
        layered2Service.importantCall();
    }
}
