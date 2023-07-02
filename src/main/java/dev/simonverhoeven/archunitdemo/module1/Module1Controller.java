package dev.simonverhoeven.archunitdemo.module1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/module1Main")
public class Module1Controller {

    @Autowired
    private Module1Service module1Service;
    private final Module1Repository module1Repository;

    Module1Controller(Module1Repository module1Repository) {
        this.module1Repository = module1Repository;
        this.module1Repository.doNothing();
    }
}
