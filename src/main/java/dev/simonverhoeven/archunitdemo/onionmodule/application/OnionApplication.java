package dev.simonverhoeven.archunitdemo.onionmodule.application;

import dev.simonverhoeven.archunitdemo.onionmodule.adapter.persistence.InMemoryBookRepository;
import dev.simonverhoeven.archunitdemo.onionmodule.domain.service.BookService;

public class OnionApplication {
    // fine
    private BookService bookService;
    // Our application should not know about adapters
    private InMemoryBookRepository inMemoryBookRepository;
}
