package org.ericwubbo.programmingbookcatalogue;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public Iterable<Book> getAll(Pageable pageable) { // beware of correct import!
        return bookRepository.findAll(pageable); // http://localhost:8080/api/v1/books?page=0&size=10&sort=title,desc
    }

    @GetMapping("limited")
    public Iterable<Book> getAllLimited(Pageable pageable) {
        return bookRepository.findAll(PageRequest.of(pageable.getPageNumber(), Math.min(pageable.getPageSize(), 3),
                pageable.getSortOr(Sort.by(new Sort.Order(Sort.Direction.ASC, "title")))));
                //Sort.by(new Sort.Order(Sort.Direction.ASC, "title")))); // http://localhost:8080/api/v1/books?page=0&size=10&sort=title,desc
    }

}
