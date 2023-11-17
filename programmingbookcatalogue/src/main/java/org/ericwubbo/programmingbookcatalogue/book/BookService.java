package org.ericwubbo.programmingbookcatalogue.book;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.ericwubbo.programmingbookcatalogue.author.Author;
import org.ericwubbo.programmingbookcatalogue.author.AuthorRepository;
import org.ericwubbo.programmingbookcatalogue.bookauthor.BookAuthor;
import org.ericwubbo.programmingbookcatalogue.bookauthor.BookAuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {
    private BookRepository bookRepository;

    private AuthorRepository authorRepository;

    private BookAuthorRepository bookAuthorRepository;

    public Book saveBook(String title, Author author) {
        return saveBook(title, List.of(author));
    }

    @Transactional
    public Book saveBook(String title, List<Author> authors) {
        Book book = new Book(title);
        bookRepository.save(book);
        //if (true) throw new IllegalArgumentException("Throwing a wrench into the works!");

        int position = 0;
        for (Author author : authors) {
            Optional<Author> foundAuthor = authorRepository.findAuthorByFirstNameAndLastName(author.getFirstName(), author.getLastName());
            if (foundAuthor.isEmpty()) authorRepository.save(author);
            else author = foundAuthor.get();
            BookAuthor bookAuthor = new BookAuthor(book, author, position++);
            bookAuthorRepository.save(bookAuthor);
            book.addAuthor(bookAuthor);
        }
        return book;
    }
}
