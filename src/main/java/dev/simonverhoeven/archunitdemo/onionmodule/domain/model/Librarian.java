package dev.simonverhoeven.archunitdemo.onionmodule.domain.model;

import dev.simonverhoeven.archunitdemo.onionmodule.domain.service.BookService;

public class Librarian {

    // Violation the domainmodel should not know about services
    private BookService bookService;
}
