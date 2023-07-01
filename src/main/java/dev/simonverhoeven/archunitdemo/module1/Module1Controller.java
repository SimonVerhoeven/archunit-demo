package dev.simonverhoeven.archunitdemo.module1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Module1Controller {

    @Autowired
    private Module1Service module1Service;
}
