package dev.simonverhoeven.archunitdemo.onionmodule.domain.service;

import dev.simonverhoeven.archunitdemo.onionmodule.adapter.persistence.InMemoryBookRepository;

public class LibrarianService {
    // Our model should NOT know about implementations
    private InMemoryBookRepository inMemoryBookRepository;
}
