package dev.simonverhoeven.archunitdemo.onionmodule.adapter.rest;

import dev.simonverhoeven.archunitdemo.onionmodule.adapter.persistence.InMemoryBookRepository;

public class BookController {

    // Violation: an adapter should not directly access another adapter
    private InMemoryBookRepository inMemoryBookRepository;
}
