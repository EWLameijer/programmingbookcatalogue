package org.ericwubbo.programmingbookcatalogue.book;

import lombok.AllArgsConstructor;
import org.ericwubbo.programmingbookcatalogue.author.Author;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Optional;


@RestController
@RequestMapping("api/v1/books")
@AllArgsConstructor
// note this yields big red lines... Either make the field not final and use @AllArgsConstructor,
// accept the red line, or create an explicit constructor
public class BookController {

    private BookRepository bookRepository;

    private BookService bookService;

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

    @GetMapping("{id}")
    public Optional<Book> getById(@PathVariable Long id) {
        return bookRepository.findById(id);
    }

    record AuthorDto(String firstName, String lastName) {}
    record BookDto(String title, AuthorDto[] authors) {}
    @PostMapping
    public ResponseEntity<Book> postBook(@RequestBody BookDto bookDto, UriComponentsBuilder ucb) {
        Book book = bookService.saveBook(bookDto.title, Arrays.stream(bookDto.authors).map(a -> new Author(a.firstName, a.lastName)).toList());
        URI locationOfNewBook = ucb
                .path("api/v1/books/{id}")
                .buildAndExpand(book.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewBook).body(book);
    }
}
