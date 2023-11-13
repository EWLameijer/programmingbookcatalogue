package org.ericwubbo.programmingbookcatalogue;

import lombok.AllArgsConstructor;
import org.ericwubbo.programmingbookcatalogue.author.Author;
import org.ericwubbo.programmingbookcatalogue.book.BookRepository;
import org.ericwubbo.programmingbookcatalogue.book.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class Seeder implements CommandLineRunner {

    private BookRepository bookRepository;

    private BookService bookService;

    @Override
    public void run(String... args) throws Exception {
        if (bookRepository.count() == 0) {
            bookService.saveBook("Code Complete 2", new Author("Steve", "McConnell"));
            bookService.saveBook("The Pragmatic Programmer", List.of(new Author("David", "Thomas"), new Author("Andrew", "Hunt")));
            bookService.saveBook("Refactoring", new Author("Martin", "Fowler"));
            bookService.saveBook("Domain-Driven Design", new Author("Eric", "Evans"));
            bookService.saveBook("Unit Testing", new Author("Vladimir", "Khorikov"));
            bookService.saveBook("Building Maintainable Software", new Author("Joost", "Visser"));
            bookService.saveBook("Facts and Fallacies of Software Engineering", new Author("Robert", "Glass"));
            bookService.saveBook("Patterns of Enterprise Application Architecture", new Author("Martin", "Fowler"));
        }
    }
}
