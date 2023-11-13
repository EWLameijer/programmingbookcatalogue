package org.ericwubbo.programmingbookcatalogue;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Seeder implements CommandLineRunner {

    private final BookRepository bookRepository;

    public Seeder(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (bookRepository.count() == 0) {
            bookRepository.saveAll(List.of(new Book("Code Complete 2", "Steve McConnell"),
                    new Book("The Pragmatic Programmer", "David Thomas & Andrew Hunt"),
                    new Book("Refactoring", "Martin Fowler"),
                    new Book("Domain-Driven Design", "Eric Evans"),
                    new Book("Unit Testing", "Vladimir Khorikov"),
                    new Book("Building Maintainable Software", "Joost Visser"),
                    new Book("Facts and Fallacies of Software Engineering", "Robert Glass")
            ));
        }
    }
}
