package dev.simonverhoeven.archunitdemo.basemodule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basemodule")
public class BaseController {

    @Autowired
    private BaseService baseService;
    private final BaseRepository baseRepository;

    BaseController(BaseRepository baseRepository) {
        this.baseRepository = baseRepository;
        this.baseRepository.doNothing();
    }
}
